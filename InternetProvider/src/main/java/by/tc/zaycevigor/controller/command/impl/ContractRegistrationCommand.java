package by.tc.zaycevigor.controller.command.impl;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.entity.Contract;
import by.tc.zaycevigor.entity.ContractData;
import by.tc.zaycevigor.service.ContractService;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.ServiceProvider;
import by.tc.zaycevigor.service.impl.ContractServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.zaycevigor.controller.command.util.Constant.*;
import static by.tc.zaycevigor.controller.command.util.JspPageName.*;

public class ContractRegistrationCommand implements Command {
    private static Logger log = Logger.getLogger(ContractRegistrationCommand.class);

    private static final String SERVICE_EXC_LOG = "Adress data, email or passport data is not correct";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        HttpSession session = request.getSession(true);
        session.setAttribute(PREV_REQUEST, url);
        ServiceProvider provider = ServiceProvider.getInstance();
        ContractService service = provider.getContractService();

        ContractData contractData = new ContractData();
        contractData.setCity(request.getParameter(PARAMETER_CITY));
        contractData.setStreet(request.getParameter(PARAMETER_STREET));
        contractData.setHouseNumber(request.getParameter(PARAMETER_HOUSE_NUMBER));
        contractData.setPassportId(request.getParameter(PARAMETER_PASSPORT_ID));
        contractData.setName(request.getParameter(PARAMETER_NAME));
        contractData.setSurname(request.getParameter(PARAMETER_SURNAME));
        String email = request.getParameter(PARAMETER_EMAIL);
        contractData.setEmail(email);
        try {
            if (service.addContract(contractData, request)) {
                session.setAttribute(PARAMETER_CONTRACT, new Contract(contractData));
                session.setAttribute(PARAMETER_EMAIL, email);
                response.sendRedirect(USER_REGISTRATION_COMMAND);
            } else {
                String errorMessage = ((ContractServiceImpl) service).getErrorMessage();
                response.sendRedirect(GO_TO_CONTRACT_REGISTRATION_PAGE + errorMessage);
            }
        } catch (ServiceException e) {
            log.error(SERVICE_EXC_LOG, e);
            response.sendRedirect(GO_TO_ERROR_PAGE);
        }

    }
}
