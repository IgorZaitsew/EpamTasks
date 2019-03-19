package by.tc.zaycevigor.service.validation;

import by.tc.zaycevigor.entity.ContractData;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.tc.zaycevigor.entity.ContractData.CONTRACT_NUMBER_SIZE;
import static by.tc.zaycevigor.entity.ContractData.PASSWORD_SIZE;
import static by.tc.zaycevigor.service.util.ErrorMessages.*;

public class ContractValidator {
    public static final Pattern GEOGRAPHICAL = Pattern.compile("^[A-Я][а-я]+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern NAMING = Pattern.compile("^[A-Я][а-я]{1,10}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern HOUSE_NUMBER = Pattern.compile("^[0-9]{1,3}[A-Я]*$", Pattern.CASE_INSENSITIVE);
    public static final Pattern PASSPORT_ID = Pattern.compile("^[A-Z]{2}[0-9]{7}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern PASSWORD = Pattern.compile("^[A-я0-9_.]{" + PASSWORD_SIZE + "}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern EMAIL =
            Pattern.compile("^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$", Pattern.CASE_INSENSITIVE);
    public static final Pattern CONTRACT_NUMBER = Pattern.compile("^[0-9]{" + CONTRACT_NUMBER_SIZE + "}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern TARIFF_ID = Pattern.compile("^[0-9]{1,3}$", Pattern.CASE_INSENSITIVE);
    public static final String ERROR = "error";
    private StringBuilder errorMessage;

    public boolean validate(HttpServletRequest request, ContractData data) {
        errorMessage = new StringBuilder();

        boolean isCorrect = isCorrect(data.getPassportId(), PASSPORT_ID, PASSWORD_ERROR) & isCorrect(data.getName(), NAMING, NAME_ERROR) &
                isCorrect(data.getSurname(), NAMING, SURNAME_ERROR) & isCorrect(data.getCity(), GEOGRAPHICAL, CITY_ERROR) &
                isCorrect(data.getStreet(), GEOGRAPHICAL, STREET_ERROR) & isCorrect(data.getHouseNumber(), HOUSE_NUMBER, HOUSE_NUMBER_ERROR) &
                isCorrect(data.getEmail(), EMAIL, EMAIL_ERROR);
        String incorrectDataMessage = errorMessage.toString();
        if (incorrectDataMessage.length() != 0) {
            request.setAttribute(ERROR, incorrectDataMessage);
        }
        return isCorrect;
    }

    public boolean validate(HttpServletRequest request, long contractNumber, String password) {
        errorMessage = new StringBuilder();
        boolean isCorrect = isCorrect(String.valueOf(contractNumber), CONTRACT_NUMBER, CONTRACT_NUMBER_ERROR) &
                isCorrect(password, PASSWORD, PASSWORD_ERROR);
        String incorrectDataMessage = errorMessage.toString();
        if (incorrectDataMessage.length() != 0) {
            request.setAttribute(ERROR, incorrectDataMessage);
        }
        return isCorrect;
    }

    public boolean validate(HttpServletRequest request, int tariffId) {
        errorMessage = new StringBuilder();
        boolean isCorrect = isCorrect(String.valueOf(tariffId), TARIFF_ID, TARIFF_ID_ERROR);
        String incorrectDataMessage = errorMessage.toString();
        if (incorrectDataMessage.length() != 0) {
            request.setAttribute(ERROR, incorrectDataMessage);
        }
        return isCorrect;
    }

    private boolean isCorrect(String string, Pattern pattern, String error) {
        Matcher m = pattern.matcher(String.valueOf(string));
        if (m.find()) {
            return true;
        } else {
            errorMessage.append(error);
            return false;
        }
    }


    public StringBuilder getErrorMessage() {
        return errorMessage;
    }
}
