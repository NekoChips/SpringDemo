package com.demo.security.bean.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * ClassName: UserRole <br/>
 * Description: UserRole 相关<br/>
 * date: 2019/11/27 16:49<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@TableName("tb_user_role")
public class UserRole implements Serializable
{
    private static final long serialVersionUID = -541632262632024841L;

    @TableField(value = "U_ID")
    private String userId;

    @TableField(value = "R_ID")
    private Integer roleId;

    public UserRole()
    {
    }

    public UserRole(String userId)
    {
        this.userId = userId;
    }

    public UserRole(String userId, Integer roleId)
    {
        this.userId = userId;
        this.roleId = roleId;
    }
}
