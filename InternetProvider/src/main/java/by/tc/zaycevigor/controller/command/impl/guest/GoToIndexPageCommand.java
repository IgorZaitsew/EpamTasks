package by.tc.zaycevigor.controller.command.impl.guest;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.tc.zaycevigor.controller.command.Command;

import by.tc.zaycevigor.controller.command.util.CreatorFullURL;

import static by.tc.zaycevigor.controller.command.util.JspPageName.DEFAULT_PAGE;
import static by.tc.zaycevigor.controller.command.util.Constant.*;

public class GoToIndexPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute(PREV_REQUEST, url);
        RequestDispatcher dispatcher = request.getRequestDispatcher(DEFAULT_PAGE);
        dispatcher.forward(request, response);
    }

}
