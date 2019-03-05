package by.tc.zaycevigor.entity;


public class User {
    private int id;


    private String password;
    private String login;
    private String tariffName;
    private String status;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public String isBanStatus() {
        return status;
    }

    public void setBanStatus(String banStatus) {
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
