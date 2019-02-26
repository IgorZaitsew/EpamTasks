package by.tc.zaycevigor.service;

import java.util.List;

import by.tc.zaycevigor.entity.Tariff;

public interface InternetService {
	
	List<Tariff> find(String criterias);

}
