package com.jason.mybatis.project.service;

import com.jason.mybatis.project.model.UserModel;

import java.util.List;

/**
 * @author WongChenHoll
 * @date 2022-4-7 15:33
 **/
public interface AnnotationsService {

    void addUser(UserModel user);

    int addAccount(String name, double money);

    List<UserModel> userList(String userName, int age);

    List<UserModel> selectUserListByNameAndAge(String userName, int age);

    List<UserModel> selectDeptUserList(String deptId);
}
