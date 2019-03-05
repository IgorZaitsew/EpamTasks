package by.tc.zaycevigor.service;

import java.util.List;

import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.Tariff;
import by.tc.zaycevigor.entity.criteria.SearchCriteria;

public interface TariffService {

    List<Tariff> find(SearchCriteria criteria) throws DaoException;

}
