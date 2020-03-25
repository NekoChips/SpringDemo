package com.demo.jpa.service;

import com.demo.jpa.JpaDemoApplication;
import com.demo.jpa.bean.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author NekoChips
 * @description StudentService 测试类
 * @date 2020/3/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaDemoApplication.class)
public class TestStudentService {

    @Autowired
    private IStudentService studentService;

    @Test
    public void addStudent() {
        Student student = new Student();
        student.setStudentId("004");
        student.setName("Mark");
        student.setSex("M");

        try {
            studentService.add(student);
        } catch (Exception e) {
           e.printStackTrace();
        }
        System.out.println("add success");
    }

    @Test
    public void queryStudent() {
        try {
            Student student = studentService.queryById("004");
            System.out.println(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateStudent() {
        Student student;
        try {
            student = studentService.queryById("004");
            if (Optional.ofNullable(student).isPresent()) {
                student.setName("Jane");
                student.setSex("F");
            }
            studentService.update(student);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("update success");
    }

    @Test
    public void queryList() {
        List<Student> students = null;
        try {
            students = studentService.queryList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!CollectionUtils.isEmpty(students)) {
            students.forEach(System.out::println);
        }
    }

    @Test
    public void delete() {
        try {
            studentService.delete("004");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("delete success");
    }

    @Test
    public void queryByName() {
        String name = "Jane";
        try {
            Student student = studentService.queryByName(name);
            System.out.println(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryListBySex() {
        try {
            List<Student> students = studentService.queryListBySex("F");
            if (!CollectionUtils.isEmpty(students)) {
                students.forEach(System.out::println);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
