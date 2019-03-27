package by.tc.zaycevigor.service.validation;

import by.tc.zaycevigor.entity.TariffData;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.tc.zaycevigor.service.util.ErrorMessages.*;

public class TariffValidator {
    public static final Pattern SPEED = Pattern.compile("^[0-9]+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern PRICE = Pattern.compile("^[0-9]+[.]?[0-9]{0,2}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern NAME = Pattern.compile("^[A-Z][a-z]{1,10}[ ][0-9]{0,5}$", Pattern.CASE_INSENSITIVE);

    public static final String ERROR = "error";
    private StringBuilder errorMessage;

    public boolean validate(HttpServletRequest request, TariffData data) {
        errorMessage = new StringBuilder();

        boolean isCorrect = isCorrect(data.getName(), NAME, TARIFF_NAME_ERROR) &
                isCorrect(String.valueOf(data.getPrice()), PRICE, TARIFF_PRICE_ERROR) &
                isCorrect(String.valueOf(data.getSpeed()), SPEED, TARIFF_SPEED_ERROR);
        String incorrectDataMessage = errorMessage.toString();
        if (incorrectDataMessage.length() != 0) {
            request.setAttribute(ERROR, incorrectDataMessage);
        }
        return isCorrect;
    }

    public boolean validate(BigDecimal amount, HttpServletRequest request) {
        errorMessage = new StringBuilder();

        boolean isCorrect = isCorrect(amount.toString(), PRICE, BALANCE_UP_AMOUNT_ERROR);
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
