package com.neko.auth.service;

import com.neko.auth.bean.SysAuth;
import com.neko.auth.bean.SysRole;

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
    List<SysRole> listRoleById(String userId);

    /**
     * 用户拥有的权限
     * @param userId 用户id
     * @return
     */
    List<SysAuth> listAuthById(String userId);
}
