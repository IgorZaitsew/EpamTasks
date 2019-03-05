package by.tc.zaycevigor.entity.criteria;

import by.tc.zaycevigor.entity.Tariff;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class SearchCriteria {
    private static final String ANY_NAME_VALUE = "%";

    private int minPrice;
    private int maxPrice;
    private int minSpeed;
    private int maxSpeed;

    private String name = "Internet";

    public SearchCriteria() {
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getMaxPrice() {
        if (maxPrice == 0) {
            maxPrice = Integer.MAX_VALUE;
        }
        return maxPrice;
    }

    public int getMinSpeed() {
        return minSpeed;
    }

    public int getMaxSpeed() {
        if (maxSpeed == 0) {
            maxSpeed = Integer.MAX_VALUE;
        }
        return maxSpeed;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }


    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }


    public void setMinSpeed(int minSpeed) {
        this.minSpeed = minSpeed;
    }


    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        if (name == null) {
            name = ANY_NAME_VALUE;
        }
        return ANY_NAME_VALUE + name + ANY_NAME_VALUE;
    }

}
