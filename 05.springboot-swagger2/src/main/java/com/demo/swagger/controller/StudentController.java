package com.demo.swagger.controller;

import com.demo.swagger.bean.Student;
import com.demo.swagger.service.IStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

/**
 * @author NekoChips
 * @description Student Controller
 * @date 2020/3/26
 */
@Api(value = "学生 Controller")
@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @ApiOperation(value = "新增学生信息", notes = "根据学生实体创建学生对象")
    @ApiImplicitParam(name = "student", value = "学生信息", required = true, dataTypeClass = Student.class)
    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody Student student) {
        int count = studentService.add(student);
        return count > 0 ?
                ResponseEntity.ok("add student success") :
                ResponseEntity.of(Optional.of("add student fail"));
    }

    @ApiOperation(value = "删除学生信息", notes = "根据学生id删除学生信息")
    @ApiImplicitParam(name = "studentId", value = "学生id", required = true, dataTypeClass = String.class)
    @DeleteMapping("delete")
    public HttpEntity<?> delete(String studentId) {
        int count = studentService.delete(studentId);
        return count > 0 ?
                ResponseEntity.ok("delete student success") :
                ResponseEntity.of(Optional.of("delete student fail"));
    }

    @ApiOperation(value = "更新学生信息", notes = "根据学生id，更新用户信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "studentId", value = "学生id", required = true, dataType = "String", paramType =
                    "path"),
            @ApiImplicitParam(name = "student", value = "学生信息", required = true, dataType = "Student")
    })
    @PutMapping("/{studentId}")
    public HttpEntity<?> update(@PathVariable("studentId") String studentId, @RequestBody() Student student) {
        student.setStudentId(studentId);
        int count = studentService.update(student);
        return count > 0 ?
                ResponseEntity.ok("update student success") :
                ResponseEntity.of(Optional.of("update student fail"));
    }

    @ApiOperation(value = "查询学生信息", notes = "根据学生id查询学生信息")
    @ApiImplicitParam(name = "studentId", value = "学生id", required = true, dataType = "String")
    @GetMapping("info")
    public HttpEntity<?> queryById(String studentId) {
        Student student = studentService.queryById(studentId);
        return ResponseEntity.ok(student);
    }

    @ApiOperation(value = "查询学生列表", notes = "查询学生列表")
    @GetMapping("list")
    public HttpEntity<?> queryList() {
        List<Student> students = studentService.queryList();
        return ResponseEntity.ok(students);
    }

    @ApiIgnore
    @GetMapping("test")
    public HttpEntity<?> test() {
        return ResponseEntity.ok("Hello, this is a test!");
    }
}
