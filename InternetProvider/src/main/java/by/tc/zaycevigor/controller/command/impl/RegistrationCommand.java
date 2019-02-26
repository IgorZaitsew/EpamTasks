package by.tc.zaycevigor.controller.command.impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.entity.UserData;
import by.tc.zaycevigor.service.ClientService;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.ServiceProvider;
import org.apache.log4j.Logger;

import java.io.IOException;

public class RegistrationCommand implements Command {
    private static Logger log = Logger.getLogger(RegistrationCommand.class);

    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String PARAMETER_EMAIL = "email";

    private static final String MAIN_PAGE = "/WEB-INF/pages/main.jsp";
    private static final String INDEX_PAGE = "/WEB-INF/pages/default.jsp";
    private static final String REGISTRATION_PAGE = "/WEB-INF/pages/registration.jsp";
    private static final String SERVICE_EXC_LOG = "Login, password or email is not correct";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter(PARAMETER_LOGIN);
        String password = request.getParameter(PARAMETER_PASSWORD);
        String email = request.getParameter(PARAMETER_EMAIL);

        ServiceProvider provider = ServiceProvider.getInstance();
        ClientService service = provider.getClientService();

        UserData user = new UserData();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);

        String page;
        boolean isRegistrated;

        try {
            isRegistrated = service.registration(user);

            if (isRegistrated) {
                request.setAttribute("user", user);
                page = MAIN_PAGE;
                String role = "admin";
                HttpSession session = request.getSession(true);
                session.setAttribute("role", role);
            } else {
                request.setAttribute("error", "Login, Email or Password Error");
                page = REGISTRATION_PAGE;
            }
        } catch (ServiceException e) {
            request.setAttribute("error", "Login, Email or Password Error");
            log.error(SERVICE_EXC_LOG);
            page = INDEX_PAGE;
        }

        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute("prev_request", url);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);

    }

}
