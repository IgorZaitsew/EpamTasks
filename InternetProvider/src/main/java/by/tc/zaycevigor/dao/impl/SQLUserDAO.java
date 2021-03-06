package by.tc.zaycevigor.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


import by.tc.zaycevigor.dao.exception.ConnectionPoolException;
import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.dao.UserDAO;
import by.tc.zaycevigor.dao.util.MessageSender;
import by.tc.zaycevigor.dao.util.MessageService;
import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.entity.UserData;

import static by.tc.zaycevigor.dao.util.Closer.close;
import static by.tc.zaycevigor.dao.util.Constant.*;
import static by.tc.zaycevigor.dao.util.IsUniqueCheck.isUniqueDatas;

public class SQLUserDAO extends SqlDao implements UserDAO {

    private static final String QUERY_REGISTRATE_USER = "INSERT INTO " + PARAMETER_USER_TABLE_NAME + " (" + PARAMETER_CONTRACT_NUMBER + ", " +
            PARAMETER_EMAIL + ") VALUES (?,?)";
    private static final String QUERY_ADD_USER = "INSERT INTO " + PARAMETER_USER_TABLE_NAME + " (" + PARAMETER_CONTRACT_NUMBER + ", " +
            PARAMETER_EMAIL + ", " + PARAMETER_ROLE + ", " + PARAMETER_STATUS + ") VALUES (?,?,?,?)";

    private static final String QUERY_CHECK_CREDENTIONALS = "SELECT * FROM " + PARAMETER_USER_TABLE_NAME + " WHERE " +
            PARAMETER_CONTRACT_NUMBER + "=?";
    private static final String QUERY_CHECK_EMAIL_USAGE = "SELECT COUNT(" + PARAMETER_EMAIL + ") FROM " + PARAMETER_USER_TABLE_NAME +
            " WHERE " + PARAMETER_EMAIL + "=?";
    private static final String QUERY_CHECK_CONTRACT_NUMBER = "SELECT COUNT(" + PARAMETER_CONTRACT_NUMBER + ") FROM " +
            PARAMETER_USER_TABLE_NAME + " WHERE " + PARAMETER_CONTRACT_NUMBER + "=?";
    private static final String QUERY_GET_USER_LIST = "SELECT * FROM " + PARAMETER_USER_TABLE_NAME;
    private static final String QUERY_DELETE_USER = "DELETE FROM " + PARAMETER_USER_TABLE_NAME + " WHERE " + PARAMETER_CONTRACT_NUMBER + " =?";
    private static final String QUERY_GET_MAIL = "SELECT " + PARAMETER_EMAIL + " FROM " +
            PARAMETER_USER_TABLE_NAME + " WHERE " + PARAMETER_CONTRACT_NUMBER + " =?";
    private static final String QUERY_READJUST_USER = "UPDATE " + PARAMETER_USER_TABLE_NAME + " SET " + PARAMETER_STATUS +
            " =?, " + PARAMETER_ROLE + " =?, " + PARAMETER_EMAIL + " =?, " + PARAMETER_CONTRACT_NUMBER + " =? " + " WHERE " + PARAMETER_ID + " =?";
    private static final String QUERY_UPDATE_STATUS_USER = "UPDATE " + PARAMETER_USER_TABLE_NAME + " SET " + PARAMETER_STATUS +
            " =? "  + " WHERE " + PARAMETER_CONTRACT_NUMBER + " =?";
    private static final ConnectionPoolImpl pool;

