package main.java.by.tc.task01.service.parsing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import main.java.by.tc.task01.service.exceptions.ParserException;

public class Parser {

    private static final String WRONG_PATH = "Wrong database path";

    public static String parse(BufferedReader reader) throws IOException, ParserException {
        String line;
        line = reader.readLine();
        return line;
    }

}
