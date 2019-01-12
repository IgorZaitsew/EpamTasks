package main.java.by.tc.task01.main;

import main.java.by.tc.task01.entity.Appliance;

public class PrintApplianceInfo {

    private static final String APPL_NOT_FOUND_MSG = "Sorry, we can't find appliance by your request";

    public static void print(Appliance appliance) {
        if (appliance != null) {
            System.out.println(appliance.toString());
        } else {
            System.out.println(APPL_NOT_FOUND_MSG);
        }
    }
}
