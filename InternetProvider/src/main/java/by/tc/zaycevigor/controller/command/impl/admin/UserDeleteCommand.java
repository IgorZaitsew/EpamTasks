package by.tc.zaycevigor.controller.command.impl.admin;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.service.ClientService;
import by.tc.zaycevigor.service.ContractService;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.ServiceProvider;
import by.tc.zaycevigor.service.impl.ClientServiceImpl;
import by.tc.zaycevigor.service.impl.ContractServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.zaycevigor.controller.command.util.Constant.*;
import static by.tc.zaycevigor.controller.command.util.JspPageName.*;

public class UserDeleteCommand implements Command {

    private static final Logger log = LogManager.getRootLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        HttpSession session = request.getSession(true);
        session.setAttribute(PREV_REQUEST, url);
        ServiceProvider provider = ServiceProvider.getInstance();
        ClientService clientService = provider.getClientService();
        ContractService contractService = provider.getContractService();
        try {
            long contractNumber = Long.parseLong(request.getParameter(PARAMETER_CONTRACT_NUMBER));
            if (clientService.deleteUser(contractNumber) & contractService.deleteContract(contractNumber)) {
                User user = (User) session.getAttribute(PARAMETER_USER);
                if (contractNumber == user.getContractNumber()) {
                    response.sendRedirect(EXIT_COMMAND);
                } else {
                    response.sendRedirect(GO_TO_USER_DELETE_SUCCESS);
                }
            } else {
                String contractErrorMessage = ((ContractServiceImpl) contractService).getErrorMessage();
                String userErrorMessage = ((ClientServiceImpl) clientService).getErrorMessage();
                response.sendRedirect(GO_TO_ERROR_PAGE + contractErrorMessage + userErrorMessage);
            }
        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(GO_TO_ERROR_PAGE);
        }
    }
}