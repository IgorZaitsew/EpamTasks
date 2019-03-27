package by.tc.zaycevigor.controller.command.impl.user;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.entity.Contract;
import by.tc.zaycevigor.entity.Tariff;
import by.tc.zaycevigor.service.ContractService;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.ServiceProvider;
import by.tc.zaycevigor.service.TariffService;
import by.tc.zaycevigor.service.impl.ContractServiceImpl;
import by.tc.zaycevigor.service.impl.TariffServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

import static by.tc.zaycevigor.controller.command.util.Constant.*;
import static by.tc.zaycevigor.controller.command.util.JspPageName.GO_TO_ERROR_PAGE;
import static by.tc.zaycevigor.controller.command.util.JspPageName.GO_TO_PERSONAL_DATA_PAGE;

public class UpBalanceCommand implements Command {
    private static final Logger log = LogManager.getRootLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        HttpSession session = request.getSession(true);
        session.setAttribute(PREV_REQUEST, url);

        ServiceProvider provider = ServiceProvider.getInstance();
        ContractService contractService = provider.getContractService();
        BigDecimal amount = new BigDecimal(request.getParameter(PARAMETER_AMOUNT));
        Contract contract = (Contract) session.getAttribute(ATTRIBUTE_CONTRACT);
        try {
            if (contractService.upBalance(amount, contract.getBalance(), contract.getContractNumber(), request)) {
                contract.setBalance(amount.add(contract.getBalance()));
                response.sendRedirect(GO_TO_PERSONAL_DATA_PAGE);
            } else {
                response.sendRedirect(GO_TO_PERSONAL_DATA_PAGE + ((ContractServiceImpl) contractService).getErrorMessage());
            }
        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(GO_TO_ERROR_PAGE);
        }
    }
}
