/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.tc.zaycevigor.controller;

import by.tc.zaycevigor.entity.Food;
import by.tc.zaycevigor.service.parsing.SAXHandler;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Administrator
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     */
    public static void main(String[] args)
            throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SAXHandler saxh = new SAXHandler();

        parser.parse(new File("src/resources/menu_db.xml"), saxh);

        for (Food food : saxh.getFoodArray()) {
            System.out.println(food.toString());
        }
    }

}
