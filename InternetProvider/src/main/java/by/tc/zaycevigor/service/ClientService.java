package by.tc.zaycevigor.service;

import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.entity.UserData;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ClientService {

    User getUser(long contractNumber, HttpServletRequest request) throws ServiceException;

    boolean registration(UserData user, HttpServletRequest request) throws ServiceException;

    List<User> getUserList() throws ServiceException;

    boolean deleteUser(long contractNumber) throws ServiceException;
}
