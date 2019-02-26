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
import by.tc.zaycevigor.controller.command.Command;

public class AuthorizationCommand implements Command {

    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";

    private static final String MAIN_PAGE = "/WEB-INF/pages/main.jsp";
    private static final String INDEX_PAGE = "/WEB-INF/pages/default.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login;
        String password;

        login = request.getParameter(PARAMETER_LOGIN);
        password = request.getParameter(PARAMETER_PASSWORD);

        ServiceProvider provider = ServiceProvider.getInstance();
        ClientService service = provider.getClientService();

        User user = null;
        String page;
        try {
            user = service.authorization(login, password);

            if (user == null) {
                request.setAttribute("error", "login or password error");
                page = INDEX_PAGE;
            } else {
                request.setAttribute("user", user);
                page = MAIN_PAGE;
                String role = "admin";
                HttpSession session = request.getSession(true);
                session.setAttribute("role", role);
            }
        } catch (ServiceException e) {
            request.setAttribute("error", "Login or Password Error");
            // log
            page = INDEX_PAGE;
        }

        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute("prev_request", url);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);

    }

}
