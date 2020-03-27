package com.demo.redis.controller;

import com.demo.redis.annotation.Idempotence;
import com.demo.redis.bean.Student;
import com.demo.redis.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author NekoChips
 * @description 学生 Controller
 * @date 2020/3/27
 */
@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody Student student) {
        int count = studentService.add(student);
        return count > 0 ?
                ResponseEntity.ok("add student success") :
                ResponseEntity.of(Optional.of("add student fail"));
    }

    @DeleteMapping("delete")
    public HttpEntity<?> delete(String studentId) {
        int count = studentService.delete(studentId);
        return count > 0 ?
                ResponseEntity.ok("delete student success") :
                ResponseEntity.of(Optional.of("delete student fail"));
    }

    @PutMapping("/{studentId}")
    public HttpEntity<?> update(@PathVariable("studentId") String studentId, @RequestBody() Student student) {
        student.setStudentId(studentId);
        int count = studentService.update(student);
        return count > 0 ?
                ResponseEntity.ok("update student success") :
                ResponseEntity.of(Optional.of("update student fail"));
    }

    @GetMapping("info")
    @Idempotence
    public HttpEntity<?> queryById(String studentId) {
        Student student = studentService.queryById(studentId);
        return ResponseEntity.ok(student);
    }

    @GetMapping("list")
    public HttpEntity<?> queryList() {
        List<Student> students = studentService.queryList();
        return ResponseEntity.ok(students);
    }

}
