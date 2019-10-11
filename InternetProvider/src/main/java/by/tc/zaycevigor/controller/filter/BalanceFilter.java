package by.tc.zaycevigor.controller.filter;

import by.tc.zaycevigor.entity.Contract;
import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.entity.criteria.SearchCriteria;
import by.tc.zaycevigor.service.ContractService;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.ServiceProvider;
import by.tc.zaycevigor.service.impl.ClientServiceImpl;
import by.tc.zaycevigor.service.impl.ContractServiceImpl;
import by.tc.zaycevigor.service.validation.ContractValidator;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

import static by.tc.zaycevigor.controller.command.util.Constant.PARAMETER_CONTRACT;
import static by.tc.zaycevigor.dao.util.Constant.*;

public class BalanceFilter implements Filter {
    public static final double LOW_BALANCE_VALUE = 0.01;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(PARAMETER_USER);

        if (!user.getRole().equals(ADMIN_ROLE)) {
            try {

                BigDecimal balance = ServiceProvider.getInstance().getContractService().getBalance(user.getContractNumber());
                Contract contract = (Contract) session.getAttribute(PARAMETER_CONTRACT);
                SearchCriteria searchCriteria = new SearchCriteria();
                searchCriteria.setId(contract.getTariffId());
                BigDecimal tariffCoeff = ServiceProvider.getInstance().getInternetService().
                        findSingle(searchCriteria).getPrice();
                int isBalanceLow = new BigDecimal(LOW_BALANCE_VALUE).
                        compareTo(balance.subtract(tariffCoeff.multiply(new BigDecimal(LOW_BALANCE_VALUE)).setScale(2, BigDecimal.ROUND_DOWN)));

                if (!user.getStatus().equals(USER_STATUS_BANNED)) {
                    if (isBalanceLow >= 0) {
                        user.setStatus(USER_STATUS_BANNED);
                        ServiceProvider.getInstance().getClientService().setStatus(user.getContractNumber(), USER_STATUS_BANNED);
                    }
                } else {
                    if (isBalanceLow < 0) {
                        user.setStatus(USER_STATUS_CLEAR);
                        ServiceProvider.getInstance().getClientService().setStatus(user.getContractNumber(), USER_STATUS_CLEAR);
                    }
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {
    }
}
