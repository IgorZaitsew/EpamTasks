package main.java.by.tc.task01.entity;

import java.util.HashMap;
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
        StringBuffer sb = new StringBuffer();
        sb.append(appName).append(" : ");
        for (Map.Entry<String, String> entry : appProps.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(",");
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb + "";
    }
}
