package com.jason.mybatis.project.model.vo;

import com.jason.mybatis.project.model.UserModel;

import java.util.Date;
import java.util.List;

/**
 * @author WongChenHoll
 * @date 2022-3-31 17:12
 **/
public class DeptVo {
    private String id; // 主键ID，UUID
    private String deptId; // 部门编号
    private String deptName; //部门名称
    private String deptManager; //部门经理
    private String status; // 部门状态，1：现存部门，0：已经不存在的部门
    private String deptSrc; // 部门描述
    private Date createTime; // 创建时间
    private String createUser; // 创建人
    private Date updateTime; // 修改时间
    private String updateUser; // 修改人
    private List<UserModel> users; // 本部门下所有的员工信息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptManager() {
        return deptManager;
    }

    public void setDeptManager(String deptManager) {
        this.deptManager = deptManager;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeptSrc() {
        return deptSrc;
    }

    public void setDeptSrc(String deptSrc) {
        this.deptSrc = deptSrc;
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

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }
}
