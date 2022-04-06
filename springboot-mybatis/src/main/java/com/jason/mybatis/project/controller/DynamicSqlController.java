package com.jason.mybatis.project.controller;

import com.jason.mybatis.project.model.UserModel;
import com.jason.mybatis.project.service.DynamicSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author WongChenHoll
 * @date 2022-4-5 16:07
 **/
@RestController
@RequestMapping("/dynamic")
public class DynamicSqlController {

    @Autowired
    private DynamicSqlService dynamicSqlService;

    @PostMapping("userInfo")
    public List<UserModel> userInfo(@RequestBody UserModel userModel) {
        return dynamicSqlService.queryUserInfo(userModel);
    }

    @PostMapping("selectUserByUserIdOrUserNameOrAge")
    public List<UserModel> selectUserByUserIdOrUserNameOrAge(@RequestBody UserModel userModel) {
        return dynamicSqlService.selectUserByUserIdOrUserNameOrAge(userModel);
    }

    @PostMapping("updateUserById")
    public void updateUserById(@RequestBody UserModel userModel) {
        dynamicSqlService.updateUserById(userModel);
    }

    @GetMapping("selectUserLikeUserName")
    public List<UserModel> selectUserLikeUserName(String userName) {
        return dynamicSqlService.selectUserLikeUserName(userName);
    }

}
