package com.jason.mybatis.project.dao;

import com.jason.mybatis.project.model.vo.DeptVo;

import java.util.List;

/**
 * @author WongChenHoll
 * @date 2022-3-31 17:13
 **/
public interface DeptMapper {

    List<DeptVo> allDept();
}
