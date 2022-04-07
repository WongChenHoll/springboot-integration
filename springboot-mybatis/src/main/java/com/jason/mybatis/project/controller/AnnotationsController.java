package com.jason.mybatis.project.controller;

import com.jason.mybatis.project.model.UserModel;
import com.jason.mybatis.project.service.AnnotationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author WongChenHoll
 * @date 2022-4-7 15:34
 **/
@RestController
@RequestMapping("/annotations")
public class AnnotationsController {

    @Autowired
    private AnnotationsService annotationsService;

    @PostMapping("add")
    public void add(@RequestBody UserModel userModel) {
        annotationsService.addUser(userModel);
    }

    @PostMapping("addAccount")
    public int addAccount(@RequestParam String name, @RequestParam double money) {
        return annotationsService.addAccount(name, money);
    }

    @GetMapping("userList")
    public List<UserModel> userList(@RequestParam String userName, @RequestParam int age) {
        return annotationsService.userList(userName, age);
    }

    @GetMapping("selectUserListByNameAndAge")
    public List<UserModel> selectUserListByNameAndAge(@RequestParam String userName, @RequestParam int age) {
        return annotationsService.selectUserListByNameAndAge(userName, age);
    }
    @GetMapping("selectDeptUserList")
    public List<UserModel> selectDeptUserList(@RequestParam String deptId) {
        return annotationsService.selectDeptUserList(deptId);
    }

}
