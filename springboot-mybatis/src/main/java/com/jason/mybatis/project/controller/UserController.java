package com.jason.mybatis.project.controller;

import com.jason.mybatis.project.model.UserModel;
import com.jason.mybatis.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author WongChenHoll
 * @date 2022-3-29 20:32
 **/
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public void add(@RequestBody UserModel userModel) {
        userService.addUser(userModel);
    }

}
