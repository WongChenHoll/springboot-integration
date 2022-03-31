package com.jason.mybatis.project.controller;

import com.jason.mybatis.project.model.UserModel;
import com.jason.mybatis.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author WongChenHoll
 * @date 2022-3-29 20:32
 **/
@RequestMapping("/user")
@RestController
public class UserController {

    // ======================== 单个记录操作 =======================
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public void add(@RequestBody UserModel userModel) {
        userService.addUser(userModel);
    }

    @GetMapping("/delete")
    public void delete(String id) {
        userService.deleteUserById(id);
    }

    @PostMapping("/update")
    public void update(@RequestBody UserModel userModel) {
        userService.updateUserById(userModel);
    }

    @GetMapping("/selectById")
    public UserModel selectById(String id) {
        return userService.selectUserById(id);
    }

    // ======================== 多个记录批量操作 =======================
    @PostMapping("/batchAdd")
    public void batchAdd(@RequestBody UserModel[] userModels) {
        userService.batchAddUser(userModels);
    }

    @PostMapping("/deleteUserByIds")
    public void deleteUserByIds(@RequestBody String[] ids) {
        userService.deleteUserByIds(ids);
    }

    @PostMapping("/batchUpdateUserById")
    public void batchUpdateUserById(@RequestBody List<UserModel> users) {
        userService.batchUpdateUserById(users);
    }

    @GetMapping("/selectByAge")
    public List<UserModel> selectByAge(int age) {
        return userService.selectByAge(age);
    }


}
