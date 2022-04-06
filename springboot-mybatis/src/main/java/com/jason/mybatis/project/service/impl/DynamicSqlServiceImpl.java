package com.jason.mybatis.project.service.impl;

import com.jason.mybatis.project.dao.DynamicSqlMapper;
import com.jason.mybatis.project.model.UserModel;
import com.jason.mybatis.project.service.DynamicSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WongChenHoll
 * @date 2022-4-5 16:06
 **/
@Service
public class DynamicSqlServiceImpl implements DynamicSqlService {

    @Autowired
    private DynamicSqlMapper dynamicSqlMapper;

    @Override
    public List<UserModel> queryUserInfo(UserModel userModel) {
        return dynamicSqlMapper.queryUserInfo(userModel);
    }

    @Override
    public List<UserModel> selectUserByUserIdOrUserNameOrAge(UserModel userModel) {
        return dynamicSqlMapper.selectUserByUserIdOrUserNameOrAge(userModel);
    }

    @Override
    public void updateUserById(UserModel userModel) {
        dynamicSqlMapper.updateUserById(userModel);
    }

    @Override
    public List<UserModel> selectUserLikeUserName(String userName) {
        return dynamicSqlMapper.selectUserLikeUserName(userName);
    }
}
