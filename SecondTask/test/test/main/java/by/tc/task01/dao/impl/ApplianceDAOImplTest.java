/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.main.java.by.tc.task01.dao.impl;

import java.io.IOException;
import main.java.by.tc.task01.dao.impl.ApplianceDAOImpl;
import main.java.by.tc.task01.entity.Appliance;
import main.java.by.tc.task01.entity.criteria.Criteria;
import main.java.by.tc.task01.entity.criteria.SearchCriteria;
import main.java.by.tc.task01.service.parsing.ParserException;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author Administrator
 */
public class ApplianceDAOImplTest {

    public ApplianceDAOImplTest() {
    }

    @Test
    public void findTest() throws IOException, ParserException {
        Appliance appliance;
        ApplianceDAOImpl applianceDAOImpl = new ApplianceDAOImpl();

        Criteria<SearchCriteria.TabletPC> criteriaTabletPC = new Criteria<>();
        criteriaTabletPC.add(SearchCriteria.TabletPC.COLOR, "green");
        criteriaTabletPC.add(SearchCriteria.TabletPC.DISPLAY_INCHES, 16);
        criteriaTabletPC.add(SearchCriteria.TabletPC.MEMORY_ROM, 16000);

        appliance = applianceDAOImpl.find(criteriaTabletPC);
        System.out.println(appliance.toString());

        Criteria<SearchCriteria.VacuumCleaner> vacuumCleaner = new Criteria<>();
        vacuumCleaner.add(SearchCriteria.VacuumCleaner.POWER_CONSUMPTION, 110);
        vacuumCleaner.add(SearchCriteria.VacuumCleaner.CLEANING_WIDTH, 25);

        appliance = applianceDAOImpl.find(vacuumCleaner);
        System.out.println(appliance.toString());
    }
}
