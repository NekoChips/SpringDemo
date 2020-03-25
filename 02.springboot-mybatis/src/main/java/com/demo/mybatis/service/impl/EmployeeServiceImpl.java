package com.demo.mybatis.service.impl;

import com.demo.mybatis.bean.Employee;
import com.demo.mybatis.mapper2.EmployeeMapper;
import com.demo.mybatis.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author NekoChips
 * @description IEmployeeService 接口实现类
 * @date 2020/3/25
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public int add(Employee employee) {
        return employeeMapper.add(employee);
    }

    @Override
    public int delete(String emNo) {
        return employeeMapper.delete(emNo);
    }

    @Override
    public int update(Employee employee) {
        return employeeMapper.update(employee);
    }

    @Override
    public Employee queryById(String emNo) {
        return employeeMapper.queryById(emNo);
    }

    @Override
    public List<Employee> queryList() {
        return employeeMapper.queryList();
    }
}
