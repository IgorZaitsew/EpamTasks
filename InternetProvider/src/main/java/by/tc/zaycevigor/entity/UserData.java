package by.tc.zaycevigor.entity;

/**
 * Stores the data entered by the user for transfer to the DAO registration method
 */
public class UserData {
    long contractNumber;
    String email;
    String status;
    String role;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(long contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
