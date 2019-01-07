/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.by.tc.task01.service.parsing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import main.java.by.tc.task01.service.exceptions.ParserException;
import static main.java.by.tc.task01.service.parsing.Parser.parse;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author Administrator
 */
public class ParserTest {

    @Test
    public void parseTest() throws IOException, ParserException {
        String dbPath;
        dbPath = "src/main/resources/appliances_db.txt";
        BufferedReader reader = new BufferedReader(new FileReader(dbPath));
        String line;
        line = parse(reader);
        // System.out.println(line.replaceAll(".* : ", ""));
        line = line.replaceAll(".* : ", "").replace(";", "");
        String[] dividedLine = line.split(", ");
        for (String singleLine : dividedLine) {
            System.out.println(singleLine);
        }
    }
}
