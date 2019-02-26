package by.tc.zaycevigor.service.impl;

import java.util.List;

import by.tc.zaycevigor.dao.TariffDAO;
import by.tc.zaycevigor.dao.DAOProvider;
import by.tc.zaycevigor.entity.Tariff;
import by.tc.zaycevigor.service.InternetService;

public class InternetServiceImpl implements InternetService {

	@Override
	public List<Tariff> find(String criterias) {
		// validation
		
		DAOProvider provider = DAOProvider.getInstance();
		TariffDAO tariffDAO = provider.getTariffDAO();
		
		List<Tariff> books;
		
		books = tariffDAO.find(criterias);
		
		return books;
	}

}
