package by.tc.zaycevigor.dao;

import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.Contract;
import by.tc.zaycevigor.entity.ContractData;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Queue;

public interface ContractDAO {
    Contract contractAuthentification(long contractNumber, String password) throws DaoException;

    Contract getContract(long contractNumber) throws DaoException;

    boolean addContract(ContractData contractData) throws DaoException;

    boolean changeTariff(long contractNumber, int tariffId) throws DaoException;

    long getContractNumber();

    boolean deleteContract(long contractNumber) throws DaoException;

    boolean upBalance(BigDecimal newBalance,long contractNumber) throws DaoException;

    void updateAllBalances(BigDecimal delta, Map<Integer,BigDecimal> priceList) throws DaoException;

    Queue<Long> findTariffUsers(int tariffId)throws DaoException;

    boolean resetTariffUsers(int tariffId)throws DaoException;

    BigDecimal getBalance(long contractNumber)throws DaoException;
}
