package by.tc.zaycevigor.dao;

import java.io.IOException;
import by.tc.zaycevigor.entity.Appliance;
import by.tc.zaycevigor.entity.criteria.Criteria;
import by.tc.zaycevigor.service.parsing.ParserException;

public interface ApplianceDAO {

    <E> Appliance find(Criteria<E> criteria) throws IOException, ParserException ;
}
