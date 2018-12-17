package by.epam.training.zaycevigor.model.util;

import by.epam.training.zaycevigor.model.util.exceptions.ParserException;
import by.epam.training.zaycevigor.model.util.exceptions.SphereException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.log4j.Logger;

public final class StringParserFromFile implements Parser {

    private static final String WRONG_PATH = "Wrong file path";
    private static Logger log = Logger.getLogger(StringParserFromFile.class);

    @Override
    public String parse(String filePath) throws IOException, ParserException {
        StringBuffer fromFile = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String s;
            while ((s = br.readLine()) != null) {

                fromFile.append(s).append("\n");
            }
        } catch (IOException e) {
            log.error(WRONG_PATH);
            throw new ParserException(WRONG_PATH, e);
        }
        return fromFile + " ";
    }

}
