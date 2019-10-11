package by.tc.zaycevigor.service;

import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.entity.UserData;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Queue;

public interface ClientService {

    User getUser(long contractNumber, HttpServletRequest request) throws ServiceException;

    boolean registration(UserData user, HttpServletRequest request) throws ServiceException;

    List<User> getUserList() throws ServiceException;

    boolean deleteUser(long contractNumber) throws ServiceException;

    boolean sendWarnings(Queue<Long> contractNumbers) throws ServiceException;

    boolean readjustUser(int userId, UserData userData, HttpServletRequest request) throws ServiceException;

    void setStatus(long contractNumber,String status) throws ServiceException;
}
