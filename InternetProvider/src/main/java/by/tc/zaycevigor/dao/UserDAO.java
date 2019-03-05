package by.tc.zaycevigor.dao;

import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.entity.UserData;

public interface UserDAO {

    User authentification(String login, String password) throws DaoException;

    boolean registration(UserData userData) throws DaoException;
}
