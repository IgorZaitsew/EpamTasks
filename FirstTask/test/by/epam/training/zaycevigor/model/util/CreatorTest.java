/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.training.zaycevigor.model.util;

import by.epam.training.zaycevigor.model.entity.Sphere;
import static by.epam.training.zaycevigor.model.util.ConstContainer.FILE_PATH;
import by.epam.training.zaycevigor.model.util.exceptions.CreatorException;
import by.epam.training.zaycevigor.model.util.exceptions.ParserException;

import by.epam.training.zaycevigor.model.util.exceptions.SphereException;
import by.epam.training.zaycevigor.model.util.exceptions.ValidatorException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author Игорь
 */
public class CreatorTest {
    
    private static final Logger log =  Logger.getLogger(CreatorTest.class);
    public CreatorTest() {
        log.info("Smtxt");
    }

    @Test
    public void sphereArrayCreateTest() throws IOException, SphereException, ParserException, ValidatorException, CreatorException {
//        Sphere[] array;
//        Creator creator = new SphereCreator();
//        Validator validator = new SphereDataValidator();
//        Parser parser = new StringParserFromFile();
//        
//        array = (Sphere[]) creator.create(validator.validate(parser.parse(FILE_PATH)));
//        
//        for (Sphere arrayElem : array) {
//            System.out.println("Sphere - " + arrayElem.toString() + "\n");
//        }

  log.info("Smtxt");
                
//        String string = "abcdefhi";
//      Pattern p = Pattern.compile("f");
//        Matcher m = p.matcher(string);
//        System.out.println(m.find());
//        System.out.println(string);
    }
}
