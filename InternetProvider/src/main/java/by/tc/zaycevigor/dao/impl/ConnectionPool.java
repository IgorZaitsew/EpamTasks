package by.tc.zaycevigor.dao.impl;

public class ConnectionPool {
	private static final ConnectionPool instance = new ConnectionPool();
	
	private ConnectionPool() {}
	
	
	
	public static ConnectionPool getInstance() {
		return instance;
	}

}
