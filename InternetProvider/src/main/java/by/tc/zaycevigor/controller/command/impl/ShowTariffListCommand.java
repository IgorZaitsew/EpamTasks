package by.tc.zaycevigor.controller.command.impl;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.Tariff;
import by.tc.zaycevigor.entity.criteria.SearchCriteria;
import by.tc.zaycevigor.service.ServiceProvider;
import by.tc.zaycevigor.service.TariffService;
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
import static by.tc.zaycevigor.controller.command.util.JspPageName.TARIFF_PAGE;

public class ShowTariffListCommand implements Command {
    private static Logger log = Logger.getLogger(ShowPersonalDataCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        ServiceProvider provider = ServiceProvider.getInstance();
        request.getSession(true).setAttribute(PREV_REQUEST, url);
        TariffService tariffService = provider.getInternetService();
        List<Tariff> tariffList = new ArrayList<>();

        int id = Integer.parseInt(request.getParameter(PARAMETER_ID));
        String name = request.getParameter(PARAMETER_TARIFF_NAME);
        Map<String, Integer> valuesMap = new HashMap<>();
        valuesMap.put(PARAMETER_TARIFF_MIN_PRICE, Integer.parseInt(request.getParameter(PARAMETER_TARIFF_MIN_PRICE)));
        valuesMap.put(PARAMETER_TARIFF_MAX_PRICE, Integer.parseInt(request.getParameter(PARAMETER_TARIFF_MAX_PRICE)));
        valuesMap.put(PARAMETER_TARIFF_MIN_SPEED, Integer.parseInt(request.getParameter(PARAMETER_TARIFF_MIN_SPEED)));
        valuesMap.put(PARAMETER_TARIFF_MAX_SPEED, Integer.parseInt(request.getParameter(PARAMETER_TARIFF_MAX_SPEED)));

        try {
            tariffList = tariffService.find(new SearchCriteria(id, name, valuesMap));
        } catch (DaoException e) {
            log.error(e);
        }
        request.setAttribute(ATTRIBUTE_TARIFFS, tariffList);
        RequestDispatcher dispatcher = request.getRequestDispatcher(TARIFF_PAGE);
        dispatcher.forward(request, response);
    }

}
