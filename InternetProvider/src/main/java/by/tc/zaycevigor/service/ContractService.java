package by.tc.zaycevigor.service;

import by.tc.zaycevigor.entity.Contract;
import by.tc.zaycevigor.entity.ContractData;

import javax.servlet.http.HttpServletRequest;

public interface ContractService {
    Contract contractAuthentification(long contractNumber, String password, HttpServletRequest request) throws ServiceException;

    Contract getContract(long contractNumber, HttpServletRequest request) throws ServiceException;

    boolean addContract(ContractData data, HttpServletRequest request) throws ServiceException;

    boolean changeTariff(long contractNumber, int tariffId, HttpServletRequest request) throws ServiceException;

    long getContractNumber();

    boolean deleteContract(long contractNumber) throws ServiceException;
}
