package com.jason.mybatis.project.service;

import com.jason.mybatis.project.model.vo.DeptInfo;
import com.jason.mybatis.project.model.vo.DeptVo;

import java.util.List;

/**
 * @author WongChenHoll
 * @date 2022-3-31 17:48
 **/
public interface DeptService {
    List<DeptVo> allDept();

    List<DeptInfo> deptInfo();
}
