package by.tc.zaycevigor.service;

import by.tc.zaycevigor.entity.Contract;
import by.tc.zaycevigor.entity.ContractData;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;

public interface ContractService {
    Contract contractAuthentification(long contractNumber, String password, HttpServletRequest request) throws ServiceException;

    Contract getContract(long contractNumber, HttpServletRequest request) throws ServiceException;

    boolean addContract(ContractData data, HttpServletRequest request) throws ServiceException;

    boolean changeTariff(long contractNumber, int tariffId, HttpServletRequest request) throws ServiceException;

    long getContractNumber();

    boolean deleteContract(long contractNumber) throws ServiceException;

    boolean upBalance(BigDecimal amount, BigDecimal balance, long contractNumber, HttpServletRequest request) throws ServiceException;

    void updateAllBalances(BigDecimal delta, Map<Integer,BigDecimal> priceList) throws ServiceException;

    Queue<Long> findTariffUsers(int tariffId) throws ServiceException;

    boolean resetTariffUsers(int tariffId)throws ServiceException;

    BigDecimal getBalance(long contractNumber) throws ServiceException;
}
