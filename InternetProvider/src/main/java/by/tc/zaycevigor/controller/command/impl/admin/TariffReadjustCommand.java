package by.tc.zaycevigor.controller.command.impl.admin;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.entity.TariffData;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.ServiceProvider;
import by.tc.zaycevigor.service.TariffService;
import by.tc.zaycevigor.service.impl.ClientServiceImpl;
import by.tc.zaycevigor.service.impl.TariffServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

import static by.tc.zaycevigor.controller.command.util.Constant.*;
import static by.tc.zaycevigor.controller.command.util.JspPageName.*;

public class TariffReadjustCommand implements Command {
    private static final Logger log = LogManager.getRootLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        HttpSession session = request.getSession(true);
        session.setAttribute(PREV_REQUEST, url);
        ServiceProvider provider = ServiceProvider.getInstance();

        TariffService tariffService = provider.getInternetService();
        try {
            int tariffId = Integer.parseInt(request.getParameter(PARAMETER_TARIFF_ID));
            TariffData tariffData = new TariffData();
            tariffData.setName(request.getParameter(PARAMETER_TARIFF_NAME));
            tariffData.setSpeed(Integer.parseInt(request.getParameter(PARAMETER_TARIFF_SPEED)));
            tariffData.setPrice(new BigDecimal(request.getParameter(PARAMETER_TARIFF_PRICE)));
            if (tariffService.readjustTariff(tariffId, tariffData, request)) {
                response.sendRedirect(GO_TO_TARIFF_LIST_PAGE);
            } else {
                String tariffErrorMessage = ((TariffServiceImpl) tariffService).getErrorMessage();
                response.sendRedirect(GO_TO_TARIFF_READJUST_PAGE + tariffErrorMessage);
            }
        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(GO_TO_ERROR_PAGE);
        }
    }
}
