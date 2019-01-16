/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.tc.zaycevigor.entity.criteria;

import by.tc.zaycevigor.entity.criteria.Criteria;
import by.tc.zaycevigor.entity.criteria.SearchCriteria;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class CriteriaTest {

    @Test
    public void CriteriaTest() {
        Criteria<SearchCriteria.Oven> criteriaOven = new Criteria<SearchCriteria.Oven>();
        criteriaOven.add(SearchCriteria.Oven.CAPACITY, 3);
        System.out.println("Type - " + criteriaOven.productName());
    }

}
