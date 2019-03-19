package by.tc.zaycevigor.controller.command.impl;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.Contract;
import by.tc.zaycevigor.service.ContractService;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.ServiceProvider;
import by.tc.zaycevigor.service.impl.ContractServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.zaycevigor.controller.command.util.Constant.*;
import static by.tc.zaycevigor.controller.command.util.JspPageName.*;

public class ChangeTariffCommand implements Command {

    private static Logger log = Logger.getLogger(ChangeTariffCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        HttpSession session = request.getSession(true);
        session.setAttribute(PREV_REQUEST, url);
        ServiceProvider provider = ServiceProvider.getInstance();
        ContractService contractService = provider.getContractService();
        try {
            int tariffId = Integer.parseInt(request.getParameter(TARIFF_ID));
            Contract contract = (Contract) session.getAttribute(PARAMETER_CONTRACT);
            if (contractService.changeTariff(contract.getContractNumber(), tariffId, request)) {
                contract.setTariffId(tariffId);
                session.removeAttribute(PARAMETER_CONTRACT);
                session.setAttribute(PARAMETER_CONTRACT, contract);
                response.sendRedirect(GO_TO_CHANGE_RESULT);
            } else {
                String errorMessage = ((ContractServiceImpl) contractService).getErrorMessage();
                response.sendRedirect(GO_TO_ERROR_PAGE + errorMessage);
            }
        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(GO_TO_ERROR_PAGE);
        }
    }
}
