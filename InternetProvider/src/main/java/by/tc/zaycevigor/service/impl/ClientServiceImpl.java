package by.tc.zaycevigor.service.impl;

import by.tc.zaycevigor.dao.DAOProvider;
import by.tc.zaycevigor.dao.DaoException;
import by.tc.zaycevigor.dao.UserDAO;
import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.entity.UserData;
import by.tc.zaycevigor.service.ClientService;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.validation.CredentionalValidator;

public class ClientServiceImpl implements ClientService {

    @Override
    public User authorization(String login, String password) throws ServiceException {

        if (!CredentionalValidator.isCorrect(login, password)) {
            throw new ServiceException();
        }

        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();

        User user;

        try {
            user = userDAO.authentification(login, password);
        } catch (DaoException e) {
            throw new ServiceException();
        }

        return user;
    }

    @Override
    public boolean registration(UserData user) throws ServiceException {

        if (!CredentionalValidator.isCorrect(user.getLogin(), user.getPassword(), user.getEmail())) {
            return false;
        }

        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();

        try {
            userDAO.registration(user);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return true;
    }

}
