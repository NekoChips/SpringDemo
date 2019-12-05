package com.demo.security.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.demo.security.bean.entity.User;
import com.demo.security.bean.entity.UserRole;
import com.demo.security.service.UserService;

/**
 * ClassName: UserController <br/>
 * Description: UserController 相关<br/>
 * date: 2019/12/5 13:08<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("user")
public class UserController
{
    @Autowired
    private UserService userService;

    @RequestMapping("setRoles")
    public JSONObject setRoles(@RequestBody UserRole userRole)
    {
        String userId = userRole.getUserId();
        Integer roleId = userRole.getRoleId();
        List<Integer> roleIds = Arrays.asList(roleId);
        userService.setRoles(userId, roleIds);

        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("codeDesc", "success");
        return result;
    }

    @GetMapping("info/{userId}")
    @PreAuthorize("hasAuthority('user:info')")
    public User queryUserById(@PathVariable String userId)
    {
        return userService.queryUserById(userId);
    }

}
