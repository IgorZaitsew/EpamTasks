package by.tc.zaycevigor.controller.command.impl.admin;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.entity.Contract;
import by.tc.zaycevigor.entity.criteria.SearchCriteria;
import by.tc.zaycevigor.service.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.zaycevigor.controller.command.util.Constant.*;
import static by.tc.zaycevigor.controller.command.util.JspPageName.*;

public class ShowUserInfoCommand implements Command {
    private static final Logger log = LogManager.getRootLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);

        HttpSession session = request.getSession(true);
        session.setAttribute(PREV_REQUEST, url);
        ServiceProvider provider = ServiceProvider.getInstance();
        TariffService tariffService = provider.getInternetService();
        ClientService clientService = provider.getClientService();
        ContractService contractService = provider.getContractService();
        long contractNumber = Long.parseLong(request.getParameter(PARAMETER_CONTRACT_NUMBER));
        try {
            Contract contract = contractService.getContract(contractNumber, request);
            if (contract != null) {
                SearchCriteria criteria = new SearchCriteria();
                criteria.setId(contract.getTariffId());
                request.setAttribute(ATTRIBUTE_SINGLE_TARIFF, tariffService.findSingle(criteria));
                request.setAttribute(ATTRIBUTE_CONTRACT, contract);
                request.setAttribute(ATTRIBUTE_USER, clientService.getUser(contractNumber, request));
                RequestDispatcher dispatcher = request.getRequestDispatcher(USER_INFO_PAGE);
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(GO_TO_ERROR_PAGE);
            }
        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(GO_TO_ERROR_PAGE);
        }
    }
}
