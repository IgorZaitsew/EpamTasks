package by.tc.zaycevigor.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Contract {
    private static final int BASIC_BALANCE=0;
    private long contractNumber;
    private BigDecimal balance;
    private String passportId;
    private int tariffId;
    private String name;
    private String surname;
    private String city;
    private String street;
    private String houseNumber;

    {
        balance=new BigDecimal(BASIC_BALANCE);
    }
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
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

    @Override
    public String toString() {
        return "Contract{" +
                "contractNumber=" + contractNumber +
                ", balance=" + balance +
                ", passportId='" + passportId + '\'' +
                ", tariffId=" + tariffId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return contractNumber == contract.contractNumber &&
                tariffId == contract.tariffId &&
                balance.equals(contract.balance) &&
                Objects.equals(passportId, contract.passportId) &&
                Objects.equals(name, contract.name) &&
                Objects.equals(surname, contract.surname) &&
                Objects.equals(city, contract.city) &&
                Objects.equals(street, contract.street) &&
                Objects.equals(houseNumber, contract.houseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractNumber, balance, passportId, tariffId, name, surname, city, street, houseNumber);
    }
}
