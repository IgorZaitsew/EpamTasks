package by.tc.zaycevigor.dao;

import java.util.List;
import by.tc.zaycevigor.entity.Tariff;

public interface TariffDAO {
	
	List<Tariff> find(String criterias);

}
