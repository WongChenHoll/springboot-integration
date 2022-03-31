package com.jason.mybatis.project.model;

import java.util.Date;

/**
 * User用户表实体类
 *
 * @author WongChenHoll
 * @date 2022-3-29 18:09
 **/
public class UserModel {
    private String id; // 主键ID，UUID
    private String userId; // 用户编号
    private String userName; // 用户姓名
    private int age; // 年龄
    private String phone; // 手机号/电话号码
    private String email; // 邮箱
    private String deptId; // 部门编号
    private int status; // 任职状态，0：离职，1:在职，2：观察，3：其他
    private String remark; // 备注
    private Date createTime; // 创建时间
    private String createUser; // 创建人
    private Date updateTime; // 修改时间
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
}
