package by.tc.zaycevigor.dao.impl;

import by.tc.zaycevigor.dao.XMLDao;
import by.tc.zaycevigor.dao.XMLDaoException;
import by.tc.zaycevigor.entity.Food;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public final class SaxXmlDao implements XMLDao {
    private final static SaxXmlDao instance = new SaxXmlDao();
    private static final String IO_EXC_MSG = "File path is wrong";
    private static final String SAX_EXC_MSG = "Sax exception";
    private static final String PARSER_CONF_XC_MSG = "Parser configuration exception";

    public static XMLDao getInstance() {
        return instance;
    }

    @Override
    public List<Food> parse(String resourceName) throws XMLDaoException {
        List<Food> foodList;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
        try {
            parser = factory.newSAXParser();
            SAXHandler saxHanl = new SAXHandler();
            parser.parse(new File("E:\\GitFolder\\ThirdTask\\src\\resources\\menu_db.xml"), saxHanl);
            foodList = saxHanl.getFoodList();
        } catch (SAXException e) {
            e.printStackTrace();
            throw new XMLDaoException(SAX_EXC_MSG, e);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw new XMLDaoException(PARSER_CONF_XC_MSG, e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new XMLDaoException(IO_EXC_MSG, e);
        }
        return foodList;
    }
}
