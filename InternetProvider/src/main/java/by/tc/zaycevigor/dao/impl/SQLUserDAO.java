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

public class SQLUserDAO extends SqlDao implements UserDAO {
    private static final String QUERY_REGISTRATE_USER = "INSERT INTO users (login, password, email)" +
            " VALUES (?,?,?)";
    private static final String QUERY_CHECK_CREDENTIONALS = "SELECT * FROM users WHERE login=?";
    private static final String CON_POOL_CREATE_EXC = "Cannot create connection pool";
    private static final String RES_SET_CLOSE_EXC = "Cannot close result set";
    private static final String PREP_STAT_CLOSE_EXC = "Cannot close prepared statement";
    private static final String QUERY_CHECK_EMAIL_USAGE = "SELECT COUNT(email) FROM users WHERE email=?";
    private static final String QUERY_CHECK_LOGIN_USAGE = "SELECT COUNT(login) FROM users WHERE login=?";

    private static Logger log = Logger.getLogger(SqlDao.class);

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
    public User authentification(String userLogin, String userPassword) throws DaoException {
        Connection connection = pool.getConnection();

        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            prepStatement = connection.prepareStatement(QUERY_CHECK_CREDENTIONALS);
            prepStatement.setString(1, userLogin);
            resultSet = prepStatement.executeQuery();
            if (resultSet.next()) {
                if (BCrypt.checkpw(userPassword, resultSet.getString("password"))) {
                    user = createUser(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {

            close(resultSet, prepStatement, connection);
        }

        return user;
    }


    private User createUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setEmail(resultSet.getString("email"));
        user.setLogin(resultSet.getString("login"));
        return user;

    }


    private void close(ResultSet rs, PreparedStatement st, Connection connection) throws DaoException {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            log.error(RES_SET_CLOSE_EXC, e);
            throw new DaoException(e);
        }
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            log.error(PREP_STAT_CLOSE_EXC, e);
            throw new DaoException(e);
        }

        if (connection != null) {
            pool.releaseConnection(connection);
        }
    }


    @Override
    public boolean registration(UserData userData) throws DaoException {
        Connection connection;
        connection = pool.getConnection();

        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        if (!isUniqueDatas(userData.getLogin(), userData.getEmail(), connection)) {
            return false;
        }
        boolean result;
        try {
            prepStatement = connection.prepareStatement(QUERY_REGISTRATE_USER);

            prepStatement.setString(1, userData.getLogin());
            prepStatement.setString(2, BCrypt.hashpw(userData.getPassword(), BCrypt.gensalt()));
            prepStatement.setString(3, userData.getEmail());

            result = prepStatement.execute();
        } catch (SQLException e) {
            log.error(e);
            throw new DaoException(e);

        } finally {
            close(resultSet, prepStatement, connection);
        }
        return result;
    }

    private boolean isUniqueDatas(String login, String email, Connection connection) throws DaoException {
        return isUniqueData(email, QUERY_CHECK_EMAIL_USAGE, connection) &&
                isUniqueData(login, QUERY_CHECK_LOGIN_USAGE, connection);
    }

    private boolean isUniqueData(String data, String query, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, data);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(1) >= 0) {
                    return false;
                }
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DaoException(e);
        }
        return true;
    }

}
