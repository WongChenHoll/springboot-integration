package com.jason.mybatis.commons.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

/**
 * 配置数据源。
 *<p>
 *     所有的数据库配置参数均使用默认的参数
 *</p>
 * @author WongChenHoll
 * @date 2022-3-29 17:29
 **/
@SpringBootConfiguration
public class DataSourceConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
