package by.tc.zaycevigor.entity;


public class User {
    private String password;
    private String login;
    private Tariff tariff;
    private boolean status;
    private String role;
    private double balance;
    private AdressData adressData;
    private String email;


    public User() {

    }

    public User(UserData data) {
        login = data.getLogin();
        email = data.getEmail();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordHash) {
        this.password = passwordHash;
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
