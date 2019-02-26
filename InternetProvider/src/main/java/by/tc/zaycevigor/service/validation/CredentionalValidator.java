package by.tc.zaycevigor.service.validation;

import by.tc.zaycevigor.entity.UserData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CredentionalValidator {
    private static final String LOGIN_REGEX = "^[a-z0-9_-]{3,15}$";
    private static final String PASSWORD_REGEX = "^[A-Za-z0-9]{6,16}$";
    private static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    public static boolean isCorrect(String login, String password) {

        return isLoginCorrect(login) && isPasswordCorrect(password);
    }

    public static boolean isCorrect(String login, String password, String email) {

        return isLoginCorrect(login) && isPasswordCorrect(password) && isEmailCorrect(email);
    }

    private static boolean isLoginCorrect(String login) {
        Pattern p = Pattern.compile(LOGIN_REGEX);
        Matcher m = p.matcher(login);
        return m.matches();
    }

    private static boolean isPasswordCorrect(String password) {
        Pattern p = Pattern.compile(PASSWORD_REGEX);
        Matcher m = p.matcher(password);
        return m.matches();
    }

    private static boolean isEmailCorrect(String email) {
        Pattern p = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);
        return m.matches();
    }

}
