package by.tc.zaycevigor.service;

import by.tc.zaycevigor.entity.Appliance;
import by.tc.zaycevigor.entity.criteria.Criteria;

public interface ApplianceService {

    <E> Appliance find(Criteria<E> criteria);

}
