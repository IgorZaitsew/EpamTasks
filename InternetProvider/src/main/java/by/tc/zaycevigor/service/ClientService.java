package by.tc.zaycevigor.service;

import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.entity.UserData;

import javax.servlet.http.HttpServletRequest;

public interface ClientService {

    User authorization(long contractNumber, HttpServletRequest request) throws ServiceException;

    boolean registration(UserData user, HttpServletRequest request) throws ServiceException;

}
