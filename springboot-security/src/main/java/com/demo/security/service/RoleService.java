package com.demo.security.service;

import java.util.List;
import java.util.Map;

import com.demo.security.bean.entity.Role;

/**
 * ClassName: RoleService <br/>
 * Description: RoleService 相关<br/>
 * date: 2019/11/26 17:18<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
public interface RoleService
{
    /**
     * 新增角色
     * @param role 角色信息
     * @return
     */
    int addOneRole(Role role);

    /**
     * 更新角色信息
     * @param role 更新后的角色信息
     * @return
     */
    int updateRole(Role role);

    /**
     * 删除角色（物理删除）
     * @param roleId 角色id
     * @return
     */
    int removeRole(Integer roleId);

    /**
     * 根据id查询角色
     * @param roleId 角色id
     * @return
     */
    Role queryById(Integer roleId);

    /**
     * 根据名称查询角色
     * @param roleName 角色名称
     * @return
     */
    Role queryByName(String roleName);

    /**
     * 根据用户id查询用户的角色列表
     * @param userId 用户id
     * @return
     */
    List<Role> queryRolesByUser(String userId);

    /**
     * 批量删除角色（物理删除）
     * @param roleIds 角色id集合
     * @return
     */
    int removeRoles(List<Integer> roleIds);

    /**
     * 设置角色的权限
     * @param roleId    角色id
     * @param permIds   权限id列表
     * @return
     */
    int setPermissions(Integer roleId, List<Integer> permIds);
}