    static {
        try {
            pool = ConnectionPoolImpl.create(url, login, password);
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }

    /**
     * return user with contract number specified in params
     *
     * @param contractNumber
     * @return
     * @throws DaoException
     */
    @Override
    public User getUser(long contractNumber) throws DaoException {
        Connection connection = pool.getConnection();

        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            prepStatement = connection.prepareStatement(QUERY_CHECK_CREDENTIONALS);
            prepStatement.setLong(1, contractNumber);
            resultSet = prepStatement.executeQuery();
            if (resultSet.next()) {
                user = createUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(pool, resultSet, prepStatement, connection);
        }

        return user;
    }

    /**
     * creates the instance of user by result set
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private User createUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setEmail(resultSet.getString(PARAMETER_EMAIL));
        user.setContractNumber(resultSet.getLong(PARAMETER_CONTRACT_NUMBER));
        user.setId(resultSet.getInt(PARAMETER_ID));
        user.setRole(resultSet.getString(PARAMETER_ROLE));
        user.setBanStatus(resultSet.getString(PARAMETER_STATUS));
        return user;

    }

    /**
     * add user with userData to DB
     *
     * @param userData
     * @return
     * @throws DaoException
     */
    @Override
    public boolean registration(UserData userData) throws DaoException {
        Connection connection;
        connection = pool.getConnection();

        PreparedStatement prepStatement = null;
        if (!isUniqueDatas(connection, String.valueOf(userData.getContractNumber()), QUERY_CHECK_CONTRACT_NUMBER, userData.getEmail(), QUERY_CHECK_EMAIL_USAGE)) {
            return false;
        }
        int result;
        try {
            prepStatement = connection.prepareStatement(QUERY_REGISTRATE_USER);

            prepStatement.setLong(1, userData.getContractNumber());
            prepStatement.setString(2, userData.getEmail());

            result = prepStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);

        } finally {
            close(pool, prepStatement, connection);
        }
        return result > 0;
    }

    /**
     * return all users,contains at DB
     *
     * @return
     * @throws DaoException
     */
    @Override
    public List<User> getUserList() throws DaoException {
        Connection connection = pool.getConnection();
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(QUERY_GET_USER_LIST);
            while (resultSet.next()) {
                userList.add(createUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(pool, resultSet, statement, connection);
        }

        return userList;
    }

    /**
     * removes user with contract number specified at params from DB
     *
     * @param contractNumber
     * @return
     * @throws DaoException
     */
    @Override
    public boolean deleteUser(long contractNumber) throws DaoException {
        Connection connection = pool.getConnection();
        PreparedStatement prepStatement = null;

        int result;
        try {
            prepStatement = connection.prepareStatement(QUERY_DELETE_USER);
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
    public boolean sendWarnings(Queue<Long> contractNumbers) throws DaoException {
        Connection connection = pool.getConnection();
        PreparedStatement prepStatement = null;

        ResultSet resultSet;
        int result = 0;
        try {
            prepStatement = connection.prepareStatement(QUERY_GET_MAIL);
            for (Long number : contractNumbers) {
                prepStatement.setLong(1, number);
                resultSet = prepStatement.executeQuery();
                if (resultSet.next()) {
                    result++;
                    sendEmail(resultSet.getString(PARAMETER_EMAIL));
                }
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(pool, prepStatement, connection);
        }
        return result > 0;
    }

    @Override
    public boolean readjustUser(int userId, UserData userData) throws DaoException {
        Connection connection;
        connection = pool.getConnection();
        PreparedStatement prepStatement = null;
        int result;
        try {
            prepStatement = connection.prepareStatement(QUERY_READJUST_USER);

            prepStatement.setString(1, userData.getStatus());
            prepStatement.setString(2, userData.getRole());
            prepStatement.setString(3, userData.getEmail());
            prepStatement.setLong(4, userData.getContractNumber());
            prepStatement.setInt(5, userId);
            result = prepStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(pool, prepStatement, connection);
        }
        return result > 0;
    }

    @Override
    public void setStatus(long contractNumber,String status)throws DaoException{
        Connection connection;
        connection = pool.getConnection();
        PreparedStatement prepStatement = null;
        try {
            prepStatement = connection.prepareStatement(QUERY_UPDATE_STATUS_USER);
            prepStatement.setString(1, status);
            prepStatement.setLong(2, contractNumber);
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(pool, prepStatement, connection);
        }
    }

    private void sendEmail(String email) {
        MessageService service = MessageService.MESSAGE_SERVICE;
        service.sendMail(MESSAGE_SUBJECT_TARIFF_REMOVE, MESSAGE_TEXT, email);
    }


}