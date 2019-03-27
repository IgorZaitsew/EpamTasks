package by.tc.zaycevigor.service.impl;

import java.math.BigDecimal;
import java.util.List;

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

import static by.tc.zaycevigor.service.util.ErrorMessages.TARIFF_EXIST;

public class TariffServiceImpl implements TariffService {
    private static final int FIRST = 0;
    private static final int ONE = 1;
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

    public String getErrorMessage() {
        return errorMessage.toString();
    }
}
