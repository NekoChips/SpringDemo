package com.demo.template.mapper;

import com.demo.template.bean.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author NekoChips
 * @description 员工信息映射类
 * @date 2020/3/25
 */
public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setEmNo(rs.getString("EM_NO"));
        employee.setEmName(rs.getString("EM_NAME"));
        employee.setEmSex(rs.getString("EM_SEX"));
        employee.setAge(rs.getInt("AGE"));

        return employee;
    }
}
