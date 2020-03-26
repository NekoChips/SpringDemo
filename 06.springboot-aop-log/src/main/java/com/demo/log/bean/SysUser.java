package com.demo.log.bean;

import java.io.Serializable;

/**
 * @author NekoChips
 * @description 系统用户类
 * @date 2020/3/26
 */
public class SysUser implements Serializable {

    private static final long serialVersionUID = -538622436653400789L;

    private String userId;

    private String username;

    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
