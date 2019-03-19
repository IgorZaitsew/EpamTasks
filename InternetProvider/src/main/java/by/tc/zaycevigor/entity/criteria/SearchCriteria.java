package by.tc.zaycevigor.entity.criteria;

import by.tc.zaycevigor.entity.Contract;
import by.tc.zaycevigor.entity.User;

import java.util.Map;

import static by.tc.zaycevigor.controller.command.util.Constant.*;

public class SearchCriteria {
    private static final String ANY_VALUE = "%";

    private int minPrice;
    private int maxPrice;
    private int minSpeed;
    private int maxSpeed;

    private int id;
    private String name;

    public SearchCriteria() {
    }

    public SearchCriteria(Contract contract) {
        id = contract.getTariffId();
    }

    public SearchCriteria(int id, String name, Map<String, Integer> valuesMap) {
        this.id = id;
        this.name = name;
        minSpeed = valuesMap.get(PARAMETER_TARIFF_MIN_SPEED);
        maxSpeed = valuesMap.get(PARAMETER_TARIFF_MAX_SPEED);
        minPrice = valuesMap.get(PARAMETER_TARIFF_MIN_PRICE);
        maxPrice = valuesMap.get(PARAMETER_TARIFF_MAX_PRICE);
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
            name = ANY_VALUE;
        }
        return ANY_VALUE + name + ANY_VALUE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
