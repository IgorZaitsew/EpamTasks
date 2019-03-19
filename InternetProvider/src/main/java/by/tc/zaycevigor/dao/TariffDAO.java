package by.tc.zaycevigor.dao;

import java.util.List;

import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.Tariff;
import by.tc.zaycevigor.entity.criteria.SearchCriteria;

public interface TariffDAO {

    List<Tariff> find(SearchCriteria criteria) throws DaoException;
    List<Tariff> find(SearchCriteria criteria,int count) throws DaoException;

}
