package com.demo.security.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.security.bean.entity.Permission;
import com.demo.security.bean.entity.Role;
import com.demo.security.bean.entity.User;
import com.demo.security.bean.entity.UserRole;
import com.demo.security.dao.UserDao;
import com.demo.security.dao.UserDeptDao;
import com.demo.security.dao.UserRoleDao;
import com.demo.security.service.DeptService;
import com.demo.security.service.RoleService;
import com.demo.security.service.UserService;

/**
 * ClassName: UserServiceImpl <br/>
 * Description: UserServiceImpl 相关<br/>
 * date: 2019/11/25 16:03<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class UserServiceImpl implements UserService
{
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDeptDao userDeptDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private DeptService deptService;

    @Autowired
    private RoleService roleService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public int saveOneUser(User user)
    {
        return 0;
    }

    @Override
    public boolean saveUsers(List<User> users)
    {
        return false;
    }

    @Override
    public int updateUser(User user)
    {
        return 0;
    }

    @Override
    public int deleteUser(String userId)
    {
        return 0;
    }

    @Override
    public boolean deleteUsers(String userIds)
    {
        return false;
    }

    @Override
    public User queryUserById(String userId)
    {
        User user = userDao.selectById(userId);
        List<String> roles = new ArrayList<>();
        List<String> perms = new ArrayList<>();
        UserRole urParams = new UserRole(userId);
        QueryWrapper<UserRole> urWrapper = new QueryWrapper<>(urParams);
        List<UserRole> userRoles = userRoleDao.selectList(urWrapper);
        if (!CollectionUtils.isEmpty(userRoles))
        {
            userRoles.forEach(userRole ->
            {
                Role role = roleService.queryById(userRole.getRoleId());
                roles.add(role.getRoleName());
                List<Permission> permissions = role.getPermissions();
                if (!CollectionUtils.isEmpty(permissions))
                {
                    permissions.forEach(permission -> perms.add(permission.getPermName()));
                }
            });
        }
        user.setRoles(roles);
        user.setPermissions(perms);
        return user;
    }

    @Override
    public User queryUserByName(String userName)
    {
        User userParams = new User();
        userParams.setUsername(userName);
        QueryWrapper<User> wrapper = new QueryWrapper<>(userParams);
        User user = userDao.selectOne(wrapper);
        user = queryUserById(user.getUserId());
        return user;
    }

    @Override
    public List<User> queryUserList(Map<String, Object> condition)
    {
        return null;
    }

    @Override
    public int addUserToDept(String userId, Integer deptId)
    {
        return 0;
    }

    @Override
    public int setRoles(String userId, List<Integer> roleIds)
    {
        UserRole userRole = new UserRole(userId, roleIds.get(0));
        return userRoleDao.insert(userRole);
    }
}
