package com.jason.mybatis.project.service;

import com.jason.mybatis.project.model.UserModel;

import java.util.List;

/**
 * @author WongChenHoll
 * @date 2022-3-29 20:30
 **/
public interface UserService {

    void addUser(UserModel userModel);

    void deleteUserById(String id);

    void updateUserById(UserModel userModel);

    UserModel selectUserById(String id);

    void batchAddUser(UserModel[] userModels);

    void deleteUserByIds(String[] ids);

    void batchUpdateUserById(List<UserModel> users);

    List<UserModel> selectByAge(int age);
}
