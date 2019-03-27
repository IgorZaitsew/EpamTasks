package by.tc.zaycevigor.entity;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * It is used to display tariff data on a page with personal data of a user
 * or on a page with a description of a tariff
 */
public class Tariff implements Serializable {
    private int id;
    private String name;
    private BigDecimal price;
    private float speed;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
