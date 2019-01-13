package main.java.by.tc.task01.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Appliance {

    private Map<String, String> appProps = new HashMap<>();
    private String appName;

    public Appliance(Map<String, String> appProps, String appName) {
        this.appProps = appProps;
        this.appName = appName;
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList();
        for (Map.Entry<String, String> entry : appProps.entrySet()) {
            list.add(entry.getKey() + "=" + entry.getValue());
        }
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        return appName + " : " + list.toString();
    }
}
