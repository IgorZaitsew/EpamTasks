package by.tc.zaycevigor.dao;

import by.tc.zaycevigor.exception.ProjectException;
import org.apache.log4j.Logger;

public class XMLDaoException extends ProjectException {

    private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger(XMLDaoException.class);

    public XMLDaoException(String message) {
        super(message);
        log.error(message);
    }

    public XMLDaoException(String message, Exception e) {
        super(message, e);
        log.error(message, e);
    }
}
