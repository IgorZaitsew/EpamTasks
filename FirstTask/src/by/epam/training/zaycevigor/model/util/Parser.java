package by.epam.training.zaycevigor.model.util;

import by.epam.training.zaycevigor.model.util.exceptions.ParserException;
import java.io.IOException;

public interface Parser {

    public String parse(String filePath) throws IOException,ParserException;
}
