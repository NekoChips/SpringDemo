package com.demo.sso.auth.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author Yangjie.Chen
 * @description 角色权限关联
 * @date 2020/3/12
 */
@TableName(value = "tb_role_perm")
public class RoleAuth implements Serializable {

    private static final long serialVersionUID = 6850795575488040800L;

    @TableField(value = "R_ID")
    private Integer roleId;

    @TableField(value = "PERM_ID")
    private Integer authId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }
}
