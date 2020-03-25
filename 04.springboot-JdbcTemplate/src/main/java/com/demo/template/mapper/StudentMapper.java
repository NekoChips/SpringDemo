package com.demo.template.mapper;

import com.demo.template.bean.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author NekoChips
 * @description Student 映射类
 * @date 2020/3/25
 */
public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setStudentId(rs.getString("S_ID"));
        student.setName(rs.getString("S_NAME"));
        student.setSex(rs.getString("SEX"));

        return student;
    }
}
