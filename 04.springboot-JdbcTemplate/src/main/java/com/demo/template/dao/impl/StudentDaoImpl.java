package com.demo.template.dao.impl;

import com.demo.template.bean.Student;
import com.demo.template.dao.IStudentDao;
import com.demo.template.mapper.StudentMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Map;

/**
 * @author NekoChips
 * @description IStudentDao 实现类
 * @date 2020/3/25
 */
@Repository
public class StudentDaoImpl implements IStudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Student student) {
        String sql = "insert into tb_student (S_ID, S_NAME, SEX) values (?, ?, ?)";
        Object[] args = {student.getStudentId(), student.getName(), student.getSex()};
        int[] argTypes = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        return jdbcTemplate.update(sql, args, argTypes);

//        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
//                new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
//        return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(student));
    }

    @Override
    public int delete(String studentId) {
        String sql = "delete from tb_student where S_ID = ?";
        Object[] args = {studentId};
        int[] argTypes = {Types.VARCHAR};
        return jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int update(Student student) {
        String sql = "update tb_student set S_NAME = ?, SEX = ? where S_ID = ?";
        Object[] args = {student.getName(), student.getSex(), student.getStudentId()};
        int[] argTypes = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        return jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public Student queryById(String studentId) {
        String sql = "select * from tb_student where S_ID = ?";
        Object[] args = {studentId};
        int[] argTypes = {Types.VARCHAR};
        return jdbcTemplate.queryForObject(sql, args, argTypes, new StudentMapper());
    }

    @Override
    public List<Student> queryList() {
        String sql = "select * from tb_student";
        return jdbcTemplate.query(sql, new StudentMapper());
    }
}
