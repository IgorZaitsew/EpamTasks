package by.tc.zaycevigor.entity;

public class Tariff {
    private int id;
    private String name;
    private float price;
    private float internetSpd;

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getInternetSpd() {
        return internetSpd;
    }

    public void setInternetSpd(float internetSpd) {
        this.internetSpd = internetSpd;
    }
}
