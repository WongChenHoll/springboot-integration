package com.jason.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * 本项目Swagger访问路径：http://localhost:12003/swagger/swagger-ui/index.html
 *
 * @author WongChenHoll
 * @date 2022-3-29 17:10
 **/
@EnableOpenApi
@SpringBootApplication
public class SwaggerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }
}
