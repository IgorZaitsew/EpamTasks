package by.tc.zaycevigor.dao.impl;

import by.tc.zaycevigor.dao.ContractDAO;
import by.tc.zaycevigor.dao.exception.ConnectionPoolException;
import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.dao.util.MessageSender;
import by.tc.zaycevigor.dao.util.MessageService;
import by.tc.zaycevigor.dao.util.RandomGenerator;
import by.tc.zaycevigor.entity.Contract;
import by.tc.zaycevigor.entity.ContractData;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.security.crypto.bcrypt.BCrypt;

import static by.tc.zaycevigor.dao.util.Closer.close;
import static by.tc.zaycevigor.dao.util.Constant.*;
import static by.tc.zaycevigor.dao.util.IsUniqueCheck.isUniqueDatas;
import static by.tc.zaycevigor.entity.ContractData.CONTRACT_NUMBER_SIZE;
import static by.tc.zaycevigor.entity.ContractData.PASSWORD_SIZE;


public class SQLContractDAO extends SqlDao implements ContractDAO {

    private static final String CON_POOL_CREATE_EXC = "Cannot create connection pool";
    private static final String RES_SET_CLOSE_EXC = "Cannot close result set";
    private static final String PREP_STAT_CLOSE_EXC = "Cannot close prepared statement";


    private static final String QUERY_GET_CONTRACT_DATA = "SELECT * FROM " + PARAMETER_PERSONAL_DATA_TABLE_NAME + " WHERE " +
            PARAMETER_CONTRACT_NUMBER + "=?";
    private static final String QUERY_CHECK_CONTRACT_NUMBER_USAGE = "SELECT COUNT(" + PARAMETER_CONTRACT_NUMBER + ") FROM personal_data WHERE " + PARAMETER_CONTRACT_NUMBER + "=?";
    private static final String QUERY_CHECK_PASSPORT_ID_USAGE = "SELECT COUNT(" + PARAMETER_PASSPORT_ID + ") FROM personal_data WHERE " + PARAMETER_PASSPORT_ID + "=?";
    private static final String QUERY_ADD_CONTRACT = "INSERT INTO " + PARAMETER_PERSONAL_DATA_TABLE_NAME + " (" + PARAMETER_CONTRACT_NUMBER +
            ", " + PARAMETER_PASSPORT_ID + ", " + PARAMETER_TARIFF_ID + ", " + PARAMETER_PASSWORD + ", " + PARAMETER_NAME +
            ", " + PARAMETER_SURNAME + ", " + PARAMETER_CITY + ", " + PARAMETER_STREET + ", " + PARAMETER_HOUSE_NUMBER +
            ")VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String QUERY_UPDATE_TARIFF_ID = "UPDATE " + PARAMETER_PERSONAL_DATA_TABLE_NAME + " SET " + PARAMETER_TARIFF_ID +
            "=? WHERE " + PARAMETER_CONTRACT_NUMBER + "=?";
    private static final String QUERY_DELETE_CONTRACT = "DELETE FROM " + PARAMETER_PERSONAL_DATA_TABLE_NAME + " WHERE " + PARAMETER_CONTRACT_NUMBER + " =?";
    private static final String QUERY_SET_BALANCE = "UPDATE " + PARAMETER_PERSONAL_DATA_TABLE_NAME + " SET " +
            PARAMETER_BALANCE + " =? WHERE " + PARAMETER_CONTRACT_NUMBER + " =?";
    private static final String QUERY_UPDATE_BALANCE = "UPDATE " + PARAMETER_PERSONAL_DATA_TABLE_NAME + " SET " +
            PARAMETER_BALANCE + " = " + PARAMETER_BALANCE + " + ? WHERE " + PARAMETER_TARIFF_ID + " =? ";
    private static final String QUERY_RESET_TARIFF = "UPDATE " + PARAMETER_PERSONAL_DATA_TABLE_NAME + " SET " + PARAMETER_TARIFF_ID +
            " =1 WHERE " + PARAMETER_TARIFF_ID + " =?";
    private static final String QUERY_GET_NUMBER = "SELECT " + PARAMETER_CONTRACT_NUMBER + " FROM " +
            PARAMETER_PERSONAL_DATA_TABLE_NAME + " WHERE " + PARAMETER_TARIFF_ID + " =?";
    private static final String QUERY_GET_BALANCE_BY_ID = "SELECT " + PARAMETER_BALANCE + " FROM " +
            PARAMETER_PERSONAL_DATA_TABLE_NAME + " WHERE " + PARAMETER_CONTRACT_NUMBER + " =?";

    private static final ConnectionPoolImpl pool;
    private long contractNumber;

