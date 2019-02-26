package by.tc.zaycevigor.dao.impl;


import java.util.ArrayList;
import java.util.List;

import by.tc.zaycevigor.dao.TariffDAO;
import by.tc.zaycevigor.dao.DaoException;
import by.tc.zaycevigor.entity.Tariff;


public class SQLTariffDAO extends SqlDao implements TariffDAO {

    @Override
    public List<Tariff> find(String criterias) {

        List<Tariff> list = new ArrayList<Tariff>();

        Tariff t1 = new Tariff();
        Tariff t2 = new Tariff();

        t1.setName("Tariff 1");
        t1.setPrice(500);

        t2.setName("Tariff 2");

        t2.setPrice(600);

        list.add(t1);
        list.add(t2);

        return list;
    }

}
