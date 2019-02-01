package by.tc.zaycevigor.dao.impl;

import by.tc.zaycevigor.entity.Food;

import java.util.ArrayList;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;


public class SAXHandler extends DefaultHandler {

    private ArrayList<Food> foodList = new ArrayList();
    private Food food;
    private String thisElement;


    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        thisElement = qName;
        if (qName.equalsIgnoreCase("Food")) {
            food = new Food();
            food.setId(Integer.parseInt(attributes.getValue("id")));
        }
    }


    @Override
    public void endElement(String namespaceURI, String localName, String qName) {
        thisElement = "";
        if (qName.equalsIgnoreCase("Food")) {
            foodList.add(food);
            food = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (thisElement.equals("name")) {
            food.setName(new String(ch, start, length));
        }
        if (thisElement.equals("portion")) {
            food.setWeight(new String(ch, start, length));
        }
        if (thisElement.equals("image")) {
            food.setImageURL(new String(ch, start, length));
        }
        if (thisElement.equals("description")) {
            food.addDescr(new String(ch, start, length));
        }
        if (thisElement.equals("price")) {
            int price = Integer.parseInt(new String(ch, start, length));
            if (price != 0) {
                food.addPrice(price);
            }
        }
    }


    public ArrayList<Food> getFoodList() {
        return foodList;
    }
}