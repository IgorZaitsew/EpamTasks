/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.tc.task01.dao.impl;

/**
 *
 * @author Administrator
 */
public class ApplianceDAOImplException extends Exception {
    
    public ApplianceDAOImplException() {
    }

    public ApplianceDAOImplException(String message) {
        super(message);
    }

    public ApplianceDAOImplException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplianceDAOImplException(Throwable cause) {
        super(cause);
    }

    public ApplianceDAOImplException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
