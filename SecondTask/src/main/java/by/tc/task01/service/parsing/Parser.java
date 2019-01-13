package main.java.by.tc.task01.service.parsing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.java.by.tc.task01.entity.criteria.SearchCriteria.Oven;

public class Parser {

    private static final String WRONG_PATH = "Wrong database path";
    private static final char DIV_NAME_AND_VALUES = ':';
    private static final char DIV_PARAM_NAME_AND_VALUE = '=';

    private String db;
    private String productName;
    boolean isEmpty = false;
    boolean isNull = false;
    int count = 0;

    public boolean parse(BufferedReader reader, String productName) throws IOException, ParserException {

        db = reader.readLine();
        if (db.isEmpty()) {
            isEmpty = true;
            return true;
        } else if (db == null) {
            isNull = true;
            return true;
        }
        isEmpty = false;
        isNull = false;
        int productNameIndex = db.indexOf(DIV_NAME_AND_VALUES);
        return db.substring(0, productNameIndex - 1).trim().matches(productName);
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean isNull() {
        return isNull;
    }

    public Map<String, String> getValues() {
        Map<String, String> appProps = new HashMap<>();
        db = db.substring(db.indexOf(DIV_NAME_AND_VALUES) + 1, db.length() - 1);
        String[] divDB = db.split(", ");
        for (String value : divDB) {
            appProps.put(value.substring(0, value.indexOf(DIV_PARAM_NAME_AND_VALUE)),
                    value.substring(value.indexOf(DIV_PARAM_NAME_AND_VALUE) + 1, value.length()));
        }
        return appProps;
    }

}
