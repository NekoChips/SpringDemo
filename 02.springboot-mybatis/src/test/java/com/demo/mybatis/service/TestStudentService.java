package com.demo.mybatis.service;

import com.demo.mybatis.MybatisDemoApplication;
import com.demo.mybatis.bean.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author NekoChips
 * @description StudentService 测试类
 * @date 2020/3/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisDemoApplication.class)
public class TestStudentService {
    @Autowired
    private IStudentService studentService;

    @Test
    public void addStudent() {
        Student student = new Student();
        student.setStudentId("004");
        student.setName("Mark");
        student.setSex("M");

        int count = studentService.add(student);
        System.out.println(count > 0 ? "add success" : "add fail");
    }

    @Test
    public void queryStudent() {
        Student student = studentService.queryById("004");
        System.out.println(student);
    }

    @Test
    public void updateStudent() {
        Student student = studentService.queryById("004");
        student.setName("Jane");
        student.setSex("F");

        int update = studentService.update(student);
        System.out.println(update > 0 ? "update success" : "update fail");
    }

    @Test
    public void queryList() {
        List<Student> students = studentService.queryList();
        students.forEach(System.out::println);
    }

    @Test
    public void delete() {
        int delete = studentService.delete("004");
        System.out.println(delete > 0 ? "delete success" : "delete fail");
    }
}
