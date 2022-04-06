package com.jason.mybatis.project.dao;

import com.jason.mybatis.project.model.UserModel;

import java.util.List;

/**
 * 针对于在Mybatis中动态 SQL 的使用
 *
 * @author WongChenHoll
 * @date 2022-4-5 16:00
 **/
public interface DynamicSqlMapper {

    /**
     * where、if 标签在查询中的结合使用。
     * where 元素只会在子元素返回任何内容的情况下才插入 “WHERE” 子句。而且，若子句的开头为 “AND” 或 “OR”，where 元素也会将它们去除。
     */
    List<UserModel> queryUserInfo(UserModel userModel);

    /**
     * 对于 标签：where、choose、when、otherwise 的使用
     *
     * @param userModel 参数对象
     * @return 返回值
     */
    List<UserModel> selectUserByUserIdOrUserNameOrAge(UserModel userModel);

    /**
     * 根据ID 更新用户信息。
     * <p/>
     * 使用 set、if 标签配合使用，会自动删除最后一个set条件中不需要的逗号,
     *
     * @param userModel 用户信息
     */
    void updateUserById(UserModel userModel);

    /**
     * 根据用户名模糊查询。
     * <p/>
     * 使用bind标签拼接userName用于模糊查询
     *
     * @param userName 参数：用户名
     * @return 用户列表
     */
    List<UserModel> selectUserLikeUserName(String userName);
}
