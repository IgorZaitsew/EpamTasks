package by.tc.zaycevigor.entity.criteria;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Criteria<E> {

    private Map<E, Object> criteria = new HashMap<>();
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
        return e.getClass().getName().replaceAll(".*SearchCriteria", "").replace('$', ' ').trim();
    }

    public Set<E> keySet() {
        return criteria.keySet();
    }
}
