/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.by.tc.task01.entity.criteria;

import main.java.by.tc.task01.entity.criteria.Criteria;
import main.java.by.tc.task01.entity.criteria.SearchCriteria;
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
