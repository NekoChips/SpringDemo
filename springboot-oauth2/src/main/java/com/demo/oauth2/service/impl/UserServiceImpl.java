package com.demo.oauth2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.oauth2.bean.AuthorityEntity;
import com.demo.oauth2.bean.RoleEntity;
import com.demo.oauth2.bean.UserEntity;
import com.demo.oauth2.dao.UserMapper;
import com.demo.oauth2.service.IAuthService;
import com.demo.oauth2.service.IRoleService;
import com.demo.oauth2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yangjie.Chen
 * @description 用户 service 实现类
 * @date 2020/3/12
 */
@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IAuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * userDetailsService 方法实现
     *
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>(user);

        UserEntity existUser = userMapper.selectOne(wrapper);
        // 设置用户方法级权限
        existUser.setAuthorities(listAuthById(existUser.getUserId()));
        // 设置用户角色级权限
        existUser.setRoles(listRoleById(existUser.getUserId()));

        return new User(username, existUser.getPassword(), existUser.isEnabled(),
                existUser.isAccountNonExpired(), existUser.isCredentialsNonExpired(),
                existUser.isAccountNonLocked(), existUser.getAuthorities());
    }

    @Override
    public List<RoleEntity> listRoleById(String userId) {
        return roleService.listByUser(userId);
    }

    @Override
    public List<AuthorityEntity> listAuthById(String userId) {
        List<RoleEntity> roles = listRoleById(userId);
        if (!CollectionUtils.isEmpty(roles)) {
            List<AuthorityEntity> auths = new ArrayList<>();
            roles.forEach(role -> auths.addAll(authService.listByRole(role.getRoleId())));
            return auths;
        }
        return null;
    }

    @Override
    public boolean saveUser(UserEntity user) {
        return false;
    }
}
