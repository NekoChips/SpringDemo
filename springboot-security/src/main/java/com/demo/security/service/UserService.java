package com.demo.security.service;

import java.util.List;
import java.util.Map;

import com.demo.security.bean.entity.User;

/**
 * ClassName: UserService <br/>
 * Description: UserService 相关<br/>
 * date: 2019/11/25 16:02<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
public interface UserService
{
    /**
     * 保存一个用户的信息
     * @param user 用户信息
     * @return
     */
    int saveOneUser(User user);

    /**
     * 批量保存用户信息
     * @param users 用户信息列表
     * @return
     */
    boolean saveUsers(List<User> users);

    /**
     * 更新用户信息
     * @param user 更新后的用户信息
     * @return
     */
    int updateUser(User user);

    /**
     * 删除用户信息
     * @param userId 用户id
     * @return
     */
    int deleteUser(String userId);

    /**
     * 批量删除用户
     * @param userIds 所有用户的id，用“,”分隔
     * @return
     */
    boolean deleteUsers(String userIds);

    /**
     * 通过用户id查询用户信息
     * @param userId 用户id
     * @return
     */
    User queryUserById(String userId);

    /**
     * 通过用户名查询用户
     * @param userName 用户名
     * @return
     */
    User queryUserByName(String userName);

    /**
     * 根据查询条件查询用户列表
     * @param condition 查询条件
     * @return
     */
    List<User> queryUserList(Map<String, Object> condition);

    /**
     * 添加用户至部门
     * @param userId 用户id
     * @param deptId 部门id
     * @return
     */
    int addUserToDept(String userId, Integer deptId);

    /**
     * 给用户设置角色
     * @param userId 用户id
     * @param roleIds 角色id列表
     * @return
     */
    int setRoles(String userId, List<Integer> roleIds);
}
