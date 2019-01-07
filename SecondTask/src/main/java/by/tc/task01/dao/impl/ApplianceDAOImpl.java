package main.java.by.tc.task01.dao.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import main.java.by.tc.task01.dao.ApplianceDAO;
import main.java.by.tc.task01.entity.Appliance;
import main.java.by.tc.task01.entity.criteria.Criteria;
import main.java.by.tc.task01.entity.criteria.SearchCriteria;
import main.java.by.tc.task01.entity.criteria.SearchCriteria.Oven;
import main.java.by.tc.task01.service.exceptions.ParserException;
import static main.java.by.tc.task01.service.parsing.Parser.parse;
import static main.java.by.tc.task01.service.validation.Validator.criteriaValidator;

public class ApplianceDAOImpl implements ApplianceDAO {

    private static final String DIV_VALUES = ",";
    private static final String DIV_PARAM_NAME_AND_VALUE = "=";
    private static final String DB_PATH = "src/main/resources/appliances_db.txt";

    @Override
    public <E> Appliance find(Criteria<E> criteria) throws IOException, ParserException {
        String productName = criteria.productName();
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_PATH))) {
            String line;
            while ((line = parse(reader)) != null) {
                if (line.replaceAll(" .*", "").matches(productName) && lineValidate(line)) {
                    String[][] dividedLine = divider(line);
                    for (String[] appliance : dividedLine) {
                        String key = appliance[0];              
                        String value = criteria.getValue(key).toString();
                    }
                }
            }
        }

        return null;
    }

    private String[][] divider(String line) {
        line = line.replaceAll(".* : ", "").replace(";", "");
        String[] firstDiv = line.split(", ");
        String[][] secondDiv = new String[firstDiv.length][];

        for (int i = 0; i < firstDiv.length; i++) {
            secondDiv[i] = firstDiv[i].split("=");
        }
        return secondDiv;
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
}
