package by.tc.zaycevigor.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import by.tc.zaycevigor.dao.exception.ConnectionPoolException;
import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.dao.UserDAO;
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

    private static final ConnectionPoolImpl pool;

    static {
        try {
            pool = ConnectionPoolImpl.create(url, login, password);
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }

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


    private User createUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setEmail(resultSet.getString(PARAMETER_EMAIL));
        user.setContractNumber(resultSet.getLong(PARAMETER_CONTRACT_NUMBER));
        user.setId(resultSet.getInt(PARAMETER_ID));
        user.setRole(resultSet.getString(PARAMETER_ROLE));
        user.setBanStatus(resultSet.getString(PARAMETER_STATUS));
        return user;

    }

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

    @Override
    public boolean addUser(UserData userData) throws DaoException {
        Connection connection;
        connection = pool.getConnection();

        PreparedStatement prepStatement = null;
        if (!isUniqueDatas(connection, String.valueOf(userData.getContractNumber()), QUERY_CHECK_CONTRACT_NUMBER, userData.getEmail(), QUERY_CHECK_EMAIL_USAGE)) {
            return false;
        }
        int result;
        try {
            prepStatement = connection.prepareStatement(QUERY_ADD_USER);

            prepStatement.setLong(1, userData.getContractNumber());
            prepStatement.setString(2, userData.getEmail());
            prepStatement.setString(3, userData.getRole());
            prepStatement.setString(4, userData.getStatus());

            result = prepStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);

        } finally {
            close(pool, prepStatement, connection);
        }
        return result > 0;
    }

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

}