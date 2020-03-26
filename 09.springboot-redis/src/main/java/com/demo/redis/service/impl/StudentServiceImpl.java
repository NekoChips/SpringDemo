package com.demo.redis.service.impl;

import com.demo.redis.bean.Student;
import com.demo.redis.mapper.StudentMapper;
import com.demo.redis.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author NekoChips
 * @description IStudentService 实现类
 * @date 2020/3/24
 */
@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private StudentMapper studentMapper;

    @CachePut(key = "#p0.studentId")
    @Override
    public int add(Student student) {
        return studentMapper.add(student);
    }

    @Override
    public int delete(String studentId) {
        return studentMapper.delete(studentId);
    }

    @Override
    public int update(Student student) {
        return studentMapper.update(student);
    }

    @Override
    public Student queryById(String studentId) {
        return studentMapper.queryById(studentId);
    }

    @Override
    public List<Student> queryList() {
        return studentMapper.queryList();
    }
}
