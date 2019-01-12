package main.java.by.tc.task01.dao.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.by.tc.task01.dao.ApplianceDAO;
import main.java.by.tc.task01.entity.Appliance;
import main.java.by.tc.task01.entity.Laptop;
import main.java.by.tc.task01.entity.Oven;
import main.java.by.tc.task01.entity.Refrigerator;
import main.java.by.tc.task01.entity.Speakers;
import main.java.by.tc.task01.entity.TabletPC;
import main.java.by.tc.task01.entity.VacuumCleaner;
import main.java.by.tc.task01.entity.criteria.Criteria;
import main.java.by.tc.task01.service.parsing.ParserException;
import static main.java.by.tc.task01.service.parsing.Parser.parse;

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
                    String[] values = new String[dividedLine.length];
                    for (int i = 0; i < dividedLine.length; i++) {
                        values[i] = dividedLine[i][1];
                    }
                    
                    if (compareCriteriaWithDB(criteria, dividedLine)) {
                        return DBtoAppl(values, productName);
                    }
                }
            }

        } catch (ApplianceDAOImplException ex) {
            Logger.getLogger(ApplianceDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
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

    private <E> boolean compareCriteriaWithDB(Criteria<E> criteria, String[][] db) {
        label:
        for (E key : criteria.keySet()) {
            for (String[] dbElem : db) {
                if (key.toString().matches(dbElem[0])) {
                    String value = criteria.getValue(key).toString();
                    if (!value.trim().matches(dbElem[1].trim())) {
                        return false;
                    } else {
                        continue label;
                    }

                }
            }
        }

        return true;
    }

    private Appliance DBtoAppl(String[] values, String productName) throws ApplianceDAOImplException {
        Appliance appl;
        switch (productName) {
            case "Oven":
                appl = new Oven();
                break;
            case "Laptop":
                appl = new Laptop();
                break;
            case "Refrigerator":
                appl = new Refrigerator();
                break;
            case "VacuumCleaner":
                appl = new VacuumCleaner();
                break;
            case "TabletPC":
                appl = new TabletPC();
                break;
            case "Speakers":
                appl = new Speakers();
                break;
            default:
                throw new ApplianceDAOImplException();
        }
        appl.setData(values);
        return appl;
    }
}
