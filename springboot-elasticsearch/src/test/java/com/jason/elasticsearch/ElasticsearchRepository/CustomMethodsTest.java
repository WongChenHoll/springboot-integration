package com.jason.elasticsearch.ElasticsearchRepository;

import com.jason.elasticsearch.project.model.UserModel;
import com.jason.elasticsearch.project.service.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 测试自定义方法
 *
 * @author WongChenHoll
 * @date 2022-4-15 星期五 16:06
 **/
@SpringBootTest
public class CustomMethodsTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
        // 年龄
        List<UserModel> list = userRepository.findByAge(66);
        for (UserModel userModel : list) {
            System.out.println("查询到年龄等于66的用户：" + userModel);
        }
//        List<UserModel> byAge = userRepository.findDistinctByAge("age");
//        for (UserModel model : byAge) {
//            System.out.println("根据年龄去重后的结果：" + model);
//        }
//        int distinctByAge = userRepository.findDistinctByAge();
//        int distinctByAge = userRepository.countDistinctByAge();
//        System.out.println("去重后的总数：：" + distinctByAge);
        // 姓名
        List<UserModel> name = userRepository.findByName("张三");
        for (UserModel model : name) {
            System.out.println("查询到姓名 包含 “张三”的用户信息：" + model);
        }
        // 地址
        List<UserModel> address = userRepository.findByAddress("上海市");
        for (UserModel model : address) {
            System.out.println("find 查询到地址 包含 “上海市”的用户信息：" + model);
        }
        List<UserModel> queryByAddress = userRepository.queryByAddress("上海市");
        for (UserModel model : queryByAddress) {
            System.out.println("query 查询到地址 包含 “上海市”的用户信息：" + model);
        }
        List<UserModel> ad = userRepository.findByAddress("上海");
        for (UserModel model : ad) {
            System.out.println("查询到地址 包含 “上海”的用户信息：" + model);
        }
        System.out.println("查询到地址 包含 “上海”的第一个用户信息：" + userRepository.findFirstByAddress("上海"));
        System.out.println("查询到地址 包含 “上海”的TOP用户信息：" + userRepository.findTopByAddress("上海"));
    }
}
