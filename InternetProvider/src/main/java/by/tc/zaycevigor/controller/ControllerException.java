package by.tc.zaycevigor.controller;

public class ControllerException extends Exception {

    private static final long serialVersionUID = 1L;

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(String message, Exception e) {
        super(message, e);
    }


}
