package by.tc.zaycevigor.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import by.tc.zaycevigor.dao.TariffDAO;
import by.tc.zaycevigor.dao.DAOProvider;
import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.Tariff;
import by.tc.zaycevigor.entity.TariffData;
import by.tc.zaycevigor.entity.criteria.SearchCriteria;
import by.tc.zaycevigor.service.ServiceException;
import by.tc.zaycevigor.service.TariffService;
import by.tc.zaycevigor.service.validation.TariffValidator;

import javax.servlet.http.HttpServletRequest;

import static by.tc.zaycevigor.service.util.ErrorMessages.*;

public class TariffServiceImpl implements TariffService {
    private static final int FIRST = 0;
    private static final int ONE = 1;
    private static final String PARAMETER_TARIFFS_SIZE = "tariffs_size";
    private StringBuilder errorMessage;

    @Override
    public List<Tariff> find(SearchCriteria criteria) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        TariffDAO tariffDAO = provider.getTariffDAO();
        List<Tariff> tariffList;
        try {
            tariffList = tariffDAO.find(criteria);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return tariffList;
    }

    @Override
    public List<Tariff> find(SearchCriteria criteria, int count) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        TariffDAO tariffDAO = provider.getTariffDAO();
        List<Tariff> tariffList;
        try {
            tariffList = tariffDAO.find(criteria, count);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return tariffList;
    }


    @Override
    public List<Tariff> find(int tariffId, int limit, int firstId, HttpServletRequest request) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        TariffDAO tariffDAO = provider.getTariffDAO();
        List<Tariff> tariffList;
        try {
            int tariffCount = tariffDAO.getTariffCount();
            request.setAttribute(PARAMETER_TARIFFS_SIZE, tariffCount);
            if (tariffId > tariffCount || tariffId < firstId) {
                errorMessage = new StringBuilder();
                errorMessage.append(TARIFF_ID_OUT_OF_BOUND);
                throw new DaoException();
            } else {
                tariffList = tariffDAO.find(tariffId, limit);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return tariffList;
    }

    @Override
    public Tariff findSingle(SearchCriteria criteria) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        TariffDAO tariffDAO = provider.getTariffDAO();

        Tariff tariff;
        try {
            tariff = tariffDAO.find(criteria, ONE).get(FIRST);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return tariff;
    }

    @Override
    public boolean add(TariffData data, HttpServletRequest request) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        TariffDAO tariffDAO = daoProvider.getTariffDAO();
        TariffValidator validator = new TariffValidator();
        if (!validator.validate(request, data)) {
            errorMessage = new StringBuilder();
            errorMessage.append(validator.getErrorMessage());
            return false;
        }
        try {
            if (!tariffDAO.add(data)) {
                errorMessage = new StringBuilder();
                errorMessage.append(TARIFF_EXIST);
                return false;
            }
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return true;
    }

    @Override
    public boolean deleteTariff(int tariffId) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        TariffDAO tariffDAO = daoProvider.getTariffDAO();
        boolean result;
        try {
            result = tariffDAO.deleteTariff(tariffId);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        if (!result) {
            errorMessage = new StringBuilder(TARIFF_DELETE_ERROR);
        }
        return result;
    }

    @Override
    public boolean readjustTariff(int tariffId, TariffData tariffData, HttpServletRequest request) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        TariffDAO tariffDAO = daoProvider.getTariffDAO();
        TariffValidator validator = new TariffValidator();
        if (!validator.validate(request, tariffData)) {
            errorMessage = new StringBuilder();
            errorMessage.append(validator.getErrorMessage());
            return false;
        }
        try {
            if (!tariffDAO.readjust(tariffId, tariffData)) {
                errorMessage = new StringBuilder();
                errorMessage.append(TARIFF_NOT_EXIST);
                return false;
            }
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return true;
    }

    @Override
    public Map<Integer, BigDecimal> getPriceList() throws ServiceException {
        TariffDAO tariffDAO = DAOProvider.getInstance().getTariffDAO();
        boolean result;
        try {
            return tariffDAO.getPriceList();
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public String getErrorMessage() {
        if (errorMessage == null) errorMessage = new StringBuilder();
        return errorMessage.toString();
    }
}
