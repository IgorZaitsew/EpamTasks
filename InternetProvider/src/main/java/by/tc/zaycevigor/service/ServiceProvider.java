package by.tc.zaycevigor.service;

import by.tc.zaycevigor.service.impl.ClientServiceImpl;
import by.tc.zaycevigor.service.impl.ContractServiceImpl;
import by.tc.zaycevigor.service.impl.TariffServiceImpl;

public class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();


    private ServiceProvider() {
    }

    private ClientService clientService = new ClientServiceImpl();
    private TariffService tariffService = new TariffServiceImpl();
    private ContractServiceImpl contractService = new ContractServiceImpl();


    public ClientService getClientService() {
        return clientService;
    }

    public TariffService getInternetService() {
        return tariffService;
    }

    public ContractService getContractService() {
        return contractService;
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

}
