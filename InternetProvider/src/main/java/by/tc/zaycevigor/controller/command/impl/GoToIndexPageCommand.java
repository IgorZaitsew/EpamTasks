package by.tc.zaycevigor.controller.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.service.ServiceProvider;

import static by.tc.zaycevigor.controller.JspPageName.DEFAULT_PAGE;

public class GoToIndexPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute("prev_request", url);
        RequestDispatcher dispatcher = request.getRequestDispatcher(DEFAULT_PAGE);
        dispatcher.forward(request, response);

    }

}
