package by.tc.zaycevigor.controller.command.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.CommandException;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.Tariff;
import by.tc.zaycevigor.entity.criteria.SearchCriteria;
import by.tc.zaycevigor.service.ServiceProvider;
import by.tc.zaycevigor.service.TariffService;
import org.apache.log4j.Logger;

import static by.tc.zaycevigor.controller.JspPageName.DEFAULT_PAGE;

public class GoToIndexPageCommand implements Command {
    private static final Logger log = Logger.getLogger(GoToIndexPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        String url = CreatorFullURL.create(request);
        ServiceProvider provider = ServiceProvider.getInstance();

        request.getSession(true).setAttribute("prev_request", url);
        TariffService bookService = provider.getInternetService();
        List<Tariff> tariffList;
        try {
            tariffList = bookService.find(new SearchCriteria());
        } catch (DaoException e) {
            log.error(e);
            throw new CommandException(e);
        }

        request.setAttribute("tariffs", tariffList);


        RequestDispatcher dispatcher = request.getRequestDispatcher(DEFAULT_PAGE);
        dispatcher.forward(request, response);

    }

}
