package com.jason.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 使用 RestHingLevelClient 客户端
 *
 * @author WongChenHoll
 * @date 2022-4-13 星期三 15:18
 **/
@SpringBootConfiguration
public class RestHingLevelClient {

    @Bean
    public RestClientBuilder restClientBuilder() {
        return RestClient.builder(createHttpHost("127.0.0.1:9200"));
    }

    @Bean
    public RestHighLevelClient restHighLevelClient(@Autowired RestClientBuilder restClientBuilder) {
        return new RestHighLevelClient(restClientBuilder);
    }

    private HttpHost createHttpHost(String ipPort) {
        String[] split = ipPort.split(":");
        String ip = split[0];
        int port = Integer.parseInt(split[1]);
        return new HttpHost(ip, port, "http");
    }
}
