package by.tc.zaycevigor.dao;

import by.tc.zaycevigor.dao.impl.SQLUserDAO;

public class DAOProvider {
	private static final DAOProvider instance = new DAOProvider();
	
	private final UserDAO userDAO = new SQLUserDAO();
	private final TariffDAO tariffDAO = new by.tc.zaycevigor.dao.impl.SQLTariffDAO();
	
	private DAOProvider() {}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public TariffDAO getTariffDAO() {
		return tariffDAO;
	}
	
	public static DAOProvider getInstance() {
		return instance;
	}

}
