package com.jason.mybatis.project.service.impl;

import com.jason.mybatis.project.dao.UserMapper;
import com.jason.mybatis.project.model.UserModel;
import com.jason.mybatis.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author WongChenHoll
 * @date 2022-3-29 20:30
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(UserModel userModel) {
        userMapper.addUser(userModel);
    }

    @Override
    public void deleteUserById(String id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public void updateUserById(UserModel userModel) {
        userMapper.updateUserById(userModel);
    }

    @Override
    public UserModel selectUserById(String id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public void batchAddUser(UserModel[] userModels) {
        ArrayList<UserModel> models = new ArrayList<>();
        Collections.addAll(models, userModels);
        userMapper.batchAddUser(models);
    }

    @Override
    public void deleteUserByIds(String[] ids) {
        userMapper.deleteUserByIds(ids);
    }

    @Override
    public void batchUpdateUserById(List<UserModel> users) {
        userMapper.batchUpdateUserById(users);
    }

    @Override
    public List<UserModel> selectByAge(int age) {
        return userMapper.selectByAge(age);
    }
}
