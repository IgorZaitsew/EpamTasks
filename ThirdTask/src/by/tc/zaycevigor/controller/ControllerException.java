package by.tc.zaycevigor.controller;

import by.tc.zaycevigor.exception.ProjectException;
import org.apache.log4j.Logger;

public class ControllerException extends ProjectException {

    private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger(ControllerException.class);

    public ControllerException(String message) {
        super(message);
        log.error(message);
    }

    public ControllerException(String message, Exception e) {
        super(message, e);
        log.error(message, e);
    }
}
