package by.tc.zaycevigor.service.parsing;

import by.tc.zaycevigor.entity.Food;
import java.util.ArrayList;
import java.util.Arrays;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {

    private ArrayList<Food> foodArray = new ArrayList();
    private Food food = new Food();
    private String thisElement;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse XML...");
    }

    @Override
    public void startElement(String uri, String localName,
            String qName, Attributes attributes) throws SAXException {
        thisElement = qName;
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        thisElement = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (thisElement.equals("id")) {
            food = new Food();
            food.setId(new Integer(new String(ch, start, length)));
        }
        if (thisElement.equals("name")) {
            food.setName(new String(ch, start, length));
        }
        if (thisElement.equals("portion")) {
            food.setWeight(new String(ch, start, length));
            foodArray.add(food);
        }
        if (thisElement.equals("image")) {
            food.setImageURL(new String(ch, start, length));
        }
        if (thisElement.equals("description")) {
            food.setDescr(new String(ch, start, length));
        }
        if (thisElement.equals("price")) {
            food.setPrice(new String(ch, start, length));
        }
    }

    @Override
    public void endDocument() {
        System.out.println("Stop parse XML...");
    }

    public ArrayList<Food> getFoodArray() {
        return foodArray;
    }
}
