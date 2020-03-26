package com.demo.log.bean;

import java.io.Serializable;

/**
 * @author NekoChips
 * @description 员工实体类
 * @date 2020/3/25
 */
public class Employee implements Serializable {

    private static final long serialVersionUID = 612710393041476403L;

    /**
     * 编号
     */
    private String emNo;

    /**
     * 姓名
     */
    private String emName;

    /**
     * 性别
     */
    private String emSex;

    /**
     * 年龄
     */
    private int age;

    public String getEmNo() {
        return emNo;
    }

    public void setEmNo(String emNo) {
        this.emNo = emNo;
    }

    public String getEmName() {
        return emName;
    }

    public void setEmName(String emName) {
        this.emName = emName;
    }

    public String getEmSex() {
        return emSex;
    }

    public void setEmSex(String emSex) {
        this.emSex = emSex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emNo='" + emNo + '\'' +
                ", emName='" + emName + '\'' +
                ", emSex='" + emSex + '\'' +
                ", age=" + age +
                '}';
    }
}
