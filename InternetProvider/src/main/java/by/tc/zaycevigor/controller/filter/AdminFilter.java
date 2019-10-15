package by.tc.zaycevigor.controller.filter;

import by.tc.zaycevigor.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.zaycevigor.controller.command.util.Constant.ADMIN_ROLE;
import static by.tc.zaycevigor.controller.command.util.Constant.PARAMETER_USER;
import static by.tc.zaycevigor.controller.command.util.JspPageName.MAIN_PAGE;

public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        if (session.getAttribute(PARAMETER_USER) == null && !((User) session.getAttribute(PARAMETER_USER)).getRole().equals(ADMIN_ROLE)) {
            request.getRequestDispatcher(MAIN_PAGE).forward(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

}