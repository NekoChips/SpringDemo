package com.demo.security.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Yangjie.Chen
 * @description 权限 实体类
 * @date 2020/3/11
 */
@TableName(value = "tb_permission")
public class AuthorityEntity implements GrantedAuthority {

    @TableId(value = "PERM_ID", type = IdType.AUTO)
    private Integer authId;

    @TableField(value = "PERM_NAME")
    private String authName;

    @Override
    public String getAuthority() {
        return this.authName;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }
}
