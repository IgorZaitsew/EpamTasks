package main.java.by.tc.task01.service.validation;

import main.java.by.tc.task01.entity.criteria.Criteria;

public class Validator {

    public static <E> boolean criteriaValidator(Criteria<E> criteria) {
        for (E key : criteria.keySet()) {
            String value = criteria.getValue(key).toString();
            if (isNumber(value)) {
                if (Float.parseFloat(value) <= 0) {
                    return false;
                }
            }

        }

        return true;
    }

    private static boolean isNumber(String number) {
        return (number.matches("([" + "+-" + "]?[0-9]+(\\.[0-9]+)?)+")
                || number.matches("^[" + "+-" + "]?\\d+"));
    }
}
