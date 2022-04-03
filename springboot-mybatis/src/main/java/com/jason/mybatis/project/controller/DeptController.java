package com.jason.mybatis.project.controller;

import com.jason.mybatis.project.model.vo.DeptVo;
import com.jason.mybatis.project.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author WongChenHoll
 * @date 2022-3-31 17:46
 **/
@RequestMapping("/dept")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/allDept")
    public List<DeptVo> allDept(){
        return deptService.allDept();
    }
}
