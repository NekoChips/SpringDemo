package com.demo.template.service.impl;

import com.demo.template.bean.Student;
import com.demo.template.dao.IStudentDao;
import com.demo.template.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author NekoChips
 * @description IStudentService 实现类
 * @date 2020/3/25
 */
@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private IStudentDao studentDao;

    @Override
    public int add(Student student) {
        return studentDao.add(student);
    }

    @Override
    public int delete(String studentId) {
        return studentDao.delete(studentId);
    }

    @Override
    public int update(Student student) {
        return studentDao.update(student);
    }

    @Override
    public Student queryById(String studentId) {
        return studentDao.queryById(studentId);
    }

    @Override
    public List<Student> queryList() {
        return studentDao.queryList();
    }
}
