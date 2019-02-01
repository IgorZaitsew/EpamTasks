package by.tc.zaycevigor.controller;

import by.tc.zaycevigor.dao.XMLDao;
import by.tc.zaycevigor.dao.XMLDaoException;
import by.tc.zaycevigor.dao.impl.SAXHandler;
import by.tc.zaycevigor.dao.impl.SaxXmlDao;
import by.tc.zaycevigor.dao.impl.StaxXmlDao;
import by.tc.zaycevigor.entity.Food;
import by.tc.zaycevigor.logic.CommandException;
import by.tc.zaycevigor.logic.CommandHelper;
import by.tc.zaycevigor.logic.ICommand;

import org.apache.log4j.xml.DOMConfigurator;
import org.xml.sax.SAXException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Controller")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Controller() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DOMConfigurator.configure("E:\\GitFolder\\ThirdTask\\src\\resources\\log4j.xml");
        String commandName = request.getParameter(RequestParametrName.COMMAND_NAME);
        ICommand command = CommandHelper.getInstance().getCommand(commandName);
        String page;
        try {
            page = command.execute(request);
        } catch (CommandException e) {
            page = JspPageName.ERROR_PAGE;
        } catch (Exception e) {
            page = JspPageName.ERROR_PAGE;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        XMLDao xmlDao;

        if (request.getParameter("parser").equals("SAX")) {
            xmlDao = SaxXmlDao.getInstance();
        } else if (request.getParameter("parser").equals("STaX")) {
            xmlDao = StaxXmlDao.getInstance();
        } else {
            xmlDao = StaxXmlDao.getInstance();
        }


        try {
            request.setAttribute("list", xmlDao.parse("E:\\GitFolder\\ThirdTask\\src\\resources\\menu_db.xml"));
        } catch (
                XMLDaoException e) {
            e.printStackTrace();
        } catch (
                XMLStreamException e) {
            e.printStackTrace();
        }


        if (dispatcher != null) {
            dispatcher.forward(request, response);
        } else {
            errorMessageDirectlyFromResponce(response);
        }

    }

    private void errorMessageDirectlyFromResponce(HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        response.getWriter().println("ERROR");
    }
}

