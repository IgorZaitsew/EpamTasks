package by.tc.zaycevigor.controller.command.impl.admin;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.entity.UserData;
import by.tc.zaycevigor.service.ClientService;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.ServiceProvider;
import by.tc.zaycevigor.service.impl.ClientServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

import static by.tc.zaycevigor.controller.command.util.Constant.*;
import static by.tc.zaycevigor.controller.command.util.JspPageName.*;

public class UserReadjustCommand implements Command {
    private static final Logger log = LogManager.getRootLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        HttpSession session = request.getSession(true);
        session.setAttribute(PREV_REQUEST, url);
        ServiceProvider provider = ServiceProvider.getInstance();

        ClientService clientService = provider.getClientService();
        try {
            int tariffId = Integer.parseInt(request.getParameter(PARAMETER_ID));
            UserData userData = new UserData();
            userData.setEmail(request.getParameter(PARAMETER_EMAIL));
            userData.setContractNumber(Long.parseLong(request.getParameter(PARAMETER_CONTRACT_NUMBER)));
            userData.setStatus(request.getParameter(PARAMETER_STATUS));
            userData.setRole(request.getParameter(PARAMETER_ROLE));
            if (clientService.readjustUser(tariffId, userData, request)) {
                response.sendRedirect(SHOW_USER_LIST);
            } else {
                String tariffErrorMessage = ((ClientServiceImpl) clientService).getErrorMessage();
                response.sendRedirect(GO_TO_USER_READJUST_PAGE + tariffErrorMessage);
            }
        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(GO_TO_ERROR_PAGE);
        }
    }
}