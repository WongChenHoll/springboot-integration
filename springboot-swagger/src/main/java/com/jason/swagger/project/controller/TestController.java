package com.jason.swagger.project.controller;

//import io.swagger.annotations.Api;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WongChenHoll
 * @date 2022-4-8 11:02
 **/
@Api(value = "测试接口")
@RequestMapping("/test")
@RestController
public class TestController {

    @PostMapping("/m1")
    public String test(String name) {
        return name;
    }
}
