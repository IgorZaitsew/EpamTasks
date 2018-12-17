/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.training.zaycevigor.model.util.exceptions;

/**
 *
 * @author Administrator
 */
public class MathCalcException extends Exception {

    public MathCalcException() {
    }

    public MathCalcException(String message) {
        super(message);
    }

    public MathCalcException(String message, Throwable cause) {
        super(message, cause);
    }

    public MathCalcException(Throwable cause) {
        super(cause);
    }

}
