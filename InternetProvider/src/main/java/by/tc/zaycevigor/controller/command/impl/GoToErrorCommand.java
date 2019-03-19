package by.tc.zaycevigor.controller.command.impl;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tc.zaycevigor.controller.command.util.Constant.PREV_REQUEST;
import static by.tc.zaycevigor.controller.command.util.JspPageName.ERROR_PAGE;

public class GoToErrorCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute(PREV_REQUEST, url);
        RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE);
        dispatcher.forward(request, response);

    }
}
