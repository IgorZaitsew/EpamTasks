package by.tc.zaycevigor.controller.command.impl.admin;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.zaycevigor.controller.command.util.Constant.*;
import static by.tc.zaycevigor.controller.command.util.JspPageName.USER_DELETE_PAGE;

public class GoToUserDeletePageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute(PREV_REQUEST, url);

        request.setAttribute(PARAMETER_CONTRACT_NUMBER,request.getParameter(PARAMETER_CONTRACT_NUMBER));
        RequestDispatcher dispatcher = request.getRequestDispatcher(USER_DELETE_PAGE);
        dispatcher.forward(request, response);
    }
}
