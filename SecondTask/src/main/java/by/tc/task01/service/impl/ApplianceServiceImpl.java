package main.java.by.tc.task01.service.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.by.tc.task01.dao.ApplianceDAO;
import main.java.by.tc.task01.dao.DAOFactory;
import main.java.by.tc.task01.entity.Appliance;
import main.java.by.tc.task01.entity.criteria.Criteria;
import main.java.by.tc.task01.service.ApplianceService;
import main.java.by.tc.task01.service.exceptions.ParserException;
import main.java.by.tc.task01.service.validation.Validator;

public class ApplianceServiceImpl implements ApplianceService{

	@Override
	public <E> Appliance find(Criteria<E> criteria) {
		if (!Validator.criteriaValidator(criteria)) {
			return null;
		}
		
		DAOFactory factory = DAOFactory.getInstance();
		ApplianceDAO applianceDAO = factory.getApplianceDAO();
		
		Appliance appliance;
            try {
                appliance = applianceDAO.find(criteria);
            } catch (IOException ex) {
                Logger.getLogger(ApplianceServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserException ex) {
                Logger.getLogger(ApplianceServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
		
		// you may add your own code here
		
		return appliance;
	}

}

//you may add your own new classes
