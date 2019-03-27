package by.tc.zaycevigor.service.impl;

import by.tc.zaycevigor.dao.DAOProvider;
import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.dao.UserDAO;
import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.entity.UserData;
import by.tc.zaycevigor.service.ClientService;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.validation.UserValidator;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static by.tc.zaycevigor.service.util.ErrorMessages.USER_DELETE_ERROR;
import static by.tc.zaycevigor.service.util.ErrorMessages.USER_EXIST;

public class ClientServiceImpl implements ClientService {
    private StringBuilder errorMessage;

    @Override
    public User getUser(long contractNumber, HttpServletRequest request) throws ServiceException {
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
            user = userDAO.getUser(contractNumber);
        } catch (DaoException e) {
            throw new ServiceException();
        }

        return user;
    }

    @Override
    public boolean registration(UserData user, HttpServletRequest request) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();
        UserValidator validator = new UserValidator();
        if (!validator.validate(request, user.getContractNumber(),user.getEmail())) {
            errorMessage = new StringBuilder();
            errorMessage.append(validator.getErrorMessage());
            return false;
        }
        try {
            if (!userDAO.registration(user)) {
                errorMessage = new StringBuilder();
                errorMessage.append(USER_EXIST);
                return false;
            }

        } catch (DaoException e) {
            throw new ServiceException();
        }
        return true;
    }

    @Override
    public List<User> getUserList() throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();
        List<User> userList;
        try {
            userList = userDAO.getUserList();

        } catch (DaoException e) {
            throw new ServiceException();
        }
        return userList;
    }

    @Override
    public boolean deleteUser(long contractNumber) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();
        boolean result;
        try {
            result = userDAO.deleteUser(contractNumber);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        if(!result){
            errorMessage=new StringBuilder(USER_DELETE_ERROR);
        }
        return result;
    }
    public String getErrorMessage() {
        return errorMessage.toString();
    }

}
