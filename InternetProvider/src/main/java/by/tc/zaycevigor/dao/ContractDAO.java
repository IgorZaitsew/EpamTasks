package by.tc.zaycevigor.dao;

import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.Contract;
import by.tc.zaycevigor.entity.ContractData;

import java.math.BigDecimal;

public interface ContractDAO {
    Contract contractAuthentification(long contractNumber, String password) throws DaoException;

    Contract getContract(long contractNumber) throws DaoException;

    boolean addContract(ContractData contractData) throws DaoException;

    boolean changeTariff(long contractNumber, int tariffId) throws DaoException;

    long getContractNumber();

    boolean deleteContract(long contractNumber) throws DaoException;

    boolean upBalance(BigDecimal newBalance,long contractNumber) throws DaoException;
}
