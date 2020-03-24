package com.demo.mybatis.bean;

import java.io.Serializable;

/**
 * @author NekoChips
 * @description 用户实体类
 * @date 2020/3/24
 */
public class User implements Serializable {

    private static final long serialVersionUID = 7025411727424804277L;

    private String userId;

    private String username;

    private String loginTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + username + '\'' +
                ", loginTime='" + loginTime + '\'' +
                '}';
    }
}
