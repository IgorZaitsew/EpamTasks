package by.tc.zaycevigor.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.service.ClientService;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.ServiceProvider;
import org.apache.log4j.Logger;

import static by.tc.zaycevigor.controller.JspPageName.*;

public class AuthorizationCommand implements Command {
    private static final Logger log = Logger.getLogger(AuthorizationCommand.class);

    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(PARAMETER_LOGIN);
        String password = request.getParameter(PARAMETER_PASSWORD);

        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute("prev_request", url);

        ServiceProvider provider = ServiceProvider.getInstance();
        ClientService service = provider.getClientService();

        HttpSession session = request.getSession(true);

        User user;

        try {
            user = service.authorization(login, password, request);

            if (user == null) {
                request.setAttribute("error", "login or password error");
                response.sendRedirect(GO_TO_AUTHORISATION_PAGE);
            } else {
                session.setAttribute("user", user);
                response.sendRedirect(GO_TO_MAIN_PAGE);
            }
        } catch (ServiceException e) {
            request.setAttribute("error", "Login or Password Error");
            log.error(e);
            response.sendRedirect(GO_TO_AUTHORISATION_PAGE);
        }
    }

}
