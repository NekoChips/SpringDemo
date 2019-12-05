package com.demo.security.bean.entity;

import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * ClassName: Department <br/>
 * Description: Department 相关<br/>
 * date: 2019/11/25 15:57<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@TableName(value = "tb_dept_info")
public class Department
{
    private static final long serialVersionUID = 5442251786887179460L;

    @TableId(value = "DEPT_ID", type = IdType.AUTO)
    private Integer deptId;

    @TableField(value = "DEPT_NAME", condition = "%s LIKE CONCAT('%%',#{%s},'%%')")
    private String deptName;

    @TableField(value = "DEPT_PID")
    private String deptPId;

    @TableField(exist = false)
    private String deptPName;

    @TableField(value = "DEPT_DESC")
    private String deptDesc;

    @TableField(exist = false)
    private List<User> users;

    @TableField(exist = false)
    private List<Department> childDepts;

    @TableField(value = "DEPT_LEADER_ID")
    private String deptLeaderId;

    @TableField(exist = false)
    private String deptLeaderName;

    @TableField(value = "DEPT_STATUS")
    private Integer deptStatus;

    @TableField(value = "CREATE_TIME")
    private String createTime;

    @TableField(value = "UPDATE_TIME")
    private String updateTime;
}
