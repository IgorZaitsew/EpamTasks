package by.tc.zaycevigor.dao.exception;

public class ConnectionPoolException extends RuntimeException {
    private static final long serialVersionUID = -82780531581969712L;

    public ConnectionPoolException() {
        super();
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(Exception e) {
        super(e);
    }

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}
