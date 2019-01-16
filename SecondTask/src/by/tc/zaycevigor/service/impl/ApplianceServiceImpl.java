package by.tc.zaycevigor.service.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import by.tc.zaycevigor.dao.ApplianceDAO;
import by.tc.zaycevigor.dao.DAOFactory;
import by.tc.zaycevigor.entity.Appliance;
import by.tc.zaycevigor.entity.criteria.Criteria;
import by.tc.zaycevigor.service.ApplianceService;
import by.tc.zaycevigor.service.parsing.ParserException;
import by.tc.zaycevigor.service.validation.Validator;

public class ApplianceServiceImpl implements ApplianceService {

    @Override
    public <E> Appliance find(Criteria<E> criteria) {
        if (!Validator.criteriaValidator(criteria)) {
            return null;
        }

        DAOFactory factory = DAOFactory.getInstance();
        ApplianceDAO applianceDAO = factory.getApplianceDAO();

        Appliance appliance = null;
        try {
            appliance = applianceDAO.find(criteria);
        } catch (IOException | ParserException ex) {
            Logger.getLogger(ApplianceServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return appliance;
    }

}
