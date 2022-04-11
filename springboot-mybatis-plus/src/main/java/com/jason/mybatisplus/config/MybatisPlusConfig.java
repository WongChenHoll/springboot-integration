package com.jason.mybatisplus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 使用Mybatis-plus时，分页插件的配置
 *
 * @author WongChenHoll
 * @date 2022-4-11 17:22
 **/
@SpringBootConfiguration
public class MybatisPlusConfig {

    /**
     * 新版本（com.baomidou:mybatis-plus-boot-starter:3.5.1 以上）的分页插件配置。
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.ORACLE_12C));
        return interceptor;
    }


//    /**
//     * 老版本（com.baomidou:mybatis-plus-boot-starter:3.5.1 以下））的分页插件配置
//     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor(){
//        PaginationInterceptor paginationInterceptor=  new PaginationInterceptor();
//        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
//        return paginationInterceptor;
//    }

}
