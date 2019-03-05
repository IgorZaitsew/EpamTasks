package by.tc.zaycevigor.controller;

public class ControllerException extends Exception {

    private static final long serialVersionUID = 5463789243712L;

    public ControllerException() {
        super();
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(Exception e) {
        super(e);
    }

    public ControllerException(String message, Exception e) {
        super(message, e);
    }

}
