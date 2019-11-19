package by.zaycevigor.logic.util;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public final class DegreesList {


    private static Map<Integer, String> degreesList = new HashMap<>();
    private static final String THOUSANDS_ENDING_FIRST = "а ";
    private static final String THOUSANDS_ENDING_SECOND = "и ";

    private static final String ENDING_FIRST = "а ";
    private static final String ENDING_SECOND = "ов ";

    public static final int THOUSANDS_DEG = 2;
    public static final int DEFAULT_LAST_NUM = 0;

    static {
        degreesList.put(THOUSANDS_DEG, "Тысяч");
        degreesList.put(3, "Миллион");
        degreesList.put(4, "Миллиард");
        degreesList.put(5, "Триллион");
        degreesList.put(6, "Квадриллион");
        degreesList.put(7, "Квинтиллион");
        degreesList.put(8, "Секстиллион");
        degreesList.put(9, "Септиллион");
        degreesList.put(10, "Октиллион");
        degreesList.put(11, "Нониллион");
        degreesList.put(12, "Дециллион");
    }

    private DegreesList() {

    }

    public static String convertDegree(int degree, String number) {
        String degreeRec = degreesList.get(degree);

        if (number.length() > 1) {
            int lastNums = Integer.parseInt(number.substring(number.length() - 2));
            if (lastNums > 10 && lastNums < 19) {
                degreeRec = findNumberDegreeEnding(degree, DEFAULT_LAST_NUM, degreeRec);
            } else {
                degreeRec = findNumberDegreeEnding(degree, lastNums % 10, degreeRec);
            }
        }else {
            degreeRec = findNumberDegreeEnding(degree, Integer.parseInt(number.substring(number.length() - 1)), degreeRec);
        }
        return degreeRec;
    }

    private static String findNumberDegreeEnding(int degree, int lastNum, String degreeRec) {
        if (degree > THOUSANDS_DEG) {
            switch (lastNum) {
                case 1:
                    degreeRec += " ";
                    break;
                case 2:
                case 3:
                case 4:
                    degreeRec += ENDING_FIRST;
                    break;
                default:
                    degreeRec += ENDING_SECOND;
                    break;
            }
        } else {
            if (degree == THOUSANDS_DEG) {
                switch (lastNum) {
                    case 1:
                        degreeRec += THOUSANDS_ENDING_FIRST;
                        break;
                    case 2:
                    case 3:
                    case 4:
                        degreeRec += THOUSANDS_ENDING_SECOND;
                        break;
                    default:
                        degreeRec += " ";
                        break;
                }
            }
        }
        return degreeRec;
    }
}
