package by.tc.zaycevigor.controller.command.impl.guest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.entity.Contract;
import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.service.ClientService;
import by.tc.zaycevigor.service.ContractService;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.ServiceProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static by.tc.zaycevigor.controller.command.util.JspPageName.*;
import static by.tc.zaycevigor.controller.command.util.Constant.*;

public class AuthorizationCommand implements Command {
    private static final Logger log = LogManager.getRootLogger();
    private static final String DATA_ERROR = "error";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long contractNumber = Long.parseLong(request.getParameter(PARAMETER_CONTRACT_NUMBER));
        String password = request.getParameter(PARAMETER_PASSWORD);
        String url = CreatorFullURL.create(request);
        HttpSession session = request.getSession(true);
        session.setAttribute(PREV_REQUEST, url);

        ServiceProvider provider = ServiceProvider.getInstance();
        ClientService service = provider.getClientService();
        ContractService contractService = provider.getContractService();



        try {
            Contract contract = contractService.contractAuthentification(contractNumber, password, request);
            if (contract == null) {
                String errorMessage = CONTRACT_NOT_FOUND;
                response.sendRedirect(GO_TO_AUTHORISATION_PAGE + errorMessage);
            } else {
                User user = service.getUser(contractNumber, request);
                session.setAttribute(PARAMETER_USER, user);
                session.setAttribute(PARAMETER_CONTRACT, contract);
                response.sendRedirect(GO_TO_MAIN_PAGE);
            }
        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(GO_TO_ERROR_PAGE);
        }
    }
}
