package by.tc.zaycevigor.dao.impl;

import by.tc.zaycevigor.dao.XMLDao;
import by.tc.zaycevigor.dao.XMLDaoException;
import by.tc.zaycevigor.entity.Food;
import org.apache.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.events.*;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;


public class StaxXmlDao implements XMLDao {
    private final static StaxXmlDao instance = new StaxXmlDao();
    private static final String FILE_NOT_FOUND_EXC = "File path is wrong";

    public static XMLDao getInstance() {
        return instance;
    }

    @Override
    public List<Food> parse(String resourceName) throws XMLDaoException, XMLStreamException {
        List<Food> foodList = new ArrayList<>();
        Food food = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        XMLEventReader xmlEventReader;
        try {
            xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(resourceName));

        } catch (XMLStreamException e) {
            e.printStackTrace();
            throw new XMLDaoException("", e);
        } catch (FileNotFoundException e) {
            throw new XMLDaoException(FILE_NOT_FOUND_EXC, e);
        }
        while (xmlEventReader.hasNext()) {
            XMLEvent xmlEvent = null;
            try {
                xmlEvent = xmlEventReader.nextEvent();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
            if (xmlEvent.isStartElement()) {

                StartElement startElement = xmlEvent.asStartElement();
                if (startElement.getName().getLocalPart().equals("Food")) {
                    food = new Food();
                    Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                    Attribute typeAttr = startElement.getAttributeByName(new QName("type"));

                    if (idAttr != null) {
                        food.setId(Integer.parseInt(idAttr.getValue()));
                        food.setType(typeAttr.getValue());
                    }
                } else if (startElement.getName().getLocalPart().equals("image")) {
                    xmlEvent = xmlEventReader.nextEvent();
                    food.setImageURL(xmlEvent.asCharacters().getData());
                } else if (startElement.getName().getLocalPart().equals("name")) {
                    xmlEvent = xmlEventReader.nextEvent();
                    food.setName(xmlEvent.asCharacters().getData());
                } else if (startElement.getName().getLocalPart().equals("description")) {
                    xmlEvent = xmlEventReader.nextEvent();
                    food.addDescr(xmlEvent.asCharacters().getData());
                } else if (startElement.getName().getLocalPart().equals("price")) {
                    xmlEvent = xmlEventReader.nextEvent();

                    String data = xmlEvent.asCharacters().getData();
                    if (!data.isEmpty()) {
                        food.addPrice(Integer.parseInt(data));
                    }
                } else if (startElement.getName().getLocalPart().equals("portion")) {
                    xmlEvent = xmlEventReader.nextEvent();
                    food.setWeight(xmlEvent.asCharacters().getData());
                }
            }

            if (xmlEvent.isEndElement()) {
                EndElement endElement = xmlEvent.asEndElement();
                if (endElement.getName().getLocalPart().equals("Food")) {
                    foodList.add(food);
                }
            }
        }

        return foodList;
    }
}
