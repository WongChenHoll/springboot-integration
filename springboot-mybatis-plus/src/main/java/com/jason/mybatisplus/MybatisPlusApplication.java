package com.jason.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author WongChenHoll
 * @date 2022-4-11 15:25
 **/
@MapperScan("com.jason.mybatisplus.project.mapper") // 添加Dao层接口的扫描
@SpringBootApplication
public class MybatisPlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }
}
