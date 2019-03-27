package by.tc.zaycevigor.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *Used to store user data in a session
 */
public class User implements Serializable {
    private int id;

    /** Free or banned - depends on the state of balance of the user */
    private String status;

    /** Admin or user. Defines privileges */
    private String role;

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

    /**
     * Used when creating a user based on registration data and randomly generated contract number
     * @param data contains the contract number generated in the DAO method and the email entered by the user
     */
    public User(UserData data) {
        contractNumber = data.getContractNumber();
        email = data.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", role='" + role + '\'' +
                ", contractNumber=" + contractNumber +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                contractNumber == user.contractNumber &&
                Objects.equals(status, user.status) &&
                Objects.equals(role, user.role) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, role, contractNumber, email);
    }
}
