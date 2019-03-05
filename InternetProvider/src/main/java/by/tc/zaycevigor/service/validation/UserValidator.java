package by.tc.zaycevigor.service.validation;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    public static final Pattern EMAIL =
            Pattern.compile("^[A-Z0-9._]+?@[A-Z0-9_]+?\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern PASSWORD = Pattern.compile("^[A-я0-9_.]{6,16}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern LOGIN = Pattern.compile("^[A-ZА-я]{4,12}$", Pattern.CASE_INSENSITIVE);
    private static final String ERROR_LOGIN = "Login is incorrect&";
    private static final String ERROR_PASSWORD = "Password is incorrect&";
    private static final String ERROR_EMAIL = "Email is incorrect&";

    private static StringBuffer errorMessage;

    public static boolean isCorrect(String login, String password, HttpServletRequest request) {

        errorMessage = new StringBuffer();

        boolean isCorrect = isLoginCorrect(login) & isPasswordCorrect(password);
        String incorrectDataMessage = errorMessage.toString();
        if (incorrectDataMessage.length() != 0) {
            request.setAttribute("error", incorrectDataMessage);
        }
        return isCorrect;
    }

    public static boolean isCorrect(String login, String password, String email, HttpServletRequest request) {

        errorMessage = new StringBuffer();

        boolean isCorrect = isLoginCorrect(login) & isPasswordCorrect(password) & isEmailCorrect(email);
        String incorrectDataMessage = errorMessage.toString();
        if (incorrectDataMessage.length() != 0) {
            request.setAttribute("error", incorrectDataMessage);
        }
        return isCorrect;
    }

    private static boolean isLoginCorrect(String login) {
        Matcher m = LOGIN.matcher(login);
        if (m.find()) {
            return true;
        } else {
            errorMessage.append(ERROR_LOGIN);
            return false;
        }
    }

    private static boolean isPasswordCorrect(String password) {
        Matcher m = PASSWORD.matcher(password);
        if (m.find()) {
            return true;
        } else {
            errorMessage.append(ERROR_PASSWORD);
            return false;
        }
    }

    private static boolean isEmailCorrect(String email) {
        Matcher m = EMAIL.matcher(email);
        if (m.find()) {
            return true;
        } else {
            errorMessage.append(ERROR_EMAIL);
            return false;
        }
    }

}
