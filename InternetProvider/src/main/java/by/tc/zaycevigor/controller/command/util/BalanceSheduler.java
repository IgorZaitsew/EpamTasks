package by.tc.zaycevigor.controller.command.util;

import java.math.BigDecimal;
import by.tc.zaycevigor.service.ContractService;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.ServiceProvider;
import by.tc.zaycevigor.service.TariffService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static by.tc.zaycevigor.controller.command.util.Constant.BALANCE_COEF;


public class BalanceSheduler implements Runnable {
    private static ContractService contractService;
    private static TariffService tariffService;
    private static final Logger log = LogManager.getRootLogger();


    static {
        ServiceProvider provider = ServiceProvider.getInstance();
        contractService = provider.getContractService();
        tariffService = provider.getInternetService();
    }

    public void writeOffMoney() {
        try {
            contractService.updateAllBalances(new BigDecimal(BALANCE_COEF),tariffService.getPriceList());
        } catch (ServiceException e) {
            log.error(e);
        }
    }

    @Override
    public void run() {
        writeOffMoney();
    }
}
