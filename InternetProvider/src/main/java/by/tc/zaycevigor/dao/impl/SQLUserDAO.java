package by.tc.zaycevigor.dao.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import by.tc.zaycevigor.dao.DaoException;
import by.tc.zaycevigor.dao.UserDAO;
import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.entity.UserData;
import org.apache.log4j.Logger;

public class SQLUserDAO extends SqlDao implements UserDAO {
    private static Logger log = Logger.getLogger(SqlDao.class);
    private static final String QUERY_CHECK_CREDENTIONALS = "SELECT * FROM users WHERE login=? and password=?";


    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public User authentification(String userLogin, String userPassword) throws DaoException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        User user = null;

        try {
            con = DriverManager.getConnection(url, login, password);
            st = con.prepareStatement(QUERY_CHECK_CREDENTIONALS);

            st.setString(1, userLogin);
            st.setString(2, getHash(userPassword));

            rs = st.executeQuery();

            if (rs.next()) {
                user = createUser(rs);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(rs, st, con);
        }

        return user;
    }


    private User createUser(ResultSet rs) throws SQLException {
        User user = new User();

        user.setEmail(rs.getString("email"));
        user.setLogin(rs.getString("login"));
        user.setPasswordHash(rs.getString("login"));

        return user;

    }


    private void close(ResultSet rs, PreparedStatement st, Connection con) throws DaoException {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            log.error("Error while closing ResultSet", e);
            throw new DaoException(e);
        }
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            log.error("Error while closing PreparedStatement", e);
            throw new DaoException(e);
        }
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            log.error("Error while closing Connection", e);
            throw new DaoException(e);
        }
    }


    @Override
    public void registration(UserData userData) throws DaoException {

        Connection con = null;
        PreparedStatement preparedStmt = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, login, password);

            String query = " insert into users (login, password, email)"
                    + " values (?, ?, ?)";

            preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, userData.getLogin());
            preparedStmt.setString(2, getHash(userData.getPassword()));
            preparedStmt.setString(3, userData.getEmail());

            preparedStmt.execute();
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e);
            throw new DaoException(e);

        }
    }

    private String getHash(String passwordToHash) throws DaoException {
        String generatedPassword = null;

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);

            byte[] hashedPassword = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hashedPassword.length; i++) {
                sb.append(Integer.toString((hashedPassword[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error(e);
            throw new DaoException(e);
        }
        return generatedPassword;
    }

}
