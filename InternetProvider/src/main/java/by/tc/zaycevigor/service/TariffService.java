package by.tc.zaycevigor.service;

import java.util.List;

import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.Tariff;
import by.tc.zaycevigor.entity.TariffData;
import by.tc.zaycevigor.entity.criteria.SearchCriteria;

import javax.servlet.http.HttpServletRequest;

public interface TariffService {

    List<Tariff> find(SearchCriteria criteria) throws ServiceException;

    List<Tariff> find(SearchCriteria criteria, int count) throws ServiceException;

    Tariff findSingle(SearchCriteria criteria) throws ServiceException;

    boolean add(TariffData data, HttpServletRequest request) throws ServiceException;
}
