package com.demo.security.bean.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * ClassName: RolePermission <br/>
 * Description: RolePermission 相关<br/>
 * date: 2019/11/27 17:28<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@TableName("tb_role_perm")
public class RolePermission implements Serializable
{
    private static final long serialVersionUID = -2737449917421492749L;

    @TableField(value = "R_ID")
    private Integer roleId;

    @TableField(value = "PERM_ID")
    private Integer permId;

    public RolePermission()
    {
    }

    public RolePermission(Integer roleId)
    {
        this.roleId = roleId;
    }

    public RolePermission(Integer roleId, Integer perId)
    {
        this.roleId = roleId;
        this.permId = perId;
    }
}
