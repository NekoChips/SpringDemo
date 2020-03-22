package com.neko.auth.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Yangjie.Chen
 * @description 角色 实体类
 * @date 2020/3/11
 */
@TableName(value = "tb_role")
public class SysRole implements GrantedAuthority {

    private static final long serialVersionUID = -6827906048760709856L;

    @TableId(value = "R_ID", type = IdType.AUTO)
    private Integer roleId;

    @TableField(value = "R_NAME")
    private String roleName;

    // 权限列表
    @TableField(exist = false)
    private Collection<SysAuth> authorities;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Collection<SysAuth> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<SysAuth> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getAuthority() {
        return this.roleName;
    }
}
