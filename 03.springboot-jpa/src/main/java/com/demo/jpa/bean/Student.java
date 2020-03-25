package com.demo.jpa.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author NekoChips
 * @description 学生实体类
 * @date 2020/3/25
 */
@Entity(name = "tb_student")
public class Student implements Serializable {

    private static final long serialVersionUID = 6029454568343571843L;

    @Id
    @Column(name = "S_ID")
    private String studentId;

    @Column(name = "S_NAME")
    private String name;

    @Column(name = "SEX")
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
