package by.tc.zaycevigor.service.impl;

import by.tc.zaycevigor.dao.DAOProvider;
import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.dao.UserDAO;
import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.entity.UserData;
import by.tc.zaycevigor.service.ClientService;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.validation.UserValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ClientServiceImpl implements ClientService {
    private static Logger log = Logger.getLogger(ClientServiceImpl.class);

    @Override
    public User authorization(String login, String password, HttpServletRequest request) throws ServiceException {

        if (!UserValidator.isCorrect(login, password, request)) {
            return null;
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
    public boolean registration(UserData user, HttpServletRequest request) throws ServiceException {

        if (!UserValidator.isCorrect(user.getLogin(), user.getPassword(), user.getEmail(), request)) {
            return false;
        }

        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();

        try {
            if(!userDAO.registration(user)){
                return false;
            }

        } catch (DaoException e) {
            log.error(e);
            throw new ServiceException();
        }
        return true;
    }

}
