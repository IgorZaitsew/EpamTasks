package by.tc.zaycevigor.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import by.tc.zaycevigor.dao.exception.ConnectionPoolException;
import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.dao.UserDAO;
import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.entity.UserData;
import org.springframework.security.crypto.bcrypt.BCrypt;

import org.apache.log4j.Logger;

import static by.tc.zaycevigor.dao.util.Closer.close;
import static by.tc.zaycevigor.dao.util.Constant.*;
import static by.tc.zaycevigor.dao.util.IsUniqueCheck.isUniqueDatas;

public class SQLUserDAO extends SqlDao implements UserDAO {
    private static final String CON_POOL_CREATE_EXC = "Cannot create connection pool";

    private static final String QUERY_REGISTRATE_USER = "INSERT INTO " + PARAMETER_USER_TABLE_NAME + " (" + PARAMETER_CONTRACT_NUMBER + ", " +
            PARAMETER_EMAIL + ") VALUES (?,?)";
    private static final String QUERY_CHECK_CREDENTIONALS = "SELECT * FROM " + PARAMETER_USER_TABLE_NAME + " WHERE " +
            PARAMETER_CONTRACT_NUMBER + "=?";
    private static final String QUERY_CHECK_EMAIL_USAGE = "SELECT COUNT(" + PARAMETER_EMAIL + ") FROM " + PARAMETER_USER_TABLE_NAME +
            " WHERE " + PARAMETER_EMAIL + "=?";
    private static final String QUERY_CHECK_CONTRACT_NUMBER = "SELECT COUNT(" + PARAMETER_CONTRACT_NUMBER + ") FROM " +
            PARAMETER_USER_TABLE_NAME + " WHERE " + PARAMETER_CONTRACT_NUMBER + "=?";

    private static Logger log = Logger.getLogger(SQLUserDAO.class);
    private static final ConnectionPoolImpl pool;

    static {
        try {
            pool = ConnectionPoolImpl.create(url, login, password);
        } catch (SQLException e) {
            log.error(CON_POOL_CREATE_EXC);
            throw new ConnectionPoolException(e);
        }
    }

    @Override
    public User authentification(long contractNumber) throws DaoException {
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
        user.setId(Integer.parseInt(resultSet.getString(PARAMETER_ID)));
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
            log.error(e);
            throw new DaoException(e);

        } finally {
            close(pool, prepStatement, connection);
        }
        return result > 0;
    }

}