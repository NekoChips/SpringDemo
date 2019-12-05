package com.demo.security.bean.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * ClassName: User <br/>
 * Description: User 相关<br/>
 * date: 2019/11/25 15:56<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@TableName(value = "tb_user_info")
public class User implements Serializable
{
    private static final long serialVersionUID = -8741624652545200976L;

    @TableId(value = "U_ID", type = IdType.UUID)
    private String userId;

    @TableField(value = "U_NAME", condition = "%s LIKE CONCAT('%%',#{%s},'%%')")
    private String username;

    @JSONField(serialize = false)
    @JsonIgnore
    @TableField(value = "U_PASSWORD")
    private String password;

    @TableField(exist = false)
    private List<String> roles;

    @TableField(exist = false)
    private List<String> permissions;

    @TableField(value = "U_STATUS")
    private Integer userStatus;

    @TableField(value = "U_DEPT_ID")
    private Integer deptId;

    @TableField(exist = false)
    private String deptName;

    @TableField(exist = false)
    private String deptLeaderId;

    @TableField(exist = false)
    private String deptLeaderName;

    @TableField(value = "LAST_LOGIN_ADDRESS")
    private String lastLoginAddress;

    @TableField(value = "LAST_LOGIN_TIME")
    private String lastLoginTime;

    @TableField(value = "CREATE_TIME")
    private String createTime;

    @TableField(value = "UPDATE_TIME")
    private String updateTime;

    public boolean isAdmin()
    {
        return "admin".equals(this.username.trim().toLowerCase());
    }

    public List<String> getRoles()
    {
        return this.roles != null ? roles : new ArrayList<>();
    }

    public List<String> getPermissions()
    {
        return this.permissions != null ? permissions : new ArrayList<>();
    }
}
