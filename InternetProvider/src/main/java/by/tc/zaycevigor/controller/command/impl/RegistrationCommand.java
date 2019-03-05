package by.tc.zaycevigor.controller.command.impl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import java.io.IOException;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.entity.UserData;
import by.tc.zaycevigor.service.ClientService;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.ServiceProvider;

import static by.tc.zaycevigor.controller.JspPageName.*;

public class RegistrationCommand implements Command {
    private static Logger log = Logger.getLogger(RegistrationCommand.class);

    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String PARAMETER_EMAIL = "email";

    private static final String SERVICE_EXC_LOG = "Login, password or email is not correct";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute("prev_request", url);

        ServiceProvider provider = ServiceProvider.getInstance();
        ClientService service = provider.getClientService();

        UserData userData = new UserData();
        userData.setLogin(request.getParameter(PARAMETER_LOGIN));
        userData.setPassword(request.getParameter(PARAMETER_PASSWORD));
        userData.setEmail(request.getParameter(PARAMETER_EMAIL));

        HttpSession session = request.getSession(true);
        boolean isRegistrated;

        try {
            isRegistrated = service.registration(userData, request);
        } catch (ServiceException e) {
            log.error(SERVICE_EXC_LOG, e);
            throw new ServletException(e);
        }
        if (isRegistrated) {
            User user = new User(userData);
            session.setAttribute("user", user);
            response.sendRedirect(GO_TO_MAIN_PAGE);

        } else {
            request.setAttribute("error", "Login, Email or Password Error");
            response.sendRedirect(GO_TO_REGISTRATION_PAGE);
        }
    }

}
