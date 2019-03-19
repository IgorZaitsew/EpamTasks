package by.tc.zaycevigor.controller.command.impl;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tc.zaycevigor.controller.command.util.JspPageName.MAIN_PAGE;
import static by.tc.zaycevigor.controller.command.util.Constant.PREV_REQUEST;


public class GoToMainPageCommand implements Command {
    private static Logger log = Logger.getLogger(UserRegistrationCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        request.getSession(false).setAttribute(PREV_REQUEST, url);
        request.getRequestDispatcher(MAIN_PAGE).forward(request, response);
    }
}
