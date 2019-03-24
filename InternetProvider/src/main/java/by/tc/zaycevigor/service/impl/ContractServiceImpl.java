package by.tc.zaycevigor.service.impl;

import by.tc.zaycevigor.controller.command.util.Constant;
import by.tc.zaycevigor.dao.ContractDAO;
import by.tc.zaycevigor.dao.DAOProvider;
import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.Contract;
import by.tc.zaycevigor.entity.ContractData;
import by.tc.zaycevigor.entity.User;
import by.tc.zaycevigor.service.ContractService;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.validation.ContractValidator;

import javax.servlet.http.HttpServletRequest;

import static by.tc.zaycevigor.dao.util.Constant.ADMIN_ROLE;
import static by.tc.zaycevigor.dao.util.Constant.PARAMETER_USER;
import static by.tc.zaycevigor.service.util.ErrorMessages.*;

public class ContractServiceImpl implements ContractService {
    private StringBuilder errorMessage;
    private long contractNumber;

    @Override
    public Contract contractAuthentification(long contractNumber, String password, HttpServletRequest request) throws ServiceException {
        ContractValidator validator = new ContractValidator();
        if (!validator.validate(request, contractNumber, password)) {
            errorMessage = new StringBuilder();
            errorMessage.append(validator.getErrorMessage());
            return null;
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        ContractDAO contractDAO = daoProvider.getContractDAO();

        Contract contract;
        try {
            contract = contractDAO.contractAuthentification(contractNumber, password);
        } catch (DaoException e) {
            throw new ServiceException();
        }

        return contract;
    }

    @Override
    public Contract getContract(long contractNumber, HttpServletRequest request) throws ServiceException {
        if (!((User)request.getSession(false).getAttribute(Constant.PARAMETER_USER)).getRole().equals(Constant.ADMIN_ROLE)) {
            System.out.println(((User)request.getSession(false).getAttribute(Constant.PARAMETER_USER)).getRole());
            throw new ServiceException();
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        ContractDAO contractDAO = daoProvider.getContractDAO();

        Contract contract;
        try {
            contract = contractDAO.getContract(contractNumber);
        } catch (DaoException e) {
            throw new ServiceException();
        }

        return contract;
    }

    @Override
    public boolean addContract(ContractData data, HttpServletRequest request) throws ServiceException {
        ContractValidator validator = new ContractValidator();
        if (!validator.validate(request, data)) {
            errorMessage = new StringBuilder();
            errorMessage.append(validator.getErrorMessage());
            return false;
        }

        DAOProvider daoProvider = DAOProvider.getInstance();
        ContractDAO contractDAO = daoProvider.getContractDAO();

        try {
            if (!contractDAO.addContract(data)) {
                errorMessage = new StringBuilder();
                errorMessage.append(EMAIL_ADRESS_EXIST);
                return false;
            }

        } catch (DaoException e) {
            throw new ServiceException();
        }
        contractNumber = contractDAO.getContractNumber();
        return true;
    }

    @Override
    public boolean changeTariff(long contractNumber, int tariffId, HttpServletRequest request) throws ServiceException {
        ContractValidator validator = new ContractValidator();
        if (!validator.validate(request, tariffId)) {
            errorMessage = new StringBuilder();
            errorMessage.append(validator.getErrorMessage());
            return false;
        }

        DAOProvider daoProvider = DAOProvider.getInstance();
        ContractDAO contractDAO = daoProvider.getContractDAO();
        try {
            if (!contractDAO.changeTariff(contractNumber, tariffId)) {
                errorMessage = new StringBuilder();
                errorMessage.append(TARIFF_ID_ERROR);
                return false;
            }

        } catch (DaoException e) {
            throw new ServiceException();
        }
        return true;
    }

    @Override
    public boolean deleteContract(long contractNumber) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        ContractDAO contractDAO = daoProvider.getContractDAO();
        boolean result;
        try {
            result = contractDAO.deleteContract(contractNumber);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        if (!result) {
            errorMessage = new StringBuilder(CONTRACT_DELETE_ERROR);
        }
        return result;
    }

    public String getErrorMessage() {
        return errorMessage.toString();
    }

    public long getContractNumber() {
        return contractNumber;
    }
}

