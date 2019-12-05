package com.demo.security.service;

import java.util.List;

import com.demo.security.bean.entity.Permission;

/**
 * ClassName: PermissionService <br/>
 * Description: PermissionService 相关<br/>
 * date: 2019/11/26 17:18<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
public interface PermissionService
{
    /**
     * 新增权限
     * @param permission 权限信息
     * @return
     */
    int addOnePermission(Permission permission);

    /**
     * 更改权限信息
     * @param permission 更新后的权限信息
     * @return
     */
    int updatePermission(Permission permission);

    /**
     * 删除权限
     * @param perIds 权限id列表
     * @return
     */
    int deletePermission(List<Integer> perIds);

    /**
     * 根据id查询权限
     * @param perId 权限id
     * @return
     */
    Permission queryById(Integer perId);

    /**
     * 通过名称查询权限
     * @param permName 权限名称
     * @return
     */
    Permission queryByName(String permName);

    /**
     * 查询角色拥有的权限先列表
     * @param roleId 角色id
     * @return
     */
    List<Permission> queryListByRole(Integer roleId);

}
