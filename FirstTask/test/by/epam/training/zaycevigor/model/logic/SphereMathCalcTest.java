/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.training.zaycevigor.model.logic;

import by.epam.training.zaycevigor.model.entity.Sphere;
import static by.epam.training.zaycevigor.model.logic.SphereMathCalc.findRelationBtwParts;
import static by.epam.training.zaycevigor.model.logic.SphereMathCalc.findSquareOfSurface;
import static by.epam.training.zaycevigor.model.logic.SphereMathCalc.findVolume;
import static by.epam.training.zaycevigor.model.logic.SphereMathCalc.isTouchAnCoordLine;
import static by.epam.training.zaycevigor.model.util.ConstContainer.FILE_PATH;
import by.epam.training.zaycevigor.model.util.Creator;
import by.epam.training.zaycevigor.model.util.Parser;
import by.epam.training.zaycevigor.model.util.SphereCreator;
import by.epam.training.zaycevigor.model.util.SphereDataValidator;
import by.epam.training.zaycevigor.model.util.StringParserFromFile;
import by.epam.training.zaycevigor.model.util.Validator;
import by.epam.training.zaycevigor.model.util.exceptions.MathCalcException;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Игорь
 */
public class SphereMathCalcTest {

    private Sphere[] array;

    public SphereMathCalcTest() {
    }

    @Test
    public void findVolumeTest() throws MathCalcException {
        double result = findVolume(array[0]);
        double expected = Math.PI * 4 / 3 * Math.pow(array[0].getRad(), 3);
        assertEquals(expected, result, 0.005);

    }

    @Test
    public void findSquareOfSurfaceTest() throws MathCalcException {
        double result = findSquareOfSurface(array[0]);
        double expected = Math.PI * 4 * Math.pow(array[0].getRad(), 2);
        assertEquals(expected, result, 0.005);
    }

    @Test
    public void isTouchAnCoordLineTest() throws MathCalcException {
        boolean result = isTouchAnCoordLine(array[0]);
        boolean expected = true;
        assertEquals(expected, result);

    }

    @Test
    public void findRelationBtwPartsTest() throws MathCalcException {
        System.out.println(findRelationBtwParts(array[0]));
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        Creator creator = new SphereCreator();
        Validator validator = new SphereDataValidator();
        Parser parser = new StringParserFromFile();
        
        array = (Sphere[]) creator.create(validator.validate(parser.parse(FILE_PATH)));
        
    }

}
