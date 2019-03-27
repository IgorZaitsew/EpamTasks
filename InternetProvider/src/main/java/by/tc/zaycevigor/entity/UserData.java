package by.tc.zaycevigor.entity;

/**
 * Stores the data entered by the user for transfer to the DAO registration method
 */
public class UserData {
    long contractNumber;
    String email;

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
