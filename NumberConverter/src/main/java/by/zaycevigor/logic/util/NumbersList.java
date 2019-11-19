package by.zaycevigor.logic.util;

import java.util.HashMap;
import java.util.Map;

public final class NumbersList {

    private static Map<Integer, String> numbersList = new HashMap<>();
    private static final String ENDING_FROM_TEN_TO_TWENTY = "надцать ";

    private static final String ENDING_TENS_LOWEST = "дцать ";
    private static final String ENDING_TENS_BIGGEST = "десят ";

    private static final String ENDING_HUNDRED_LOWEST = "ста ";
    private static final String ENDING_HUNDRED_BIGGEST = "сот ";

    public static final int NUMERIC_INDEX = 1;
    public static final int TENS_INDEX = 2;
    public static final int HUNDREDS_INDEX = 3;

    public static final int THOUSANDS_DEG = 2;

    static {
        numbersList.put(0, "");
        numbersList.put(1, "Один");
        numbersList.put(100, "Сто");
        numbersList.put(-1, "Одна");
        numbersList.put(2, "Два");
        numbersList.put(200, "Двести");
        numbersList.put(-2, "Две");
        numbersList.put(3, "Три");
        numbersList.put(4, "Четыре");
        numbersList.put(40, "Сорок");
        numbersList.put(5, "Пять");
        numbersList.put(6, "Шесть");
        numbersList.put(7, "Семь");
        numbersList.put(8, "Восемь");
        numbersList.put(9, "Девять");
        numbersList.put(90, "Девяноста");
        numbersList.put(10, "Десять");
    }

    private NumbersList() {
    }

    public static String convertNumber(String number, int degree) {
        StringBuilder characterRecord = new StringBuilder();
        int length = number.length();
        for (int i = length; i > 0; i--) {
            int digic = Integer.parseInt(number.substring(length - i, length - i + 1));
            switch (i) {
                case HUNDREDS_INDEX:
                    characterRecord.append(hundredsConvert(digic) + " ");
                    break;
                case TENS_INDEX:
                    int prevNum = Integer.parseInt(number.substring(length - i + 1, length - i + 2));
                    characterRecord.append(tensConvert(digic, prevNum) + " ");
                    if (digic == 1) {
                        i = 0;
                    }
                    break;
                case NUMERIC_INDEX:
                    characterRecord.append(numeralConvert(digic, degree));
                    break;
            }
        }
        return characterRecord + " ";
    }

    public static StringBuilder numeralConvert(int numeral, int degree) {
        if ((numeral == 1 || numeral == 2) && degree == THOUSANDS_DEG) {
            numeral *= -1;
        }
        return new StringBuilder(numbersList.get(numeral));
    }

    private static StringBuilder tensConvert(int tens, int prevNum) {
        StringBuilder tensRecord = new StringBuilder();
        if (tens == 1) {
            if (prevNum == 0) {
                tensRecord.append(numbersList.get(10));
            } else {
                if (prevNum != 0 && prevNum != 2 && prevNum != 3) {
                    String number = numbersList.get(prevNum);
                    tensRecord.append(number, 0, number.length() - 1);
                } else {
                    if (prevNum == 2) {
                        tensRecord.append(numbersList.get(-prevNum));
                    } else {
                        if (prevNum == 3) {
                            tensRecord.append(numbersList.get(prevNum));
                        }
                    }
                }
                tensRecord.append(ENDING_FROM_TEN_TO_TWENTY);
            }

        } else {
            if (tens == 4) {
                tensRecord.append(numbersList.get(40));
            } else {
                if (tens == 9) {
                    tensRecord.append(numbersList.get(90));
                } else {
                    tensRecord.append(numbersList.get(tens));
                    if (tens > 4) {
                        tensRecord.append(ENDING_TENS_BIGGEST);
                    } else {
                        if (tens < 4 && tens!=0) {
                            tensRecord.append(ENDING_TENS_LOWEST);
                        }
                    }
                }
            }
        }
        return tensRecord;
    }

    private static StringBuilder hundredsConvert(int hundeds) {
        StringBuilder hundredsRecord = new StringBuilder();
        if (hundeds == 1) {
            hundredsRecord.append(numbersList.get(100));
        } else {
            if (hundeds == 2) {
                hundredsRecord.append(numbersList.get(200));
            } else {
                hundredsRecord.append(numbersList.get(hundeds));
                if (hundeds > 4) {
                    hundredsRecord.append(ENDING_HUNDRED_BIGGEST);
                } else {
                    if (hundeds > 2) {
                        hundredsRecord.append(ENDING_HUNDRED_LOWEST);
                    }
                }
            }
        }

        return hundredsRecord;
    }
}
