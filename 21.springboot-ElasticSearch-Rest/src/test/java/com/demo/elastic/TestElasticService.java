package com.demo.elastic;

import com.demo.elastic.bean.ElasticData;
import com.demo.elastic.bean.Employee;
import com.demo.elastic.service.ElasticService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author NekoChips
 * @description ElasticService
 * @date 2020/4/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ElasticRestApplication.class})
public class TestElasticService {

    private static final String TEST_INDEX = "spring-elastic-rest";
    
    @Autowired
    private ElasticService elasticService;
    
    @Test
    public void testBuildIndex() {
        elasticService.buildIndex(TEST_INDEX);
    }
    
    @Test
    public void testPut() {
        Employee employee = new Employee();
        employee.setId(3);
        employee.setFirstName("Douglas");
        employee.setLastName("Fir");
        employee.setAge(35);
        employee.setAbout("I like to build cabinets");
        employee.setInterest(new String[]{"forestry"});

        ElasticData<Employee> elasticData = new ElasticData<>(employee);
        elasticData.setId(String.valueOf(employee.getId()));
        elasticService.put(TEST_INDEX, elasticData);
    }
    
    @Test
    public void testSearchById() {
        Employee employee = elasticService.searchById(TEST_INDEX, "1", Employee.class);
        System.out.println(employee);
    }
    
    @Test
    public void testSearch1() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("about", "rock climbing");
        queryParams.put("age", 25);
        List<Employee> employees = elasticService.search(TEST_INDEX, queryParams, Employee.class);
        employees = Optional.ofNullable(employees)
                .orElse(new ArrayList<>());
        employees.forEach(System.out::println);
    }
    
    @Test
    public void testSearch2() {
        String[] fields = {"lastName", "about"};
        List<Employee> employees = elasticService.search(TEST_INDEX, fields, "rock, Fir", Employee.class);
        employees = Optional.ofNullable(employees)
                .orElse(new ArrayList<>());
        employees.forEach(System.out::println);
    }
    
    @Test
    public void testStatisticsByFiled() {
        Map<String, Long> result = elasticService.statisticsByField(TEST_INDEX, "all_interests", "interest");
        result.forEach((x, y) -> {
            System.out.println(String.format("%s : %s", x, y));
        });
    }
    
}
