package com.jason.mybatis.project.service.impl;

import com.jason.mybatis.project.dao.AnnotationsMapper;
import com.jason.mybatis.project.model.Account;
import com.jason.mybatis.project.model.UserModel;
import com.jason.mybatis.project.service.AnnotationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WongChenHoll
 * @date 2022-4-7 15:33
 **/
@Service
public class AnnotationsServiceImpl implements AnnotationsService {

    @Autowired
    private AnnotationsMapper annotationsMapper;

    @Override
    public void addUser(UserModel user) {
        annotationsMapper.addUser(user);
    }

    @Override
    public int addAccount(String name, double money) {
//        int addAccount = annotationsMapper.addAccount(name, money);

        // 添加新数据之后，自动获取新数据的主键ID（也就是序列值）
        Account account = new Account();
        account.setName(name);
        account.setMoney(money);
        annotationsMapper.addAccount(account);
        return account.getId();
    }

    @Override
    public List<UserModel> userList(String userName, int age) {
        return annotationsMapper.userList(userName, age);
    }

    @Override
    public List<UserModel> selectUserListByNameAndAge(String userName, int age) {
        return annotationsMapper.selectUserListByNameAndAge(userName, age);
    }

    @Override
    public List<UserModel> selectDeptUserList(String deptId) {
        return annotationsMapper.selectDeptUserList(deptId);
    }
}
