package main.java.by.tc.task01.dao;

import java.io.IOException;
import main.java.by.tc.task01.entity.Appliance;
import main.java.by.tc.task01.entity.criteria.Criteria;
import main.java.by.tc.task01.service.exceptions.ParserException;

public interface ApplianceDAO {

    <E> Appliance find(Criteria<E> criteria) throws IOException, ParserException ;
}
