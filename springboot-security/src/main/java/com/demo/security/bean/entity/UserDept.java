package com.demo.security.bean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * ClassName: UserDept <br/>
 * Description: 用户部门中间表 相关<br/>
 * date: 2019/11/26 14:52<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@TableName("tb_user_dept")
public class UserDept
{
    @TableField(value = "U_ID")
    private String userId;

    @TableField(value = "DEPT_ID")
    private Integer deptId;

    public UserDept()
    {
    }
    
    public UserDept(String userId, Integer deptId)
    {
        this.userId = userId;
        this.deptId = deptId;
    }
}
