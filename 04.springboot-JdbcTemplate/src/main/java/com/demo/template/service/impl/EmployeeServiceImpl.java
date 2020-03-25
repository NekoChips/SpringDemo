package com.demo.template.service.impl;

import com.demo.template.bean.Employee;
import com.demo.template.dao2.IEmployeeDao;
import com.demo.template.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author NekoChips
 * @description IEmployeeService 实现类
 * @date 2020/3/25
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private IEmployeeDao employeeDao;

    @Override
    public int add(Employee employee) {
        return employeeDao.add(employee);
    }

    @Override
    public int delete(String emNo) {
        return employeeDao.delete(emNo);
    }

    @Override
    public int update(Employee employee) {
        return employeeDao.update(employee);
    }

    @Override
    public Employee queryById(String emNo) {
        return employeeDao.queryById(emNo);
    }

    @Override
    public List<Employee> queryList() {
        return employeeDao.queryList();
    }
}
