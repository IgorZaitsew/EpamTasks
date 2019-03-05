package by.tc.zaycevigor.service;

import by.tc.zaycevigor.service.impl.ClientServiceImpl;
import by.tc.zaycevigor.service.impl.TariffServiceImpl;

public class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();

    private ServiceProvider() {
    }

    private ClientService clientService = new ClientServiceImpl();
    private TariffService tariffService = new TariffServiceImpl();


    public ClientService getClientService() {
        return clientService;
    }

    public TariffService getInternetService() {
        return tariffService;
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

}
