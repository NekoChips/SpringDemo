package com.demo.oauth2.service;

import com.demo.oauth2.bean.AuthorityEntity;
import com.demo.oauth2.bean.RoleEntity;
import com.demo.oauth2.bean.UserEntity;

import java.util.List;

/**
 * @author Yangjie.Chen
 * @description 用户接口
 * @date 2020/3/12
 */
public interface IUserService {
    /**
     * 用户拥有的角色
     * @param userId 用户id
     * @return
     */
    List<RoleEntity> listRoleById(String userId);

    /**
     * 用户拥有的权限
     * @param userId 用户id
     * @return
     */
    List<AuthorityEntity> listAuthById(String userId);

    /**
     * 新增用户
     * @param user 用户信息
     * @return
     */
    boolean saveUser(UserEntity user);
}
