package com.jason.mybatis.project.service;

import com.jason.mybatis.project.model.UserModel;

import java.util.List;

/**
 * @author WongChenHoll
 * @date 2022-4-5 16:06
 **/
public interface DynamicSqlService {

    List<UserModel> queryUserInfo(UserModel userModel);

    List<UserModel> selectUserByUserIdOrUserNameOrAge(UserModel userModel);

    void updateUserById(UserModel userModel);

    List<UserModel> selectUserLikeUserName(String userName);
}
