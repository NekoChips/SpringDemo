package com.demo.oauth2.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.demo.oauth2.constants.CommonConstant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Yangjie.Chen
 * @description 用户 实体类
 * @date 2020/3/12
 */
@TableName(value = "tb_user_info")
public class UserEntity implements UserDetails {

    private static final long serialVersionUID = -517800012387095551L;

    @TableId(value = "U_ID", type = IdType.ASSIGN_UUID)
    private String userId;

    @TableField(value = "U_NAME")
    private String username;

    @TableField(value = "U_PASSWORD")
    private String password;

    // -1： 过期; 0： 锁定； 1：启用
    @TableField(value = "U_STATUS")
    private Integer status;

    // 角色列表
    @TableField(exist = false)
    private Collection<RoleEntity> roles;

    // 权限列表
    @TableField(exist = false)
    private Collection<AuthorityEntity> authorities;

    public UserEntity() {

    }

    public UserEntity(String username, String password, Integer status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Collection<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles;
    }

    public void setAuthorities(Collection<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }

    // 获取用户的权限，这里可以配置基于方法的权限，也可以配置基于角色的权限
    // 同样可以把两个都配置进去
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.addAll(this.authorities);
        grantedAuthorities.addAll(this.roles);
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // 用户是否过期,过期返回false, 没过期返回true
    @Override
    public boolean isAccountNonExpired() {
        return !this.status.equals(CommonConstant.EXPIRED.getValue());
    }

    // 用户是否被锁住,锁定返回false，没锁定返回true
    @Override
    public boolean isAccountNonLocked() {
        return !this.status.equals(CommonConstant.LOCKED.getValue());
    }

    // 判断用户的凭证是否过期， 用于 refresh_token
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 判断用户是否可用
    @Override
    public boolean isEnabled() {
        return this.status.equals(CommonConstant.VALID.getValue());
    }

}
