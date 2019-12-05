package com.demo.security.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.security.bean.entity.Permission;
import com.demo.security.dao.PermissionDao;
import com.demo.security.dao.RolePermissionDao;
import com.demo.security.service.PermissionService;

/**
 * ClassName: PermissionServiceImpl <br/>
 * Description: PermissionServiceImpl 相关<br/>
 * date: 2019/11/26 17:19<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl implements PermissionService
{
    private Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public int addOnePermission(Permission permission)
    {
        return 0;
    }

    @Override
    public int updatePermission(Permission permission)
    {
        return 0;
    }

    @Override
    public int deletePermission(List<Integer> perIds)
    {
        return 0;
    }

    @Override
    public Permission queryById(Integer perId)
    {
        return permissionDao.selectById(perId);
    }

    @Override
    public Permission queryByName(String permName)
    {
        return null;
    }

    @Override
    public List<Permission> queryListByRole(Integer roleId)
    {
        return null;
    }
}
