package com.jason.mybatisplus.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.mybatisplus.project.model.UserModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author WongChenHoll
 * @date 2022-4-11 15:48
 **/
public interface UserMapper extends BaseMapper<UserModel> {

    List<UserModel> pageList(@Param("page") Page<UserModel> page, @Param("user") UserModel user);
}
