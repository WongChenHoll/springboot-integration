package com.jason.elasticsearch.commons.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 使用 RestHingLevelClient 客户端
 *
 * @author WongChenHoll
 * @date 2022-4-13 星期三 15:18
 **/
@SpringBootConfiguration
public class RestHingLevelClientConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(createHttpHost("127.0.0.1:9200")));
    }

    private HttpHost createHttpHost(String ipPort) {
        String[] split = ipPort.split(":");
        String ip = split[0];
        int port = Integer.parseInt(split[1]);
        return new HttpHost(ip, port, "http");
    }
}
