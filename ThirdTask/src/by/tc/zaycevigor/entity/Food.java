package by.tc.zaycevigor.entity;

import java.io.Serializable;
import java.util.*;

public class Food implements Serializable {

    private int id;
    private String name;
    private String imageURL;
    private String weight;
    private String type;

    private String description = " ";
    private String price = " ";

    public Food() {
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void addDescr(String descr) {
        this.description = description.concat(descr + "</br>");
    }

    public String getPrice() {
        return price;
    }

    public void addPrice(int price) {
        this.price = this.price.concat(price + "</br>");
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
