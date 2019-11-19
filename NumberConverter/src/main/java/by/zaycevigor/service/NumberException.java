package by.zaycevigor.service;

public class NumberException extends Exception {
    private static final long serialVersionUID=1416474532;

    public NumberException() {
        super();
    }

    public NumberException(String message) {
        super(message);
    }

    public NumberException(Exception e) {
        super(e);
    }

    public NumberException(String message, Exception e) {
        super(message, e);
    }

}
