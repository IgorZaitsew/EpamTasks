package by.tc.zaycevigor.servlet;

import by.tc.zaycevigor.dao.SAXHandler;
import by.tc.zaycevigor.entity.Food;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Controller")
public class Controller extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Food> foodList = new ArrayList<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
        try {
            parser = factory.newSAXParser();
            SAXHandler saxh = new SAXHandler();
            parser.parse(new File("E:\\GitFolder\\ThirdTask\\src\\resources\\menu_db.xml"), saxh);
            foodList = saxh.getFoodArray();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        request.setAttribute("list", foodList);
        request.getRequestDispatcher("WEB-INF/views/home.jsp").forward(request, response);
    }
}
