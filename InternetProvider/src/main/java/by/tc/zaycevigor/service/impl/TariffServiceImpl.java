package by.tc.zaycevigor.service.impl;

import java.util.List;

import by.tc.zaycevigor.dao.TariffDAO;
import by.tc.zaycevigor.dao.DAOProvider;
import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.entity.Tariff;
import by.tc.zaycevigor.entity.criteria.SearchCriteria;
import by.tc.zaycevigor.service.TariffService;

public class TariffServiceImpl implements TariffService {

	@Override
	public List<Tariff> find(SearchCriteria criteria) throws DaoException {
		
		DAOProvider provider = DAOProvider.getInstance();
		TariffDAO tariffDAO = provider.getTariffDAO();
		
		List<Tariff> tariffs;

		tariffs = tariffDAO.find(criteria);
		
		return tariffs;
	}

}
