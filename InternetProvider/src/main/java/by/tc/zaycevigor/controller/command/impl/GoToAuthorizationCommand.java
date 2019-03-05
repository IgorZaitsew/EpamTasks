package by.tc.zaycevigor.controller.command.impl;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.tc.zaycevigor.controller.JspPageName.AUTHORIZATION_PAGE;

public class GoToAuthorizationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        request.getSession(false).setAttribute("previous_url", url);

        RequestDispatcher dispatcher = request.getRequestDispatcher(AUTHORIZATION_PAGE);
        dispatcher.forward(request, response);
    }
}
