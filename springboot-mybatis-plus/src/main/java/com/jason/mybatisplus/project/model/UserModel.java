package com.jason.mybatisplus.project.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * User用户表实体类
 *
 * @author WongChenHoll
 * @date 2022-3-29 18:09
 **/
@TableName(value = "SBI_USER")
public class UserModel {

    @TableId
    @TableField(value = "ID")
    private String id; // 主键ID，UUIDv

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

    @TableField(value = "CREATE_TIME")
    private Date createTime; // 创建时间

    @TableField(value = "CREATE_USER")
    private String createUser; // 创建人

    @TableField(value = "UPDATE_TIME")
    private Date updateTime; // 修改时间

    @TableField(value = "UPDATE_USER")
    private String updateUser; // 修改人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", deptId='" + deptId + '\'' +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                '}';
    }
}
