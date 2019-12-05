package com.demo.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.security.bean.entity.Permission;
import com.demo.security.bean.entity.Role;
import com.demo.security.bean.entity.RolePermission;
import com.demo.security.dao.RoleDao;
import com.demo.security.dao.RolePermissionDao;
import com.demo.security.dao.UserRoleDao;
import com.demo.security.service.PermissionService;
import com.demo.security.service.RoleService;

/**
 * ClassName: RoleServiceImpl <br/>
 * Description: RoleServiceImpl 相关<br/>
 * date: 2019/11/26 17:19<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService
{
    private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Autowired
    private PermissionService permissionService;

    @Override
    public int addOneRole(Role role)
    {
        return 0;
    }

    @Override
    public int updateRole(Role role)
    {
        return 0;
    }

    @Override
    public int removeRole(Integer roleId)
    {
        return 0;
    }

    @Override
    public Role queryById(Integer roleId)
    {
        Role role = roleDao.selectById(roleId);
        List<Permission> perms = new ArrayList<>();
        RolePermission rpParams = new RolePermission();
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>(rpParams);
        List<RolePermission> rolePermissions = rolePermissionDao.selectList(wrapper);
        if (!CollectionUtils.isEmpty(rolePermissions))
        {
            rolePermissions.forEach(rolePermission ->
            {
                perms.add(permissionService.queryById(rolePermission.getPermId()));
            });
        }
        role.setPermissions(perms);
        return role;
    }

    @Override
    public Role queryByName(String roleName)
    {
        return null;
    }

    @Override
    public List<Role> queryRolesByUser(String userId)
    {
        return null;
    }

    @Override
    public int removeRoles(List<Integer> roleIds)
    {
        return 0;
    }

    @Override
    public int setPermissions(Integer roleId, List<Integer> permIds)
    {
        return 0;
    }
}
