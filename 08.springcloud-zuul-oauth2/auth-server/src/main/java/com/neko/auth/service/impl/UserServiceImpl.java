package com.neko.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neko.auth.bean.SysAuth;
import com.neko.auth.bean.SysRole;
import com.neko.auth.bean.SysUser;
import com.neko.auth.mapper.UserMapper;
import com.neko.auth.service.IAuthService;
import com.neko.auth.service.IRoleService;
import com.neko.auth.service.IUserService;
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
        SysUser user = new SysUser();
        user.setUsername(username);
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>(user);

        SysUser existUser = userMapper.selectOne(wrapper);
        // 设置用户方法级权限
        existUser.setAuthorities(listAuthById(existUser.getUserId()));
        // 设置用户角色级权限
        existUser.setRoles(listRoleById(existUser.getUserId()));

        return new User(username, existUser.getPassword(), existUser.isEnabled(),
                existUser.isAccountNonExpired(), existUser.isCredentialsNonExpired(),
                existUser.isAccountNonLocked(), existUser.getAuthorities());
    }

    @Override
    public List<SysRole> listRoleById(String userId) {
        return roleService.listByUser(userId);
    }

    @Override
    public List<SysAuth> listAuthById(String userId) {
        List<SysRole> roles = listRoleById(userId);
        if (!CollectionUtils.isEmpty(roles)) {
            List<SysAuth> auths = new ArrayList<>();
            roles.forEach(role -> auths.addAll(authService.listByRole(role.getRoleId())));
            return auths;
        }
        return null;
    }
}
