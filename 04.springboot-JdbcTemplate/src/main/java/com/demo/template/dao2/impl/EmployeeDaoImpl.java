package com.demo.template.dao2.impl;

import com.demo.template.bean.Employee;
import com.demo.template.dao2.IEmployeeDao;
import com.demo.template.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

/**
 * @author NekoChips
 * @description IEmployeeDao 实现类
 * @date 2020/3/25
 */
@Repository
public class EmployeeDaoImpl implements IEmployeeDao {

    @Autowired
    @Qualifier("db2JdbcTemplate")
    private JdbcTemplate db2JdbcTemplate;

    @Override
    public int add(Employee employee) {
        String sql = "insert into tb_employee (EM_NO, EM_NAME, EM_SEX, AGE) values (?, ?, ?, ?)";
        Object[] args = {employee.getEmNo(), employee.getEmName(), employee.getEmSex(), employee.getAge()};
        int[] argTypes = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
        return db2JdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int delete(String emNo) {
        String sql = "delete from tb_employee where EM_NO = ?";
        Object[] args = {emNo};
        int[] argTypes = {Types.VARCHAR};
        return db2JdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int update(Employee employee) {
        String sql = "update tb_employee set EM_NAME = ?, EM_SEX = ?, AGE = ? where EM_NO = ?";
        Object[] args = {employee.getEmName(), employee.getEmSex(), employee.getAge(), employee.getEmNo()};
        int[] argTypes = {Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.VARCHAR};
        return db2JdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public Employee queryById(String emNo) {
        String sql = "select * from tb_employee where EM_NO = ?";
        Object[] args = {emNo};
        int[] argTypes = {Types.VARCHAR};
        return db2JdbcTemplate.queryForObject(sql, args, argTypes, new EmployeeMapper());
    }

    @Override
    public List<Employee> queryList() {
        String sql = "select * from tb_employee";
        return db2JdbcTemplate.query(sql, new EmployeeMapper());
    }
}
