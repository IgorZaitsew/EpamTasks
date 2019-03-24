package by.tc.zaycevigor.controller.command.impl.admin;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.service.ClientService;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.ServiceProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.tc.zaycevigor.controller.command.util.Constant.ATTRIBUTE_USERS;
import static by.tc.zaycevigor.controller.command.util.Constant.PREV_REQUEST;
import static by.tc.zaycevigor.controller.command.util.JspPageName.*;

public class ShowUserListCommand implements Command {
    private static final Logger log = LogManager.getRootLogger();    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        ServiceProvider provider = ServiceProvider.getInstance();
        HttpSession session=request.getSession(true);
        session.setAttribute(PREV_REQUEST, url);
        ClientService clientService = provider.getClientService();
        List<User> userList;
        try {
            userList = clientService.getUserList();
            session.setAttribute(ATTRIBUTE_USERS, userList);
            response.sendRedirect(GO_TO_USER_LIST_COMMAND);
        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(ERROR_PAGE);
        }
    }
}
