package by.tc.zaycevigor.dao.impl;

import by.tc.zaycevigor.dao.DAOProvider;
import by.tc.zaycevigor.dao.TariffDAO;
import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.Tariff;
import by.tc.zaycevigor.entity.criteria.SearchCriteria;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SQLTariffDAOTest {
    private TariffDAO tariffDAO;
    SearchCriteria criteria = new SearchCriteria();
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        criteria.setId(1);
        DAOProvider daoProvider = DAOProvider.getInstance();
        tariffDAO = daoProvider.getTariffDAO();
    }

    @org.junit.jupiter.api.Test
    void find() throws DaoException {
        int count=5;
        List<Tariff> tariffs = tariffDAO.find(new SearchCriteria());
        assertEquals(tariffs.size(),count);
    }

    @org.junit.jupiter.api.Test
    void find1() throws DaoException {
        List<Tariff> tariffs = tariffDAO.find(criteria);
        assertEquals(tariffs.size(),1);
    }
}