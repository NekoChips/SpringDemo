package com.demo.jpa.service;

import com.demo.jpa.bean.Student;

import java.util.List;

/**
 * @author NekoChips
 * @description student 逻辑处理层接口
 * @date 2020/3/25
 */
public interface IStudentService {

    /**
     * 新增学生信息
     *
     * @param student 学生信息
     * @return
     */
    void add(Student student) throws Exception;

    /**
     * 删除学生信息
     *
     * @param studentId 学生id
     * @return
     */
    void delete(String studentId) throws Exception;

    /**
     * 更新学生信息
     *
     * @param student 学生信息
     * @return
     */
    void update(Student student) throws Exception;

    /**
     * 查询学生信息
     *
     * @param studentId 学生id
     * @return
     */
    Student queryById(String studentId) throws Exception;

    /**
     * 查询学生信息
     * @param name 学生姓名
     * @return
     */
    Student queryByName(String name) throws Exception;

    /**
     * 查询所有学生信息的学生类列表
     *
     * @return
     */
    List<Student> queryList() throws Exception;

    /**
     * 查询学生信息列表
     * @param sex 学生性别
     * @return
     */
    List<Student> queryListBySex(String sex) throws Exception;
}
