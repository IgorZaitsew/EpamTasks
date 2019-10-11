package by.tc.zaycevigor.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.Tariff;
import by.tc.zaycevigor.entity.TariffData;
import by.tc.zaycevigor.entity.criteria.SearchCriteria;

public interface TariffDAO {

    List<Tariff> find(SearchCriteria criteria) throws DaoException;

    List<Tariff> find(SearchCriteria criteria, int count) throws DaoException;

    List<Tariff> find(int id,int limit) throws DaoException;

    int getTariffCount() throws DaoException;

    boolean add(TariffData data) throws DaoException;

    boolean deleteTariff(int tariffId) throws DaoException;

    boolean readjust(int tariffId, TariffData tariffData) throws DaoException;

    Map<Integer, BigDecimal> getPriceList()throws DaoException;
}
