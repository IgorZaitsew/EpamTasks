package by.tc.zaycevigor.entity;

import java.sql.Date;
import java.util.Objects;

public class User {
    private String passwordHash;
    private String login;
    private Tariff tariff;
    private boolean status;
    private String role;
    private double balance;
    private AdressData adressData;
    private String email;


    public User() {

    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Tariff getTariffId() {
        return tariff;
    }

    public void setTariffId(Tariff tariff) {
        this.tariff = tariff;
    }

    public boolean isBanStatus() {
        return status;
    }

    public void setBanStatus(boolean banStatus) {
        this.status = banStatus;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
