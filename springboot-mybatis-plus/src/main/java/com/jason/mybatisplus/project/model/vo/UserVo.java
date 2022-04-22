package com.jason.mybatisplus.project.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author WongChenHoll
 * @date 2022-4-11 21:50
 **/
@TableName(value = "SBI_USER")
public class UserVo extends BaseModel {

    @TableField(value = "USER_ID")
    private String userId; // 用户编号

    @TableField(value = "USER_NAME")
    private String userName; // 用户姓名

    @TableField(value = "AGE")
    private int age; // 年龄

    @TableField(value = "PHONE")
    private String phone; // 手机号/电话号码

    @TableField(value = "EMAIL")
    private String email; // 邮箱

    @TableField(value = "DEPT_ID")
    private String deptId; // 部门编号

    @TableField(value = "STATUS")
    private String status; // 任职状态，0：离职，1:在职，2：观察，3：其他

    @TableField(value = "REMARK")
    private String remark; // 备注
}
