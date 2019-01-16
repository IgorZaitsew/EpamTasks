package by.tc.zaycevigor.dao.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import by.tc.zaycevigor.dao.ApplianceDAO;
import by.tc.zaycevigor.entity.Appliance;
import by.tc.zaycevigor.entity.criteria.Criteria;
import by.tc.zaycevigor.service.parsing.ParserException;
import by.tc.zaycevigor.service.parsing.Parser;

public class ApplianceDAOImpl implements ApplianceDAO {

    private static final String DB_PATH = "src/main/resources/appliances_db.txt";

    @Override
    public <E> Appliance find(Criteria<E> criteria) throws IOException, ParserException {
        String productName = criteria.productName();
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_PATH))) {
            Parser parser = new Parser();

            while (parser.parse(reader)) {
                if (parser.productName().matches(productName)) {
                    Map<String, String> appProps = parser.getValues();
                    if (compareCriteriaWithAppProps(criteria, appProps)) {
                        return new Appliance(appProps, productName);
                    }
                }
            }

        }
        return null;
    }

    private <E> boolean compareCriteriaWithAppProps(Criteria<E> criteria, Map<String, String> appProps) {
        for (E key : criteria.keySet()) {
            if (!appProps.containsKey(key.toString()) || !appProps.get(key.toString()).equals(criteria.getValue(key).toString())) {
                return false;
            }
        }
        return true;
    }
}
