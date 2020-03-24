package com.demo.mybatis.service.impl;

import com.demo.mybatis.bean.User;
import com.demo.mybatis.mapper.UserMapper;
import com.demo.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author NekoChips
 * @description IUserService 实现类
 * @date 2020/3/24
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryById(String userId) {
        return userMapper.queryById(userId);
    }

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int remove(String userId) {
        return userMapper.deleteById(userId);
    }
}
