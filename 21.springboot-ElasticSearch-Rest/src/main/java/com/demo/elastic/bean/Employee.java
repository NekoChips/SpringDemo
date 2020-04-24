package com.demo.elastic.bean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author NekoChips
 * @description 员工
 * @date 2020/4/22
 */
public class Employee implements Serializable {

    private static final long serialVersionUID = 4115663818759958065L;
    
    private Integer id;

    private String firstName;

    private String lastName;

    private Integer age;

    private String about;

    private String[] interest;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String[] getInterest() {
        return interest;
    }

    public void setInterest(String[] interest) {
        this.interest = interest;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", about='" + about + '\'' +
                ", interest=" + Arrays.toString(interest) +
                '}';
    }
}
