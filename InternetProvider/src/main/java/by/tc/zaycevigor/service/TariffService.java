package by.tc.zaycevigor.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import by.tc.zaycevigor.entity.Tariff;
import by.tc.zaycevigor.entity.TariffData;
import by.tc.zaycevigor.entity.criteria.SearchCriteria;

import javax.servlet.http.HttpServletRequest;

public interface TariffService {

    List<Tariff> find(SearchCriteria criteria) throws ServiceException;

    List<Tariff> find(SearchCriteria criteria, int count) throws ServiceException;

    List<Tariff> find(int tariffId, int limit, int firstId, HttpServletRequest request) throws ServiceException;

    Tariff findSingle(SearchCriteria criteria) throws ServiceException;

    boolean add(TariffData data, HttpServletRequest request) throws ServiceException;

    boolean deleteTariff(int tariffId) throws ServiceException;

    boolean readjustTariff(int tariffId, TariffData tariffData, HttpServletRequest request) throws ServiceException;

    Map<Integer,BigDecimal> getPriceList()throws ServiceException;
}
