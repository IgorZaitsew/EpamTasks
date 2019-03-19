package by.tc.zaycevigor.dao;

import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.entity.UserData;

public interface UserDAO {

    User authentification(long contractNumber) throws DaoException;

    boolean registration(UserData userData) throws DaoException;
}
