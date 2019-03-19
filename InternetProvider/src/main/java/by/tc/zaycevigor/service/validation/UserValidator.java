package by.tc.zaycevigor.service.validation;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.tc.zaycevigor.entity.ContractData.CONTRACT_NUMBER_SIZE;
import static by.tc.zaycevigor.entity.ContractData.PASSWORD_SIZE;
import static by.tc.zaycevigor.service.util.ErrorMessages.*;

public class UserValidator  {
    public static final Pattern EMAIL =
            Pattern.compile("^[A-Z]+[A-Z0-9_]+?\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern PASSWORD = Pattern.compile("^[A-я0-9_.]{"+PASSWORD_SIZE+"}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern CONTRACT_NUMBER = Pattern.compile("^[0-9]{"+CONTRACT_NUMBER_SIZE+"}$", Pattern.CASE_INSENSITIVE);
    public static final String ERROR_NAME="error";
    private StringBuilder errorMessage;
    public boolean validate(HttpServletRequest request,long contractNumber) {

        errorMessage = new StringBuilder();

        boolean isCorrect = isContractNumberCorrect(contractNumber);
        String incorrectDataMessage = errorMessage.toString();
        if (incorrectDataMessage.length() != 0) {
            request.setAttribute(ERROR_NAME, incorrectDataMessage);
        }
        return isCorrect;
    }

    public boolean validate( HttpServletRequest request,long contractNumber, String password, String email) {

        errorMessage = new StringBuilder();

        boolean isCorrect = isContractNumberCorrect(contractNumber) & isPasswordCorrect(password) & isEmailCorrect(email);
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
            return true;
        } else {
            errorMessage.append(EMAIL_ERROR);
            return false;
        }
    }

    public StringBuilder getErrorMessage() {
        return errorMessage;
    }
}
