package com.neko.auth.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author Yangjie.Chen
 * @description 用户角色关联
 * @date 2020/3/12
 */
@TableName(value = "tb_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 2272664752247618992L;

    @TableField(value = "U_ID")
    private String userId;

    @TableField(value = "R_ID")
    private Integer roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
