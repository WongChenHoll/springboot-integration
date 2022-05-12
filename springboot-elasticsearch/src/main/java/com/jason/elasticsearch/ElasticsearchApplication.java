package com.jason.elasticsearch;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * <pre>
 *  在elasticsearch8.x版本中推荐使用
 *      1.rest客户端 RestHingLevelClient{@link RestHighLevelClient}
 *      2.和接口 ElasticsearchRepository
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
