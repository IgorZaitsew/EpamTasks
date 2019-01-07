package main.java.by.tc.task01.service;

import main.java.by.tc.task01.entity.Appliance;
import main.java.by.tc.task01.entity.criteria.Criteria;

public interface ApplianceService {

    <E> Appliance find(Criteria<E> criteria);

}
