package com.demo.mybatis.service;

import com.demo.mybatis.MybatisDemoApplication;
import com.demo.mybatis.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author NekoChips
 * @description 测试用户接口
 * @date 2020/3/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisDemoApplication.class)
public class TestUserService {

    @Autowired
    private IUserService userService;

    @Test
    public void queryUser() {
        String userId = "dda8b61a31605d197ea48e511f698da7";
        User user = userService.queryById(userId);
        System.out.println(user);
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setUserId("10001");
        user.setUsername("stranger");
        int count = userService.addUser(user);
        System.out.println(count > 0 ? "add user success" : "add user fail");
    }

    @Test
    public void updateUser() {
        User user = userService.queryById("10001");
        if (user != null) {
            user.setLoginTime("2019-12-25 00:00:00");
            userService.addUser(user);
            System.out.println("update user success");
        }
        System.out.println("user not exist");
    }

    @Test
    public void deleteUser() {
        int count = userService.remove("10001");
        System.out.println(count > 0 ? "delete user success" : "delete user fail");
    }
}
