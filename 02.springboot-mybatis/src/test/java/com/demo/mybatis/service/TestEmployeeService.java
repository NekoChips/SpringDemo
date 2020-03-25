package com.demo.mybatis.service;

import com.demo.mybatis.MybatisDemoApplication;
import com.demo.mybatis.bean.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author NekoChips
 * @description EmployeeService 接口测试类
 * @date 2020/3/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisDemoApplication.class)
public class TestEmployeeService {

    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void add() {
        Employee employee = new Employee();
        employee.setEmNo("005");
        employee.setEmName("Queen");
        employee.setEmSex("F");
        employee.setAge(40);

        int count = employeeService.add(employee);
        System.out.println(count > 0 ? "add success" : "add fail");
    }

    @Test
    public void queryById() {
        Employee employee = employeeService.queryById("005");
        System.out.println(employee);
    }

    @Test
    public void update() {
        Employee employee = employeeService.queryById("005");
        employee.setEmName("King");
        employee.setEmSex("M");
        employee.setAge(50);

        int update = employeeService.update(employee);
        System.out.println(update > 0 ? "update success" : "update fail");
    }

    @Test
    public void queryList() {
        List<Employee> employees = employeeService.queryList();
        employees.forEach(System.out::println);
    }

    @Test
    public void delete() {
        int delete = employeeService.delete("005");
        System.out.println(delete > 0 ? "delete success" : "delete fail");
    }
}
