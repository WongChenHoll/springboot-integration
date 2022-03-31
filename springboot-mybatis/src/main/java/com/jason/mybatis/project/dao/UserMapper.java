package com.jason.mybatis.project.dao;

import com.jason.mybatis.project.model.UserModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author WongChenHoll
 * @date 2022-3-29 18:05
 **/
public interface UserMapper {

    /**
     * 添加用户
     */
    void addUser(UserModel userModel);

    /**
     * 根据主键ID删除用户
     *
     * @param id 主键ID
     */
    void deleteUserById(String id);

    /**
     * 根据主键ID修改用户
     *
     * @param userModel 用户信息
     */
    void updateUserById(UserModel userModel);

    /**
     * 根据主键ID查询用户
     *
     * @param id 主键ID
     * @return 查询到的用户信息
     */
    UserModel selectUserById(String id);

    /**
     * 批量添加用户
     *
     * @param models 用户信息数组
     */
    void batchAddUser(List<UserModel> models);

    /**
     * 根据主键ID批量删除数据
     *
     * @param ids 主键ID数组
     */
    void deleteUserByIds(String[] ids);

    /**
     * 根据ID批量修改用户信息
     *
     * @param users  用户信息
     */
    void batchUpdateUserById(@Param("users") List<UserModel> users);

    /**
     * 查询除年龄大于等于该年龄的所有用户
     *
     * @param age 年龄
     * @return 用户信息列表
     */
    List<UserModel> selectByAge(Integer age);
}
