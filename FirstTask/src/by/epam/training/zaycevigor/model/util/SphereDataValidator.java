/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.training.zaycevigor.model.util;

import static by.epam.training.zaycevigor.model.util.ConstContainer.END_OF_LINE;
import static by.epam.training.zaycevigor.model.util.ConstContainer.START_OF_LINE;
import static by.epam.training.zaycevigor.model.util.ConstContainer.VALUES_DIVIDER;
import by.epam.training.zaycevigor.model.util.exceptions.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.log4j.Logger;

public class SphereDataValidator implements Validator {
    
    private static Logger log = Logger.getLogger(SphereDataValidator.class);
    
    public static final String NO_END_OF_LINE_EXC_MESSAGE = "There is no end of line symbol finded";
    public static final String POSITIVE_SIGN = "+";
    public static final String POSITIVE_AND_NEGATIVE_SIGN = "+-";
    public static final String UNVALID_STRING_EXC_MESSAGE = "The given string is empty";
    public static final int VALUES_COUNT = 4;
    
    @Override
    public String validate(String unvalid) throws ValidatorException {
        if (unvalid == null || unvalid.isEmpty()) {
            log.error(UNVALID_STRING_EXC_MESSAGE);
            throw new ValidatorException(UNVALID_STRING_EXC_MESSAGE);
        }
        
        StringBuilder valid = new StringBuilder("");
        label:
        for (int i = 0; i < unvalid.length(); i++) {
            if (START_OF_LINE.equals(unvalid.substring(i, i + 1))) {
                int valuesCount = 0;
                
                int j = i + 1;
                int startOfValue = i + 1;
                
                while (unvalid.substring(j, j + 1).matches(END_OF_LINE) || j < unvalid.length()) {
                    int endOfValue = elemLocation(unvalid, VALUES_DIVIDER, j);
                    if (endOfValue == -1) {
                        endOfValue = elemLocation(unvalid, END_OF_LINE, j - 1);
                        if (valuesCount != VALUES_COUNT - 1) {
                            i = endOfValue + 1;
                            continue label;
                        }
                    }
                    String sign;

                    /**
                     *
                     * Last value of line is a radius of sphere and it can't be
                     * negative by sign
                     *
                     */
                    if (valuesCount < VALUES_COUNT - 1) {
                        sign = POSITIVE_AND_NEGATIVE_SIGN;
                    } else {
                        sign = POSITIVE_SIGN;
                    }
                    
                    if (isNumber(unvalid.substring(startOfValue, endOfValue), sign)) {
                        valuesCount++;
                        if (valuesCount < VALUES_COUNT) {
                            
                            j = endOfValue + 1;
                            startOfValue = j;
                        } else {
                            valid.append(unvalid.substring(i, endOfValue + 1)).append("\n");
                            // i = endOfValue + 1;
                            i = elemLocation(unvalid, END_OF_LINE, i) + 1;
                            
                            continue label;
                            
                        }
                    } else {
                        i = elemLocation(unvalid, END_OF_LINE, i) + 1;
                        continue label;
                        
                    }
                    
                    j++;
                }
            }
        }
        
        log.info("Valid string: " + valid);
        return valid + " ";
    }
    
    private static int elemLocation(String string, String searchable, int start) {
        int position = -1;
        for (int i = start; i < string.length(); i++) {
            if (string.substring(i, i + 1).matches(searchable)) {
                position = i;
                return position;
            }
            if (string.substring(i, i + 1).matches(END_OF_LINE)) {
                break;
            }
        }
        return position;
    }
    
    private static int linesCount(String string) throws ValidatorException {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.substring(i, i + 1).equals(START_OF_LINE)) {
                for (int j = i + 1; j < string.length(); j++) {
                    if (j + 1 > string.length()) {
                        throw new ValidatorException(NO_END_OF_LINE_EXC_MESSAGE);
                    }
                    if (string.substring(j, j + 1).equals(END_OF_LINE)) {
                        count++;
                        i = j;
                        break;
                    }
                    
                }
            }
        }
        return count;
    }
    
    private static boolean isNumber(String number, String sign) {
        return (number.matches("([" + sign + "]?[0-9]+(\\.[0-9]+)?)+")
                || number.matches("^[" + sign + "]?\\d+"));
    }
}
