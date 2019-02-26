package by.tc.zaycevigor.dao;

import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.entity.UserData;

public interface UserDAO {

    User authentification(String login, String password) throws DaoException;

    void registration(UserData userData) throws DaoException;
}
