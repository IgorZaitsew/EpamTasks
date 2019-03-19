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

import static by.tc.zaycevigor.service.util.ErrorMessages.CONTRACT_EXIST;
import static by.tc.zaycevigor.service.util.ErrorMessages.USER_EXIST;

public class ClientServiceImpl implements ClientService {
    private static Logger log = Logger.getLogger(ClientServiceImpl.class);
    private StringBuilder errorMessage;

    @Override
    public User authorization(long contractNumber,HttpServletRequest request) throws ServiceException {
        UserValidator validator = new UserValidator();
        if (!validator.validate(request, contractNumber)) {
            errorMessage = new StringBuilder();
            errorMessage.append(validator.getErrorMessage());
            return null;
        }

        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();

        User user;
        try {
            user = userDAO.authentification(contractNumber);
        } catch (DaoException e) {
            throw new ServiceException();
        }

        return user;
    }

    @Override
    public boolean registration(UserData user, HttpServletRequest request) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();

        try {
            if (!userDAO.registration(user)) {
                errorMessage = new StringBuilder();
                errorMessage.append(USER_EXIST);
                return false;
            }

        } catch (DaoException e) {
            log.error(e);
            throw new ServiceException();
        }
        return true;
    }

    public String getErrorMessage() {
        return errorMessage.toString();
    }
}
