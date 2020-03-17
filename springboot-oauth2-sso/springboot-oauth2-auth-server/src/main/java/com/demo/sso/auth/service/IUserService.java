package com.demo.sso.auth.service;

import com.demo.sso.auth.bean.AuthorityEntity;
import com.demo.sso.auth.bean.RoleEntity;

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
