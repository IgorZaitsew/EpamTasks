/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.training.zaycevigor.model.util;

import static by.epam.training.zaycevigor.model.util.ConstContainer.FILE_PATH;
import static by.epam.training.zaycevigor.model.util.StringParserFromFile.*;
import by.epam.training.zaycevigor.model.util.exceptions.SphereException;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Игорь
 */
public class StringParserFromFileTest {
    
    public StringParserFromFileTest() {
    }

    @Test
    public void parse() throws SphereException, IOException{
        Creator creator = new SphereCreator();
        Parser parser = new StringParserFromFile();
        
        
    }

}
