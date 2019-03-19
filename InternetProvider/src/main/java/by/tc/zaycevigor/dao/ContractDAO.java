package by.tc.zaycevigor.dao;

import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.Contract;
import by.tc.zaycevigor.entity.ContractData;

public interface ContractDAO {
    Contract contractAuthentification(long contractNumber, String password) throws DaoException;

    public boolean addContract(ContractData contractData) throws DaoException;

    public boolean changeTariff(long contractNumber, int tariffId) throws DaoException;

    public long getContractNumber();
}
