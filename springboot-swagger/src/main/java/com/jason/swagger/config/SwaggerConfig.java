package com.jason.swagger.config;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 注意：
 * 3.0.0之前版本需使用@EnableSwagger2注解
 * 3.0.0版本则不需要@EnableSwagger2注解，取而代之是@EnableOpenApi
 * <p/>
 * 3.0.0之前的版本访问是：/swagger-ui.html
 * 3.0.0版本访问是：/swagger-ui/index.html
 *
 * @author WongChenHoll
 * @date 2022-4-8 11:04
 **/
@SpringBootConfiguration
public class SwaggerConfig {

    // 可以不配置下面这个代码，swagger会有默认的页面配置。
    @Bean
    public Docket getDoc() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("测试Swagger的使用")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org.licenses/LICENSE-2.0.html")
                .version("1.0")
                .contact(new Contact("测试", null, null))
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .build()
                .globalRequestParameters(null)
                .useDefaultResponseMessages(false);
    }
}
