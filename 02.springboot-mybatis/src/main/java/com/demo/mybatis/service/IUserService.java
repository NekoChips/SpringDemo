package com.demo.mybatis.service;

import com.demo.mybatis.bean.User;

/**
 * @author NekoChips
 * @description 用户接口
 * @date 2020/3/24
 */
public interface IUserService {

    /**
     * 查询用户信息
     * @param userId 用户id
     * @return
     */
    User queryById(String userId);

    /**
     * 添加用户
     * @param user 用户信息
     * @return
     */
    int addUser(User user);

    /**
     * 更新用户
     * @param user 用户信息
     * @return
     */
    int update(User user);

    /**
     * 删除用户
     * @param userId 用户id
     * @return
     */
    int remove(String userId);
}
