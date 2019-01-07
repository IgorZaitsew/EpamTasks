package main.java.by.tc.task01.entity.criteria;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

public class Criteria<E> {

    private Map<E, Object> criteria = new HashMap<E, Object>();
    private E e;

    public void add(E searchCriteria, Object value) {
        criteria.put(searchCriteria, value);
        e = searchCriteria;
    }

    public int size() {
        return criteria.size();
    }

    public Object getValue(E key) {
        return criteria.get(key);
    }

    public String productName() {
        return e.getClass().getName().replaceAll(".*SearchCriteria", "").replace('$', ' ').replaceAll(" ", "");
    }
}
