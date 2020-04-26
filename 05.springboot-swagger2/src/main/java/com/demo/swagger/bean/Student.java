package com.demo.swagger.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;

/**
 * @author NekoChips
 * @description 学生实体类
 * @date 2020/3/25
 */
@ApiModel(value = "学生信息")
public class Student implements Serializable {

    private static final long serialVersionUID = 6029454568343571843L;

    @ApiParam(value = "学生 id")
    private String studentId;

    @ApiParam(value = "学生姓名")
    private String name;

    @ApiParam(value = "学生性别")
    private String sex;


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
