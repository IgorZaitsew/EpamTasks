package by.tc.zaycevigor.dao;

import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.entity.UserData;
import by.tc.zaycevigor.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Queue;

public interface UserDAO {

    User getUser(long contractNumber) throws DaoException;

    boolean registration(UserData userData) throws DaoException;

    List<User> getUserList() throws DaoException;

    boolean sendWarnings(Queue<Long> contractNumbers) throws DaoException;

    boolean deleteUser(long contractNumber) throws DaoException;

    boolean readjustUser(int userId, UserData userData) throws DaoException;

    void setStatus(long contractNumber,String status) throws DaoException;
}
