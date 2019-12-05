package com.demo.security.bean.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * ClassName: Permission <br/>
 * Description: Permission 相关<br/>
 * date: 2019/11/25 15:58<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@TableName("tb_permission")
public class Permission implements Serializable
{
    private static final long serialVersionUID = 2049354677314992513L;

    @TableId(value = "PERM_ID", type = IdType.AUTO)
    private Integer permId;

    @TableField(value = "PERM_NAME", condition = "%s LIKE CONCAT('%%',#{%s},'%%')")
    private String permName;

    @TableField(value = "PERM_URL")
    private String permUrl;

    @TableField(value = "PERM_TYPE")
    private String permType;

    @TableField(value = "PERM_METHOD")
    private String permMethod;

    @TableField(value = "PERM_DESC")
    private String permDesc;

    @TableField(value = "CREATE_TIME")
    private String createTime;

    @TableField(value = "UPDATE_TIME")
    private String updateTime;
}
