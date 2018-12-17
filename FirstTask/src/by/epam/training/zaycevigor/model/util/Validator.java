/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.training.zaycevigor.model.util;

import by.epam.training.zaycevigor.model.util.exceptions.ValidatorException;

/**
 *
 * @author Administrator
 */
public interface Validator {

    public String validate(String unvalid) throws ValidatorException;
}
