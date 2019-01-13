package main.java.by.tc.task01.dao.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import main.java.by.tc.task01.dao.ApplianceDAO;
import main.java.by.tc.task01.entity.Appliance;
import main.java.by.tc.task01.entity.criteria.Criteria;
import main.java.by.tc.task01.service.parsing.ParserException;
import main.java.by.tc.task01.service.parsing.Parser;

public class ApplianceDAOImpl implements ApplianceDAO {

    private static final String DIV_VALUES = ",";
    private static final String DIV_PARAM_NAME_AND_VALUE = "=";
    private static final String DB_PATH = "src/main/resources/appliances_db.txt";

    @Override
    public <E> Appliance find(Criteria<E> criteria) throws IOException, ParserException {
        String productName = criteria.productName();
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_PATH))) {
            String line;
            Parser parser = new Parser();

            while (!parser.isNull()) {
                if (!parser.parse(reader, productName) || parser.isEmpty()) {
                    continue;
                }
                Map<String, String> appProps = parser.getValues();
                if (compareCriteriaWithAppProps(criteria, appProps)) {
                    return new Appliance(appProps, productName);
                }

            }
        }
        return null;
    }

    private boolean lineValidate(String line) {
        return findCount(line, DIV_PARAM_NAME_AND_VALUE) == (findCount(line, DIV_VALUES) + 1);
    }

    private int findCount(String string, String searchable) {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.substring(i, i + 1).matches(searchable)) {
                count++;
            }
        }
        return count;
    }

    private <E> boolean compareCriteriaWithAppProps(Criteria<E> criteria, Map<String, String> appProps) {
        for (E key : criteria.keySet()) {
            if (!appProps.containsKey(key.toString()) || !appProps.get(key.toString()).trim().equals(criteria.getValue(key).toString())) {
                return false;
            }
        }
        return true;
    }
}