    static {
        try {
            pool = ConnectionPoolImpl.create(url, login, password);
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }

    /**
     * Returns the contract corresponding to the contract number and password entered by the user
     *
     * @param contractNumber
     * @param contractPassword
     * @return Contract corresponding to the param's
     * @throws DaoException
     */
    @Override
    public Contract contractAuthentification(long contractNumber, String contractPassword) throws DaoException {
        Connection connection = pool.getConnection();

        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        Contract contract = null;
        try {
            prepStatement = connection.prepareStatement(QUERY_GET_CONTRACT_DATA);
            prepStatement.setLong(1, contractNumber);
            resultSet = prepStatement.executeQuery();
            if (resultSet.next()) {
                if (BCrypt.checkpw(contractPassword, resultSet.getString(PARAMETER_PASSWORD))) {
                    contract = createContract(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(pool, resultSet, prepStatement, connection);
        }
        return contract;
    }

    /**
     * Returns the contract corresponding to the number of the contract entered by the admin.
     * Only available for users with a role field equal to 'admin'
     *
     * @param contractNumber
     * @return Contract corresponding to the contractNumber or null if the contract is not found
     * @throws DaoException
     */
    @Override
    public Contract getContract(long contractNumber) throws DaoException {
        Connection connection = pool.getConnection();

        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        Contract contract = null;
        try {
            prepStatement = connection.prepareStatement(QUERY_GET_CONTRACT_DATA);
            prepStatement.setLong(1, contractNumber);
            resultSet = prepStatement.executeQuery();
            if (resultSet.next()) {
                contract = createContract(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(pool, resultSet, prepStatement, connection);
        }
        return contract;
    }

    /**
     * Creates a new contract according to the entered ContractData
     *
     * @param data
     * @return
     * @throws DaoException
     */
    @Override
    public boolean addContract(ContractData data) throws DaoException {
        Connection connection;
        connection = pool.getConnection();
        RandomGenerator passwordGenerator = passwordGenerator();
        RandomGenerator contractNumberGenerator = contractNumberGenerator();
        String password = passwordGenerator.generate(PASSWORD_SIZE);
        contractNumber = Long.parseLong(contractNumberGenerator.generate(CONTRACT_NUMBER_SIZE));

        PreparedStatement prepStatement = null;
        if (!isUniqueDatas(connection, data.getPassportId(), QUERY_CHECK_PASSPORT_ID_USAGE)) {
            return false;
        }
        while (!isUniqueDatas(connection, String.valueOf(contractNumber), QUERY_CHECK_CONTRACT_NUMBER_USAGE)) {
            contractNumber = Long.parseLong(contractNumberGenerator.generate(CONTRACT_NUMBER_SIZE));
        }
        int result;
        try {
            prepStatement = connection.prepareStatement(QUERY_ADD_CONTRACT);

            prepStatement.setLong(1, contractNumber);
            prepStatement.setString(2, data.getPassportId());
            prepStatement.setInt(3, BASIC_TARIFF_ID);
            String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
            prepStatement.setString(4, passwordHash);
            if (BCrypt.checkpw(password, passwordHash)) {
            }
            prepStatement.setString(5, data.getName());
            prepStatement.setString(6, data.getSurname());
            prepStatement.setString(7, data.getCity());
            prepStatement.setString(8, data.getStreet());
            prepStatement.setString(9, data.getHouseNumber());

            result = prepStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);

        } finally {
            close(pool, prepStatement, connection);
        }
        sendEmail(contractNumber, password, data.getEmail());

        return result > 0;
    }

    /**
     * changes the id in the contract determined by the contract number
     *
     * @param contractNumber
     * @param tariffId
     * @return
     * @throws DaoException
     */
    @Override
    public boolean changeTariff(long contractNumber, int tariffId) throws DaoException {
        Connection connection;
        connection = pool.getConnection();
        PreparedStatement prepStatement = null;
        int result;
        try {
            prepStatement = connection.prepareStatement(QUERY_UPDATE_TARIFF_ID);
            prepStatement.setInt(1, tariffId);
            prepStatement.setLong(2, contractNumber);
            result = prepStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);

        } finally {
            close(pool, prepStatement, connection);
        }
        return result > 0;
    }

    /**
     * create instance of Contract by result set data
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private Contract createContract(ResultSet resultSet) throws SQLException {
        Contract contract = new Contract();
        contract.setContractNumber(resultSet.getLong(PARAMETER_CONTRACT_NUMBER));
        contract.setPassportId(resultSet.getString(PARAMETER_PASSPORT_ID));
        contract.setName(resultSet.getString(PARAMETER_NAME));
        contract.setSurname(resultSet.getString(PARAMETER_SURNAME));
        contract.setCity(resultSet.getString(PARAMETER_CITY));
        contract.setStreet(resultSet.getString(PARAMETER_STREET));
        contract.setHouseNumber(resultSet.getString(PARAMETER_HOUSE_NUMBER));
        contract.setBalance(resultSet.getBigDecimal(PARAMETER_BALANCE));
        contract.setTariffId(resultSet.getInt(PARAMETER_TARIFF_ID));
        return contract;
    }

    /**
     * send email with contract number and password to registrated user
     *
     * @param contractNumber
     * @param password
     * @param email
     */
    private void sendEmail(long contractNumber, String password, String email) {
        String text = MESSAGE_CONTRACT_NUMBER + contractNumber + MESSAGE_PASSWORD + password;
        MessageService service = MessageService.MESSAGE_SERVICE;
        service.sendMail(MESSAGE_SUBJECT, text, email);
    }

    /**
     * removes contract from DB
     *
     * @param contractNumber
     * @return
     * @throws DaoException
     */
    @Override
    public boolean deleteContract(long contractNumber) throws DaoException {
        Connection connection = pool.getConnection();
        PreparedStatement prepStatement = null;

        int result;
        try {
            prepStatement = connection.prepareStatement(QUERY_DELETE_CONTRACT);
            prepStatement.setLong(1, contractNumber);

            result = prepStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(pool, prepStatement, connection);
        }
        return result > 0;
    }

    @Override
    public long getContractNumber() {
        return contractNumber;
    }

    /**
     * increases the balance in the account with the specified contract number
     *
     * @param newBalance
     * @param contractNumber
     * @return
     * @throws DaoException
     */
    @Override
    public boolean upBalance(BigDecimal newBalance, long contractNumber) throws DaoException {
        Connection connection;
        connection = pool.getConnection();
        PreparedStatement prepStatement = null;
        int result;
        try {
            prepStatement = connection.prepareStatement(QUERY_SET_BALANCE);
            prepStatement.setBigDecimal(1, newBalance);
            prepStatement.setLong(2, contractNumber);
            result = prepStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);

        } finally {
            close(pool, prepStatement, connection);
        }
        return result > 0;
    }

    @Override
    public void updateAllBalances(BigDecimal delta, Map<Integer, BigDecimal> priceList) throws DaoException {
        Connection connection;
        connection = pool.getConnection();
        PreparedStatement prepStatement = null;
        try {
            for (int tariffId : priceList.keySet()) {
                prepStatement = connection.prepareStatement(QUERY_UPDATE_BALANCE);
                prepStatement.setBigDecimal(1, delta.multiply(priceList.get(tariffId)));
                prepStatement.setInt(2, tariffId);
                prepStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);

        } finally {
            close(pool, prepStatement, connection);
        }
    }


    @Override
    public Queue<Long> findTariffUsers(int tariffId) throws DaoException {
        Connection connection;
        connection = pool.getConnection();
        PreparedStatement prepStatement = null;
        ResultSet resultSet;
        ArrayDeque<Long> contractNumbers = null;
        try {
            prepStatement = connection.prepareStatement(QUERY_GET_NUMBER);
            prepStatement.setLong(1, tariffId);
            resultSet = prepStatement.executeQuery();

            if (resultSet.last()) {
                contractNumbers = new ArrayDeque<>(resultSet.getRow());
                resultSet.beforeFirst();
            }
            while (resultSet.next()) {
                contractNumbers.add(resultSet.getLong(PARAMETER_CONTRACT_NUMBER));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(pool, prepStatement, connection);
        }
        return contractNumbers;
    }

    @Override
    public boolean resetTariffUsers(int tariffId) throws DaoException {
        Connection connection;
        connection = pool.getConnection();
        PreparedStatement prepStatement = null;
        int result;
        try {
            prepStatement = connection.prepareStatement(QUERY_RESET_TARIFF);
            prepStatement.setInt(1, tariffId);
            result = prepStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(pool, prepStatement, connection);
        }
        return result > 0;
    }

    @Override
    public BigDecimal getBalance(long contractNumber) throws DaoException {
        Connection connection;
        connection = pool.getConnection();
        PreparedStatement prepStatement = null;
        BigDecimal result = null;
        try {
            prepStatement = connection.prepareStatement(QUERY_GET_BALANCE_BY_ID);
            prepStatement.setLong(1, contractNumber);
            ResultSet resultSet;
            resultSet = prepStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getBigDecimal(PARAMETER_BALANCE);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(pool, prepStatement, connection);
        }
        return result;
    }

    public RandomGenerator passwordGenerator() {
        RandomGenerator randomGenerator = new RandomGenerator.RandomGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        return randomGenerator;
    }

    public RandomGenerator contractNumberGenerator() {
        RandomGenerator randomGenerator = new RandomGenerator.RandomGeneratorBuilder()
                .useDigits(true)
                .build();
        return randomGenerator;
    }

}
