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
            if (!appProps.containsKey(key.toString()) || !appProps.get(key.toString()).trim().equals(criteria.getValue(key).toString())) {
                return false;
            }
        }
        return true;
    }
}
