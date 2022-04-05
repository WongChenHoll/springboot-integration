package com.jason.mybatis.project.service.impl;

import com.jason.mybatis.project.dao.DeptMapper;
import com.jason.mybatis.project.model.vo.DeptInfo;
import com.jason.mybatis.project.model.vo.DeptVo;
import com.jason.mybatis.project.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WongChenHoll
 * @date 2022-3-31 17:48
 **/
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<DeptVo> allDept() {
        return deptMapper.allDept();
    }

    @Override
    public List<DeptInfo> deptInfo() {
        return deptMapper.deptInfo();
    }
}
