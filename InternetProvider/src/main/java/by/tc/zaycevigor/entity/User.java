package by.tc.zaycevigor.entity;


public class User {
    private int id;
    private String status;
    private String role;
    private double balance;
    private long contractNumber;
    private String email;

    public User() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(long contractNumber) {
        this.contractNumber = contractNumber;
    }

    public User(UserData data) {
        contractNumber = data.getContractNumber();
        email = data.getEmail();
        balance=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getLogin() {
        return contractNumber;
    }

    public void setLogin(long contractNumber) {
        this.contractNumber = contractNumber;
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
