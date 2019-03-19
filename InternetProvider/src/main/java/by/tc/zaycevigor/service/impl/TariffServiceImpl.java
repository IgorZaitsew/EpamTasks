package by.tc.zaycevigor.service.impl;

import java.util.List;

import by.tc.zaycevigor.dao.TariffDAO;
import by.tc.zaycevigor.dao.DAOProvider;
import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.Tariff;
import by.tc.zaycevigor.entity.criteria.SearchCriteria;
import by.tc.zaycevigor.service.TariffService;

public class TariffServiceImpl implements TariffService {
    private static final int FIRST = 0;
    private static final int ONE = 1;

    @Override
    public List<Tariff> find(SearchCriteria criteria) throws DaoException {

        DAOProvider provider = DAOProvider.getInstance();
        TariffDAO tariffDAO = provider.getTariffDAO();

        List<Tariff> tariffs;

        tariffs = tariffDAO.find(criteria);

        return tariffs;
    }

    @Override
    public List<Tariff> find(SearchCriteria criteria, int count) throws DaoException {
        DAOProvider provider = DAOProvider.getInstance();
        TariffDAO tariffDAO = provider.getTariffDAO();

        return tariffDAO.find(criteria, count);
    }

    @Override
    public Tariff findSingle(SearchCriteria criteria) throws DaoException {
        DAOProvider provider = DAOProvider.getInstance();
        TariffDAO tariffDAO = provider.getTariffDAO();

        return tariffDAO.find(criteria, ONE).get(FIRST);
    }

}
