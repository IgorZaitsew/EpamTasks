package by.zaycevigor.service;


import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Scanner;

import static by.zaycevigor.service.NumberService.SERVICE;
import static org.junit.jupiter.api.Assertions.*;

public class NumberServiceTest {
    public static final String INPUT_NUMBER = "Input a number: ";
    public static final String INPUT_RESULT = "Input the expected result: ";
    public static final String OUTPUT_TEXT = "Your number: %d \n";

    @Test
    void convertTest() throws NumberException {
        String[][] numbers = {{"123", "сто двадцать три"},
                {"951051001921", "девятьсот пятьдесят один миллиард пятьдесят один миллион одна тысяча девятьсот двадцать один"},
                {"000001","один"},
                {"2","два"}};
        for(String[] pair:numbers){
            assertEquals(pair[1].trim(),(SERVICE.convert(pair[0]).trim()).toLowerCase());
        }
    }
}


