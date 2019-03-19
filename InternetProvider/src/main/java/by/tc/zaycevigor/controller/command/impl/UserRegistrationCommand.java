package by.tc.zaycevigor.controller.command.impl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.tc.zaycevigor.service.impl.ClientServiceImpl;
import org.apache.log4j.Logger;

import java.io.IOException;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.entity.UserData;
import by.tc.zaycevigor.service.ClientService;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.ServiceProvider;

import static by.tc.zaycevigor.controller.command.util.JspPageName.*;
import static by.tc.zaycevigor.controller.command.util.Constant.*;

public class UserRegistrationCommand implements Command {
    private static Logger log = Logger.getLogger(UserRegistrationCommand.class);


    private static final String SERVICE_EXC_LOG = "Contract number or email is not correct";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        HttpSession session = request.getSession(true);
        session.setAttribute(PREV_REQUEST, url);
        ServiceProvider provider = ServiceProvider.getInstance();
        ClientService service = provider.getClientService();

        UserData userData = new UserData();
        userData.setContractNumber((Long) session.getAttribute(PARAMETER_CONTRACT_NUMBER));
        userData.setEmail((String) session.getAttribute(PARAMETER_EMAIL));
        session.removeAttribute(PARAMETER_CONTRACT_NUMBER);
        session.removeAttribute(PARAMETER_EMAIL);
        try {
            if (service.registration(userData, request)) {
                User user = new User(userData);
                session.setAttribute(PARAMETER_USER, user);
                response.sendRedirect(GO_TO_MAIN_PAGE);
            } else {
                String errorMessage = ((ClientServiceImpl) service).getErrorMessage();
                response.sendRedirect(GO_TO_USER_REGISTRATION + errorMessage);
            }
        } catch (ServiceException e) {
            log.error(SERVICE_EXC_LOG, e);
            response.sendRedirect(GO_TO_ERROR_PAGE);
        }

    }

}
