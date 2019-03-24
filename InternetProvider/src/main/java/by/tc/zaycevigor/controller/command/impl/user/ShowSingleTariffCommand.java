package by.tc.zaycevigor.controller.command.impl.user;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.criteria.SearchCriteria;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.ServiceProvider;
import by.tc.zaycevigor.service.TariffService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.zaycevigor.controller.command.util.Constant.*;
import static by.tc.zaycevigor.controller.command.util.JspPageName.*;

public class ShowSingleTariffCommand implements Command {
    private static final Logger log = LogManager.getRootLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);

        HttpSession session = request.getSession(true);
        session.setAttribute(PREV_REQUEST, url);
        ServiceProvider provider = ServiceProvider.getInstance();
        TariffService tariffService = provider.getInternetService();
        SearchCriteria criteria = new SearchCriteria();
        criteria.setId(Integer.parseInt(request.getParameter(PARAMETER_ID)));
        try {
            request.setAttribute(ATTRIBUTE_SINGLE_TARIFF, tariffService.findSingle(criteria));
        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(ERROR_PAGE);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(SINGLE_TARIFF_PAGE);
        dispatcher.forward(request, response);
    }
}
