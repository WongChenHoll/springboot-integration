package com.jason.mybatis.project.model.vo;

import com.jason.mybatis.project.model.UserModel;

/**
 * @author WongChenHoll
 * @date 2022-4-5 14:51
 **/
public class DeptInfo {

    private String id; // 主键ID，UUID
    private String deptId; // 部门编号
    private String deptName; //部门名称
    private String deptManager; //部门经理
    private String status; // 部门状态，1：现存部门，0：已经不存在的部门
    private String deptDesc; // 部门描述
    private UserModel userInfo;

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

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

    public UserModel getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserModel userInfo) {
        this.userInfo = userInfo;
    }
}
