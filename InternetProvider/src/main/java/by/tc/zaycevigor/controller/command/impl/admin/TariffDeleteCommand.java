package by.tc.zaycevigor.controller.command.impl.admin;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.service.*;
import by.tc.zaycevigor.service.impl.ClientServiceImpl;
import by.tc.zaycevigor.service.impl.ContractServiceImpl;
import by.tc.zaycevigor.service.impl.TariffServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Queue;

import static by.tc.zaycevigor.controller.command.util.Constant.*;
import static by.tc.zaycevigor.controller.command.util.JspPageName.*;

public class TariffDeleteCommand implements Command {
    private static final Logger log = LogManager.getRootLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        HttpSession session = request.getSession(true);
        session.setAttribute(PREV_REQUEST, url);
        ServiceProvider provider = ServiceProvider.getInstance();

        TariffService tariffService = provider.getInternetService();
        ContractService contractService = provider.getContractService();
        ClientService clientService = provider.getClientService();
        try {
            int tariffId = Integer.parseInt(request.getParameter(PARAMETER_TARIFF_ID));
            Queue<Long> contractNumbers = contractService.findTariffUsers(tariffId);
            contractService.resetTariffUsers(tariffId);
            int changedTariffCount = 0;
            if (contractNumbers != null) {
                clientService.sendWarnings(contractNumbers);
                changedTariffCount = contractNumbers.size();
            }
            session.setAttribute(CHANGED_TARIFF_COUNT, changedTariffCount);
            if (tariffService.deleteTariff(tariffId)) {
                response.sendRedirect(GO_TO_TARIFF_DELETE_SUCCESS);
            } else {
                response.sendRedirect(GO_TO_ERROR_PAGE);
            }
        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(GO_TO_ERROR_PAGE);
        }
    }
}
