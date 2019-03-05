package by.tc.zaycevigor.dao.impl;

import by.tc.zaycevigor.dao.ConnectionPool;

import by.tc.zaycevigor.dao.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPoolImpl implements ConnectionPool {
    public static final String MAX_SIZE_REACHED_EXC = "Maximum pool size reached, no available connections";
    public static final String CREATE_CON_EXC = "Cannot create connection";

    private static Logger log = Logger.getLogger(SqlDao.class);
    private String url;
    private String user;
    private String password;
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 5;
    private static int MAX_POOL_SIZE = 10;

    private ConnectionPoolImpl(String url, String user, String password, List<Connection> pool) {
        this.url = url;
        this.user = user;
        this.password = password;
        connectionPool = pool;
    }

    public static ConnectionPoolImpl create(
            String url, String user,
            String password) throws SQLException {

        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }
        return new ConnectionPoolImpl(url, user, password, pool);
    }

    @Override
    public Connection getConnection() {

        if (connectionPool.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                try {
                    connectionPool.add(createConnection(url, user, password));
                } catch (SQLException e) {
                    log.error(CREATE_CON_EXC);
                    throw new ConnectionPoolException(CREATE_CON_EXC, e);
                }
            } else {
                log.error(MAX_SIZE_REACHED_EXC);
                throw new ConnectionPoolException(MAX_SIZE_REACHED_EXC);
            }
        }
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public String getUser() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    private static Connection createConnection(
            String url, String user, String password)
            throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }


}
