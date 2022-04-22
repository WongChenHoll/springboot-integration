package com.jason.mybatisplus.project.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

/**
 * 通用实体类
 *
 * @author WongChenHoll
 * @date 2022-4-11 21:49
 **/
public class BaseModel{

    @TableId
    @TableField(value = "ID")
    private String id; // 主键ID，UUID

    @TableField(value = "CREATE_TIME")
    private Date createTime; // 创建时间

    @TableField(value = "CREATE_USER")
    private String createUser; // 创建人

    @TableField(value = "UPDATE_TIME")
    private Date updateTime; // 修改时间

    @TableField(value = "UPDATE_USER")
    private String updateUser; // 修改人
}
