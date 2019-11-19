package by.zaycevigor.logic;

import by.zaycevigor.logic.util.DegreesList;
import by.zaycevigor.logic.util.NumbersList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberConverter {
    public static String SEPARATOR_CHAR = "-";

    public static String ZERO_SYMBOL = "0";

    public static String convert(String numbericalRecord) {
        List<String> dividedNumber = new ArrayList<>();
        Collections.addAll(dividedNumber, numbericalRecord.split(SEPARATOR_CHAR));

        int size = dividedNumber.size();
        int degree = size;
        StringBuilder convertedNumber = new StringBuilder();
        while (degree > 0) {
            String current = numberFormat(dividedNumber.get(size - degree));
                convertedNumber.append(NumbersList.convertNumber(current, degree));
                if (degree > 1&&!current.equals("0")) {
                    convertedNumber.append(DegreesList.convertDegree(degree, current));
                }
            degree--;
        }
        return convertedNumber + "";
    }

    private static String numberFormat(String nonFormatedNum) {
        StringBuilder formatedNum = new StringBuilder();
        if (nonFormatedNum.length() > 1) {
            int badCount = 0;
            for (int i = 0; i < nonFormatedNum.length() - 1; i++) {
                if (nonFormatedNum.substring(i, i + 1).equals(ZERO_SYMBOL)) {
                    badCount++;
                } else {
                    break;
                }
            }
            formatedNum.append(nonFormatedNum, badCount, nonFormatedNum.length());
        } else {
            formatedNum.append(nonFormatedNum);
        }
        return formatedNum + "";
    }
}
