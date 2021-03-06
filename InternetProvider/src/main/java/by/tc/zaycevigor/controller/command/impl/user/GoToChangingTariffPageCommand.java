package by.tc.zaycevigor.controller.command.impl.user;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tc.zaycevigor.controller.command.util.Constant.*;
import static by.tc.zaycevigor.controller.command.util.JspPageName.CHANGE_TARIFF_PAGE;
import static by.tc.zaycevigor.dao.util.Constant.PARAMETER_ID;

public class GoToChangingTariffPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        request.setAttribute(TARIFF_ID,request.getParameter(TARIFF_ID));
        request.setAttribute(PARAMETER_TARIFF_NAME,request.getParameter(PARAMETER_TARIFF_NAME));

        request.getSession(true).setAttribute(PREV_REQUEST, url);
        RequestDispatcher dispatcher = request.getRequestDispatcher(CHANGE_TARIFF_PAGE);
        dispatcher.forward(request, response);
    }
}