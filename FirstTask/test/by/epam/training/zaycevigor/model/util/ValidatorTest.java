/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.training.zaycevigor.model.util;

import static by.epam.training.zaycevigor.model.util.ConstContainer.FILE_PATH;
import by.epam.training.zaycevigor.model.util.exceptions.ParserException;
import by.epam.training.zaycevigor.model.util.exceptions.SphereException;
import by.epam.training.zaycevigor.model.util.exceptions.ValidatorException;
import java.io.IOException;
import org.testng.annotations.Test;

/**
 *
 * @author Игорь
 */
public class ValidatorTest {

    public ValidatorTest() {
    }

    @Test
    public void stringValidateTest() throws IOException, SphereException, ParserException, ValidatorException {
        Validator validator = new SphereDataValidator();
        Parser parser = new StringParserFromFile();
        System.out.println(validator.validate(parser.parse(FILE_PATH)));
    }
}
