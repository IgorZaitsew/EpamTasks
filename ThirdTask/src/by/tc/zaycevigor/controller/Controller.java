package by.tc.zaycevigor.controller;

import by.tc.zaycevigor.dao.XMLDao;
import by.tc.zaycevigor.dao.XMLDaoException;
import by.tc.zaycevigor.dao.impl.DomXmlDao;
import by.tc.zaycevigor.dao.impl.SaxXmlDao;
import by.tc.zaycevigor.dao.impl.StaxXmlDao;
import by.tc.zaycevigor.entity.Food;
import by.tc.zaycevigor.logic.CommandException;
import by.tc.zaycevigor.logic.CommandHelper;
import by.tc.zaycevigor.logic.ICommand;

import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Controller")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String filePath = "E:\\GitFolder\\ThirdTask\\src\\resources\\menu_db.xml";

    public Controller() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("filename", filePath);
        request.setCharacterEncoding("UTF-8");
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
            xmlDao = DomXmlDao.getInstance();
        }
        try {
            request.setAttribute("list", cutArray(xmlDao.parse(filePath), request));
        } catch (
                XMLDaoException | XMLStreamException e) {
            e.printStackTrace();
        }


        if (dispatcher != null) {
            dispatcher.forward(request, response);
        } else {
            errorMessageDirectlyFromResponce(response);
        }

    }

    private static List<Food> cutArray(List<Food> foodList, HttpServletRequest request) {
        int start = Integer.parseInt(request.getParameter("startPos"));
        int count = Integer.parseInt(request.getParameter("count"));

        foodList = cutArrayByType(foodList, request.getParameter("foodType"));
        ArrayList<Food> newFoodList = new ArrayList<>();
        if (start >= 0 && start < foodList.size()) {
            for (int i = start; i < start + count; i++) {
                if (i < foodList.size()) {
                    newFoodList.add(foodList.get(i));
                }
            }

        }
        return newFoodList;
    }

    private static List<Food> cutArrayByType(List<Food> foodList, String type) {
        List<Food> newFoodList = new ArrayList<>();
        for (Food food : foodList) {
            if (food.getType().matches(type)) {
                newFoodList.add(food);
            }
        }
        return newFoodList;
    }

    private void errorMessageDirectlyFromResponce(HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        response.getWriter().println("ERROR");
    }
}

