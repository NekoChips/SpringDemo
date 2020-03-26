package com.demo.swagger.service;

import com.demo.swagger.bean.Student;

import java.util.List;

/**
 * @author NekoChips
 * @description Student 逻辑处理层接口
 * @date 2020/3/24
 */
public interface IStudentService {
    /**
     * 新增学生信息
     *
     * @param student 学生信息
     * @return
     */
    int add(Student student);

    /**
     * 删除学生信息
     *
     * @param studentId 学生id
     * @return
     */
    int delete(String studentId);

    /**
     * 更新学生信息
     *
     * @param student 学生信息
     * @return
     */
    int update(Student student);

    /**
     * 查询学生信息
     *
     * @param studentId 学生id
     * @return
     */
    Student queryById(String studentId);

    /**
     * 查询学生列表
     *
     * @return
     */
    List<Student> queryList();
}
