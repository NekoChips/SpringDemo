package com.demo.security.bean.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * ClassName: Role <br/>
 * Description: Role 相关<br/>
 * date: 2019/11/25 15:57<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@TableName("tb_role")
public class Role implements Serializable
{
    private static final long serialVersionUID = -3913946660911517895L;

    @TableId(value = "R_ID", type = IdType.AUTO)
    private Integer roleId;

    @TableField(value = "R_NAME", condition = "%s LIKE CONCAT('%%',#{%s},'%%')")
    private String roleName;

    @TableField(value = "R_DESC")
    private String roleDesc;

    @TableField(value = "R_LEVEL")
    private String roleLevel;

    @TableField(exist = false)
    private List<Permission> permissions;

    @TableField(value = "R_STATUS")
    private Integer roleStatus;

    @TableField(value = "CREATE_TIME")
    private String createTime;

    @TableField(value = "UPDATE_TIME")
    private String updateTime;
}
