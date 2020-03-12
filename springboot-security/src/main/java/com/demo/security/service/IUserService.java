package com.demo.security.service;

import com.demo.security.bean.AuthorityEntity;
import com.demo.security.bean.RoleEntity;

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
}
