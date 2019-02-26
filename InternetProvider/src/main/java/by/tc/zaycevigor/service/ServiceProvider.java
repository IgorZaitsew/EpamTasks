package by.tc.zaycevigor.service;

import by.tc.zaycevigor.entity.Tariff;
import by.tc.zaycevigor.service.impl.ClientServiceImpl;
import by.tc.zaycevigor.service.impl.InternetServiceImpl;

public class ServiceProvider {
	
	private static final ServiceProvider instance = new ServiceProvider();
	
	private ServiceProvider() {}
	
	private ClientService clientService = new ClientServiceImpl();
	private InternetService libService = new InternetServiceImpl();
		
	
	
	public ClientService getClientService() {
		return clientService;
	}
	
	public InternetService getLibraryService() {
		return libService;
	}
	
	public static ServiceProvider getInstance() {
		return instance;
	}

}
