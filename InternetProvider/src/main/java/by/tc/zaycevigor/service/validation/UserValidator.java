package by.tc.zaycevigor.service.validation;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.tc.zaycevigor.entity.ContractData.CONTRACT_NUMBER_SIZE;
import static by.tc.zaycevigor.entity.ContractData.PASSWORD_SIZE;
import static by.tc.zaycevigor.service.util.ErrorMessages.*;

public class UserValidator {
    public static final Pattern EMAIL =
            Pattern.compile("^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$", Pattern.CASE_INSENSITIVE);
    public static final Pattern PASSWORD = Pattern.compile("^[A-я0-9_.]{" + PASSWORD_SIZE + "}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern CONTRACT_NUMBER = Pattern.compile("^[0-9]{" + CONTRACT_NUMBER_SIZE + "}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern ROLE = Pattern.compile("^[admin]|[user]$", Pattern.CASE_INSENSITIVE);
    public static final Pattern STATUS = Pattern.compile("^[banned]|[free]$", Pattern.CASE_INSENSITIVE);
    public static final String ERROR_NAME = "error";
    private StringBuilder errorMessage;

    public boolean validate(HttpServletRequest request, long contractNumber) {

        errorMessage = new StringBuilder();

        boolean isCorrect = isContractNumberCorrect(contractNumber);
        String incorrectDataMessage = errorMessage.toString();
        if (incorrectDataMessage.length() != 0) {
            request.setAttribute(ERROR_NAME, incorrectDataMessage);
        }
        return isCorrect;
    }

    public boolean validate(HttpServletRequest request, long contractNumber, String email) {

        errorMessage = new StringBuilder();

        boolean isCorrect = isContractNumberCorrect(contractNumber) & isEmailCorrect(email);
        String incorrectDataMessage = errorMessage.toString();
        if (incorrectDataMessage.length() != 0) {
            request.setAttribute(ERROR_NAME, incorrectDataMessage);
        }
        return isCorrect;
    }

    public boolean validate(HttpServletRequest request, long contractNumber, String email,String role,String status) {

        errorMessage = new StringBuilder();

        boolean isCorrect = isContractNumberCorrect(contractNumber) & isEmailCorrect(email) & isRoleCorrect(role) &
                isStatusCorrect(status);
        String incorrectDataMessage = errorMessage.toString();
        if (incorrectDataMessage.length() != 0) {
            request.setAttribute(ERROR_NAME, incorrectDataMessage);
        }
        return isCorrect;
    }

    private boolean isContractNumberCorrect(long contractNumber) {
        Matcher m = CONTRACT_NUMBER.matcher(String.valueOf(contractNumber));
        if (m.find()) {
            return true;
        } else {
            errorMessage.append(CONTRACT_NUMBER_ERROR);
            return false;
        }
    }

    private boolean isPasswordCorrect(String password) {
        Matcher m = PASSWORD.matcher(password);
        if (m.find()) {
            return true;
        } else {
            errorMessage.append(PASSWORD_ERROR);
            return false;
        }
    }

    private boolean isEmailCorrect(String email) {
        Matcher m = EMAIL.matcher(email);
        if (m.find()) {
            System.out.println("true");
            return true;
        } else {
            System.out.println("false");
            errorMessage.append(EMAIL_ERROR);
            return false;
        }
    }

    private boolean isRoleCorrect(String role) {
        Matcher m = ROLE.matcher(role);
        if (m.find()) {
            return true;
        } else {
            errorMessage.append(USER_ROLE_ERROR);
            return false;
        }
    }

    private boolean isStatusCorrect(String status) {
        Matcher m = STATUS.matcher(status);
        if (m.find()) {
            return true;
        } else {
            errorMessage.append(USER_STATUS_ERROR);
            return false;
        }
    }

    public StringBuilder getErrorMessage() {
        return errorMessage;
    }
}
