package com.demo.elastic;

import com.demo.elastic.bean.Employee;
import com.demo.elastic.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author NekoChips
 * @description 测试 ElasticService
 * @date 2020/4/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticApplication.class)
public class TestElasticServiceDemo {

    @Autowired
    private EmployeeRepository employeeRepository;

    
    @Test
    public void testFindById() {
        Employee employee = employeeRepository.findById(1).orElse(null);
        System.out.println(employee);
    }
    
    @Test
    public void testFindAll() {
        Page<Employee> employeePage = employeeRepository.findAll(PageRequest.of(0, 10));
        List<Employee> employees = employeePage.getContent();
        employees.forEach(System.out::println);
    }
    
    @Test
    public void testFindAllByLastName() {
        String lastName = "Smith";
        List<Employee> employees = employeeRepository.findAllByLastName(lastName);
        if (CollectionUtils.isEmpty(employees)) {
            return;
        }
        employees.forEach(System.out::println);
    }
    
    @Test
    public void testFindEmployeesByAbout() {
        String about = "rock";
        List<Employee> employees = employeeRepository.findEmployeesByAbout(about);
        if (CollectionUtils.isEmpty(employees)) {
            return;
        }
        employees.forEach(System.out::println);
    }
    
    @Test
    public void testFindEmployeesByLastNameAndAgeAfter() {
        String lastName = "Smith";
        Integer age = 30;
        List<Employee> employees = employeeRepository.findEmployeesByLastNameAndAgeAfter(lastName, age);
        if (CollectionUtils.isEmpty(employees)) {
            return;
        }
        employees.forEach(System.out::println);
    }
}
