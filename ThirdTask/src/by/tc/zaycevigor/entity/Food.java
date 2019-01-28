package by.tc.zaycevigor.entity;

import java.io.Serializable;
import java.util.*;

public class Food implements Serializable {

    private int id;
    private String name;
    private String imageURL;
    private String weight;
    private List<String> descrArray = new ArrayList<>();
    private List<Integer> priceArray = new ArrayList<>();

    public Food() {
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

    public void setDescrArray(List<String> descrArray) {
        this.descrArray = descrArray;
    }

    public List<String> getDescrArray() {
        return descrArray;
    }

    public void addDescr(String descr) {
        descrArray.add(descr);
    }

    public void setPriceArray(List<Integer> priceArray) {
        this.priceArray = priceArray;
    }

    public List<Integer> getPriceArray() {
        return priceArray;
    }

    public void addPrice(int price) {
        priceArray.add(price);
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
