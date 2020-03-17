package com.demo.sso.auth.service;

import com.demo.sso.auth.bean.RoleEntity;

import java.util.List;

/**
 * @author Yangjie.Chen
 * @description 角色接口
 * @date 2020/3/12
 */
public interface IRoleService {

    /**
     * 用户拥有的角色
     * @param userId 用户id
     * @return
     */
    List<RoleEntity> listByUser(String userId);
}
