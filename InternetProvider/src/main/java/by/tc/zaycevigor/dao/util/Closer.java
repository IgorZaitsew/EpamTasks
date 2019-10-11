package by.tc.zaycevigor.dao.util;

import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.dao.impl.ConnectionPoolImpl;

import java.sql.*;

public class Closer {
    private static final String RES_SET_CLOSE_EXC = "Cannot close result set";
    private static final String PREP_STAT_CLOSE_EXC = "Cannot close prepared statement";

    private Closer() {
    }

    public static void close(ConnectionPoolImpl pool, ResultSet rs, PreparedStatement st, Connection connection) throws DaoException {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        if (connection != null) {
            pool.releaseConnection(connection);
        }
    }

    public static void close(ConnectionPoolImpl pool, PreparedStatement st, Connection connection) throws DaoException {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        if (connection != null) {
            pool.releaseConnection(connection);
        }
    }


    public static void close(ConnectionPoolImpl pool, ResultSet rs, Statement st, Connection connection) throws DaoException {

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        if (connection != null) {
            pool.releaseConnection(connection);
        }
    }

    public static void close(ConnectionPoolImpl pool, Statement st, Connection connection) throws DaoException {

        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        if (connection != null) {
            pool.releaseConnection(connection);
        }
    }
}
