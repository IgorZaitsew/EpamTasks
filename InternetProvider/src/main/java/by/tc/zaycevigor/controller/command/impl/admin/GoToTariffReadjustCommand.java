package by.tc.zaycevigor.controller.command.impl.admin;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tc.zaycevigor.controller.command.util.Constant.*;
import static by.tc.zaycevigor.controller.command.util.JspPageName.TARIFF_READJUST_PAGE;

public class GoToTariffReadjustCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute(PREV_REQUEST, url);
        request.setAttribute(PARAMETER_TARIFF_ID, request.getParameter(PARAMETER_TARIFF_ID));
        request.setAttribute(PARAMETER_TARIFF_NAME, request.getParameter(PARAMETER_TARIFF_NAME));
        request.setAttribute(PARAMETER_TARIFF_SPEED, request.getParameter(PARAMETER_TARIFF_SPEED));
        request.setAttribute(PARAMETER_TARIFF_PRICE, request.getParameter(PARAMETER_TARIFF_PRICE));
        RequestDispatcher dispatcher = request.getRequestDispatcher(TARIFF_READJUST_PAGE);
        dispatcher.forward(request, response);
    }
}