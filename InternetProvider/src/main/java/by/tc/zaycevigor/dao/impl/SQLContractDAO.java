package by.tc.zaycevigor.dao.impl;

import by.tc.zaycevigor.dao.ContractDAO;
import by.tc.zaycevigor.dao.exception.ConnectionPoolException;
import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.dao.util.MessageSender;
import by.tc.zaycevigor.entity.Contract;
import by.tc.zaycevigor.entity.ContractData;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCrypt;

import static by.tc.zaycevigor.dao.util.Closer.close;
import static by.tc.zaycevigor.dao.util.Constant.*;
import static by.tc.zaycevigor.dao.util.IsUniqueCheck.isUniqueDatas;


public class SQLContractDAO extends SqlDao implements ContractDAO {

    private static final String CON_POOL_CREATE_EXC = "Cannot create connection pool";
    private static final String RES_SET_CLOSE_EXC = "Cannot close result set";
    private static final String PREP_STAT_CLOSE_EXC = "Cannot close prepared statement";


    private static final String QUERY_CHECK_CREDENTIONALS = "SELECT * FROM " + PARAMETER_PERSONAL_DATA_TABLE_NAME + " WHERE " +
            PARAMETER_CONTRACT_NUMBER + "=?";
    private static final String QUERY_CHECK_CONTRACT_NUMBER_USAGE = "SELECT COUNT(" + PARAMETER_CONTRACT_NUMBER + ") FROM personal_data WHERE " + PARAMETER_CONTRACT_NUMBER + "=?";
    private static final String QUERY_CHECK_PASSPORT_ID_USAGE = "SELECT COUNT(" + PARAMETER_PASSPORT_ID + ") FROM personal_data WHERE " + PARAMETER_PASSPORT_ID + "=?";
    private static final String QUERY_ADD_CONTRACT = "INSERT INTO " + PARAMETER_PERSONAL_DATA_TABLE_NAME + " (" + PARAMETER_CONTRACT_NUMBER +
            ", " + PARAMETER_PASSPORT_ID + ", " + PARAMETER_TARIFF_ID + ", " + PARAMETER_PASSWORD + ", " + PARAMETER_NAME +
            ", " + PARAMETER_SURNAME + ", " + PARAMETER_CITY + ", " + PARAMETER_STREET + ", " + PARAMETER_HOUSE_NUMBER +
            ")VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String QUERY_UPDATE_TARIFF_ID = "UPDATE " + PARAMETER_PERSONAL_DATA_TABLE_NAME + " SET " + PARAMETER_TARIFF_ID +
            "=? WHERE " + PARAMETER_CONTRACT_NUMBER + "=?";
    private static Logger log = Logger.getLogger(SQLContractDAO.class);
    private static final ConnectionPoolImpl pool;
    private static MessageSender tlsSender;
    private long contractNumber;

    static {
        try {
            pool = ConnectionPoolImpl.create(url, login, password);
        } catch (SQLException e) {
            log.error(CON_POOL_CREATE_EXC);
            throw new ConnectionPoolException(e);
        }
    }

    @Override
    public Contract contractAuthentification(long contractNumber, String contractPassword) throws DaoException {
        Connection connection = pool.getConnection();

        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        Contract contract = null;
        try {
            prepStatement = connection.prepareStatement(QUERY_CHECK_CREDENTIONALS);
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

    @Override
    public boolean addContract(ContractData data) throws DaoException {
        Connection connection;
        connection = pool.getConnection();
        String password = data.generatePassword();
        contractNumber = data.generateContractNumber();
        PreparedStatement prepStatement = null;
        if (!isUniqueDatas(connection, data.getPassportId(), QUERY_CHECK_PASSPORT_ID_USAGE)) {
            return false;
        }
        while (!isUniqueDatas(connection, String.valueOf(contractNumber), QUERY_CHECK_CONTRACT_NUMBER_USAGE)) {
            contractNumber = data.generateContractNumber();
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
            log.error(e);
            throw new DaoException(e);

        } finally {
            close(pool, prepStatement, connection);
        }
        sendEmail(contractNumber, password, data.getEmail());
        return result > 0;
    }

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
            log.error(e);
            throw new DaoException(e);

        } finally {
            close(pool, prepStatement, connection);
        }
        return result > 0;
    }

    private Contract createContract(ResultSet resultSet) throws SQLException {
        Contract contract = new Contract();
        contract.setContractNumber(resultSet.getLong(PARAMETER_CONTRACT_NUMBER));
        contract.setPassportId(resultSet.getString(PARAMETER_PASSPORT_ID));
        contract.setName(resultSet.getString(PARAMETER_NAME));
        contract.setSurname(resultSet.getString(PARAMETER_SURNAME));
        contract.setCity(resultSet.getString(PARAMETER_CITY));
        contract.setStreet(resultSet.getString(PARAMETER_STREET));
        contract.setHouseNumber(resultSet.getString(PARAMETER_HOUSE_NUMBER));
        contract.setBalance(Float.parseFloat(resultSet.getString(PARAMETER_BALANCE)));
        contract.setTariffId(Integer.parseInt(resultSet.getString(PARAMETER_TARIFF_ID)));
        return contract;
    }

    private void sendEmail(long contractNumber, String password, String email) {
        tlsSender = new MessageSender();
        String text = MESSAGE_CONTRACT_NUMBER + contractNumber + MESSAGE_PASSWORD + password;
        tlsSender.send(MESSAGE_SUBJECT, text, email);
    }

    @Override
    public long getContractNumber() {
        return contractNumber;
    }
}
