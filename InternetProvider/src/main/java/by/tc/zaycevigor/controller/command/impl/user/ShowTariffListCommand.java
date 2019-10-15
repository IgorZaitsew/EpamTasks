package by.tc.zaycevigor.controller.command.impl.user;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.entity.Tariff;
import by.tc.zaycevigor.entity.criteria.SearchCriteria;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.ServiceProvider;
import by.tc.zaycevigor.service.TariffService;
import by.tc.zaycevigor.service.impl.TariffServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.tc.zaycevigor.controller.command.util.Constant.*;
import static by.tc.zaycevigor.controller.command.util.JspPageName.*;

public class ShowTariffListCommand implements Command {
    private static final Logger log = Logger.getLogger(ShowTariffListCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute(PREV_REQUEST, url);

        ServiceProvider provider = ServiceProvider.getInstance();
        TariffService tariffService = provider.getInternetService();
        List<Tariff> tariffList;

        int id = Integer.parseInt(request.getParameter(PARAMETER_ID));
        try {
            tariffList = tariffService.find(id, TARIFFS_COUNT_FOR_PAGINATION, FIRST_TARIFF_ID, request);
            request.setAttribute(PARAMETER_FIRST_TARIFF_ID, id);
        } catch (ServiceException e) {
            log.error(((TariffServiceImpl) tariffService).getErrorMessage(), e);
            response.sendRedirect(GO_TO_ERROR_PAGE);
            return;
        }
        request.setAttribute(ATTRIBUTE_TARIFFS, tariffList);
        RequestDispatcher dispatcher = request.getRequestDispatcher(TARIFF_PAGE);
        dispatcher.forward(request, response);
    }

}
