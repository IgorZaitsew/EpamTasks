package by.tc.zaycevigor.exception;

import org.apache.log4j.Logger;

public class ProjectException extends Exception {
    private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger(ProjectException.class);
    private Exception hiddenException;

    public ProjectException(String message) {
        super(message);
        log.error(message);
    }

    public ProjectException(String message, Exception e) {
        super(message);
        hiddenException = e;
        log.error(message, e);
    }

    public Exception getHiddenException() {
        return hiddenException;
    }
}
