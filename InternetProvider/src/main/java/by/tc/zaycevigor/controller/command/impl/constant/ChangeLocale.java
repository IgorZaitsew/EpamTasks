package by.tc.zaycevigor.controller.command.impl.constant;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.tc.zaycevigor.controller.command.Command;

import static by.tc.zaycevigor.controller.command.util.Constant.*;

public class ChangeLocale implements Command {
    private static final String LOCALE = "locale";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newLocale;
        HttpSession session;

        newLocale = request.getParameter(LOCALE);
        session = request.getSession(true);
        session.setAttribute(LOCALE, newLocale);
        String url = (String) request.getSession(false).getAttribute(PREV_REQUEST);
        response.sendRedirect(url);
    }
}
