package com.jason.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <pre>
 *  在elasticsearch8.x版本中推荐使用
 *      1.rest客户端RestHingLevelClient{@link com.jason.elasticsearch.config.RestHingLevelClient}
 *      2.和接口ElasticSearchRespositoy
 * </pre>
 *
 * @author WongChenHoll
 * @date 2022-4-12 星期二 15:16
 **/
@SpringBootApplication
public class ElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }
}
