package by.tc.zaycevigor.entity;

import java.math.BigDecimal;

/**
 * Stores the tariff data entered by the administrator for transfer to the DAO add method
 */
public class TariffData {
    private String name;
    private BigDecimal price;
    private int speed;

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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
