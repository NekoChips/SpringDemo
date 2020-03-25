package com.demo.template.service;

import com.demo.template.bean.Student;

import java.util.List;

/**
 * @author NekoChips
 * @description student 逻辑处理层接口
 * @date 2020/3/25
 */
public interface IStudentService {

    /**
     * 新增学生信息
     * @param student 学生信息
     * @return
     */
    int add(Student student);

    /**
     * 删除学生信息
     * @param studentId 学生id
     * @return
     */
    int delete(String studentId);

    /**
     * 更新学生信息
     * @param student 学生信息
     * @return
     */
    int update(Student student);

    /**
     * 查询学生信息
     * @param studentId 学生id
     * @return
     */
    Student queryById(String studentId);

    /**
     * 查询所有学生信息的学生类列表
     * @return
     */
    List<Student> queryList();
}
