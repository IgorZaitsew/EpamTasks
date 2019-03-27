package by.tc.zaycevigor.dao;

import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.entity.UserData;

import java.util.List;

public interface UserDAO {

    User getUser(long contractNumber) throws DaoException;

    boolean registration(UserData userData) throws DaoException;

    List<User> getUserList() throws DaoException;

    boolean deleteUser(long contractNumber) throws DaoException;
}
