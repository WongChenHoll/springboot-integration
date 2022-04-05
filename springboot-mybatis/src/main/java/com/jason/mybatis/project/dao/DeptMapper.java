package com.jason.mybatis.project.dao;

import com.jason.mybatis.project.model.vo.DeptInfo;
import com.jason.mybatis.project.model.vo.DeptVo;

import java.util.List;

/**
 * @author WongChenHoll
 * @date 2022-3-31 17:13
 **/
public interface DeptMapper {

    /**
     * 查询部门信息。
     * <p/>
     * mybatis使用的知识：配置一对多（在resultMap中使用collection标签）。<p/>
     * 使用两张表关联的外键作为resultMap的id标签使用。
     *
     * @return 返回部门列表以及该部门下所有的员工信息
     */
    List<DeptVo> allDept();

    /**
     * 部门信息，不查询该部门下的员工信息。
     * <p/>
     * 主要是查询部门经理的姓名，而不是经理的编号。
     * <p/>
     * mybatis使用的知识：配置一对一。
     *
     * @return 部门信息的列表
     */
    List<DeptInfo> deptInfo();
}
