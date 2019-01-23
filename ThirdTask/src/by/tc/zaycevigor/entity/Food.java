package by.tc.zaycevigor.entity;

import java.util.HashMap;
import java.util.Map;

public class Food {

    private int id;
    private String name;
    private Map<String, String> descrAndPrice = new HashMap();
    private String imageURL;
    private String weight;
    private String descr;

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

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Map<String, String> getDescrAndPrice() {
        return descrAndPrice;
    }

    public void setPrice(String price) {
        descrAndPrice.put(descr, price);
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

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("\nId: " + id + "\nImagePath: " + imageURL + "\n Name: " + name);
        for (String descr : descrAndPrice.keySet()) {
            sb.append("\n Description: ").append(descr).append("\n Price: ").append(descrAndPrice.get(descr));
        }
        return sb + "\n Weight: " + weight;
    }
}
