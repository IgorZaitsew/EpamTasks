package by.tc.zaycevigor.entity;

public class Contract {
    private long contractNumber;
    private float balance;
    private String passportId;
    private int tariffId;
    private String name;
    private String surname;
    private String city;
    private String street;
    private String houseNumber;

    public Contract() {

    }

    public Contract(ContractData data) {
        contractNumber = data.getContractNumber();
        name = data.getName();
        surname = data.getSurname();
        city = data.getCity();
        street = data.getStreet();
        houseNumber = data.getHouseNumber();
        passportId = data.getPassportId();
    }

    public long getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(long contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
