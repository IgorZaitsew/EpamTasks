package by.tc.zaycevigor.controller.command.impl.guest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.tc.zaycevigor.service.impl.ClientServiceImpl;

import java.io.IOException;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.entity.UserData;
import by.tc.zaycevigor.service.ClientService;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.ServiceProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import static by.tc.zaycevigor.controller.command.util.JspPageName.*;
import static by.tc.zaycevigor.controller.command.util.Constant.*;

public class UserRegistrationCommand implements Command {
    private static final Logger log = LogManager.getRootLogger();

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
                if(!((User)session.getAttribute(PARAMETER_USER)).getRole().equals(ADMIN_ROLE)) {
                    User user = new User(userData);
                    session.setAttribute(PARAMETER_USER, user);
                    response.sendRedirect(GO_TO_MAIN_PAGE);
                }else{
                    response.sendRedirect(GO_TO_USER_ADD_SUCCESS_PAGE);
                }
            } else {
                String errorMessage = ((ClientServiceImpl) service).getErrorMessage();
                response.sendRedirect(GO_TO_CONTRACT_REGISTRATION_PAGE + errorMessage);
            }
        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(GO_TO_ERROR_PAGE);
        }

    }

}
