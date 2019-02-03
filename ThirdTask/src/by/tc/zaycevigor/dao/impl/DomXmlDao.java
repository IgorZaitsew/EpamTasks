package by.tc.zaycevigor.dao.impl;

import by.tc.zaycevigor.dao.XMLDao;
import by.tc.zaycevigor.entity.Food;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DomXmlDao implements XMLDao {
    private static ArrayList<Food> foodList = new ArrayList<>();
    private final static DomXmlDao instance = new DomXmlDao();

    public static XMLDao getInstance() {
        return instance;
    }

    @Override
    public ArrayList<Food> parse(String filePath) {
        File inputFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = dBuilder.parse(inputFile);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        doc.getDocumentElement().normalize();

        NodeList foodNodeList = doc.getElementsByTagName("Food");

        for (int i = 0; i < foodNodeList.getLength(); i++) {
            Node node = foodNodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Food food = new Food();
                Element element = (Element) node;

                food.setId(Integer.parseInt(element.getAttribute("id")));
                food.setType(element.getAttribute("type"));

                food.setName(element.getElementsByTagName("name").item(0).getTextContent());
                food.setImageURL(element.getElementsByTagName("image").item(0).getTextContent());
                for (int j = 0; j < element.getElementsByTagName("price").getLength(); j++) {
                    if (element.getElementsByTagName("price").item(j) != null) {
                        food.addPrice(Integer.parseInt(element.getElementsByTagName("price").item(j).getTextContent()));
                    }
                }
                for (int j = 0; j < element.getElementsByTagName("description").getLength(); j++) {
                    if (element.getElementsByTagName("description").item(j) != null) {
                        food.addDescr(element.getElementsByTagName("description").item(j).getTextContent());
                    }
                }
                food.setWeight(element.getElementsByTagName("portion").item(0).getTextContent());

                foodList.add(food);
            }
        }
        return foodList;
    }

}

