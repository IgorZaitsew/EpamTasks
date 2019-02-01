package by.tc.zaycevigor.logic.impl;

import by.tc.zaycevigor.controller.JspPageName;
import by.tc.zaycevigor.controller.RequestParametrName;
import by.tc.zaycevigor.dao.XMLDAOFactory;
import by.tc.zaycevigor.dao.XMLDao;
import by.tc.zaycevigor.dao.XMLDaoException;
import by.tc.zaycevigor.entity.Food;
import by.tc.zaycevigor.logic.CommandException;
import by.tc.zaycevigor.logic.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.XMLStreamException;
import java.util.List;

public class DoAnythingCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        XMLDao dao;
        try {
            dao = XMLDAOFactory.getInstance().getDAO(XMLDAOFactory.DAOType.SAX);
            List<Food> info =
                    dao.parse(request.getParameter(RequestParametrName.FILE_NAME));
            request.setAttribute(RequestParametrName.SIMPLE_INFO, info);
            page = JspPageName.MENU_PAGE;
        } catch (XMLDaoException | XMLStreamException e) {
            throw new CommandException("can't get DAO", e);
        }
        return page;
    }

}
