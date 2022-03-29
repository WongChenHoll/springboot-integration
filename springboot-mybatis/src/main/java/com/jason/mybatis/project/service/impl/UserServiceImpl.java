package com.jason.mybatis.project.service.impl;

import com.jason.mybatis.project.dao.UserMapper;
import com.jason.mybatis.project.model.UserModel;
import com.jason.mybatis.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
