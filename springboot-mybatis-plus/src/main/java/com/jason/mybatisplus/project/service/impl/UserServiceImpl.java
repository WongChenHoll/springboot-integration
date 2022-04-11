package com.jason.mybatisplus.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.mybatisplus.project.mapper.UserMapper;
import com.jason.mybatisplus.project.model.UserModel;
import com.jason.mybatisplus.project.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author WongChenHoll
 * @date 2022-4-11 15:51
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserModel> implements UserService {

}
