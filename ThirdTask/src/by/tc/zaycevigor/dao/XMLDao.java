package by.tc.zaycevigor.dao;

import by.tc.zaycevigor.entity.Food;

import javax.xml.stream.XMLStreamException;
import java.util.List;

public interface XMLDao {
    List<Food> parse(String resourceName) throws XMLDaoException, XMLStreamException;
}
