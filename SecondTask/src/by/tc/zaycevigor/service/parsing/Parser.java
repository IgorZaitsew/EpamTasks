package by.tc.zaycevigor.service.parsing;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    private static final String DIV_NAME_AND_VALUES = ":";
    private static final String END_OF_LINE = ";";
    private static final String DIV_VALUES = ",";
    private static final String DIV_PARAM_NAME_AND_VALUE = "=";

    private String db;

    public boolean parse(BufferedReader reader) throws IOException, ParserException {
        db = "";
        while (!lineValidate(db)) {
            db = reader.readLine();
            if (db == null) {
                return false;
            }
        }
        return true;
    }

    public Map<String, String> getValues() {
        Map<String, String> appProps = new HashMap<>();
        db = db.substring(db.indexOf(DIV_NAME_AND_VALUES) + 1, db.length());
        db.replaceAll(END_OF_LINE, "");
        String[] divDB = db.split(",");
        int indexOfDiv;
        
        for (String value : divDB) {
            indexOfDiv = value.indexOf(DIV_PARAM_NAME_AND_VALUE);
            appProps.put(value.substring(0, indexOfDiv).trim(),
                    value.substring(indexOfDiv + 1, value.length()).trim());
        }
        return appProps;
    }

    public String productName() {
        return db.substring(0, db.indexOf(DIV_NAME_AND_VALUES)).trim();
    }

    private boolean lineValidate(String line) {
        if (line.isEmpty()) {
            return false;
        }
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
