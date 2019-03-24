package by.tc.zaycevigor.entity;

import by.tc.zaycevigor.dao.util.RandomGenerator;

public class ContractData {
    public static final int PASSWORD_SIZE = 10;
    public static final int CONTRACT_NUMBER_SIZE = 12;
    private long contractNumber;
    private String passportId;
    private int tariffId;
    private String name;
    private String surname;
    private String city;
    private String street;
    private String houseNumber;
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String generatePassword() {
        RandomGenerator randomGenerator = new RandomGenerator.RandomGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        return randomGenerator.generate(PASSWORD_SIZE);
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

    public long getContractNumber() {
        return contractNumber;
    }

    public long generateContractNumber() {
        RandomGenerator randomGenerator = new RandomGenerator.RandomGeneratorBuilder()
                .useDigits(true)
                .build();
        contractNumber = Long.parseLong(randomGenerator.generate(CONTRACT_NUMBER_SIZE));
        return contractNumber;
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
}
