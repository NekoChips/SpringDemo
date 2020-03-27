package com.demo.freemarker.bean;

import java.io.Serializable;

/**
 * @author NekoChips
 * @description 学生类
 * @date 2020/3/27
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 6938816239203109L;

    private String studentId;

    private String name;

    private String sex;

    public Student() {

    }

    public Student(String studentId, String name, String sex) {
        this.studentId = studentId;
        this.name = name;
        this.sex = sex;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
