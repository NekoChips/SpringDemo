package com.demo.jpa.service.impl;

import com.demo.jpa.bean.Student;
import com.demo.jpa.dao.StudentDao;
import com.demo.jpa.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author NekoChips
 * @description IStudentService 实现类
 * @date 2020/3/25
 */
@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public void add(Student student) throws Exception {
        studentDao.save(student);
    }

    @Override
    public void delete(String studentId) throws Exception {
        studentDao.deleteById(studentId);
    }

    @Override
    public void update(Student student) throws Exception {
        studentDao.saveAndFlush(student);
    }

    @Override
    public Student queryById(String studentId) throws Exception{
        Optional<Student> optional = studentDao.findById(studentId);
        return optional.orElse(null);
    }

    @Override
    public Student queryByName(String name) throws Exception{
        return studentDao.findByName(name);
    }

    @Override
    public List<Student> queryList() throws Exception{
        return studentDao.findAll();
    }

    @Override
    public List<Student> queryListBySex(String sex) throws Exception {
        return studentDao.queryListBySex(sex);
    }
}
