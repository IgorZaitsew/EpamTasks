package by.zaycevigor.service;

import by.zaycevigor.logic.NumberConverter;

import java.math.BigInteger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public enum NumberService {
    SERVICE;
    public static final String WRONG_NUMBER_EXC_TEXT="The number you entered is incorrect";

    public static String SEPARATOR_CHAR = "-";
    public static int PARTITITION_FREQUENCY = 3;
    public static final Pattern NUMERIC= Pattern.compile("^[0-9]+$", Pattern.CASE_INSENSITIVE);


    public String convert(String numericalRecord) throws NumberException {
        if(!validate(numericalRecord)){
            throw new NumberException(WRONG_NUMBER_EXC_TEXT);
        }
        numericalRecord = divide(numericalRecord);
        return (NumberConverter.convert(numericalRecord)).replaceAll("\\s+", " ");
    }

    private String divide(String nonDividedRecord) {
        StringBuilder dividedNumber = new StringBuilder(nonDividedRecord);
        int index = 0;
        while (dividedNumber.length() - index > PARTITITION_FREQUENCY) {
            index += PARTITITION_FREQUENCY;
            dividedNumber.insert(dividedNumber.length() - index, SEPARATOR_CHAR);
            index++;
        }
        return dividedNumber + "";
    }

    private boolean validate(String unchecked){
        Matcher m = NUMERIC.matcher(unchecked);
        return m.find();
    }

}
