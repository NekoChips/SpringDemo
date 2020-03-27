package com.demo.freemarker.controller;

import com.demo.freemarker.bean.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NekoChips
 * @description Student Controller
 * @date 2020/3/27
 */
@Controller
@RequestMapping("student")
public class StudentController {

    @GetMapping("index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/index");
        List<Student> students = new ArrayList<>();
        students.add(new Student("001", "Jack", "M"));
        students.add(new Student("002", "Jim", "M"));
        students.add(new Student("003", "Julia", "F"));
        students.add(new Student("004", "Jane", "F"));

        modelAndView.addObject("students", students);
        return modelAndView;
    }
}
