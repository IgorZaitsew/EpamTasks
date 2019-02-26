package by.tc.zaycevigor.service;

import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.entity.UserData;

public interface ClientService {

    User authorization(String login, String password) throws ServiceException;

    boolean registration(UserData user) throws ServiceException;

}
