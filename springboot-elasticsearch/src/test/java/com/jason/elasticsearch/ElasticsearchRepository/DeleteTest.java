package com.jason.elasticsearch.ElasticsearchRepository;

import com.jason.elasticsearch.project.service.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 测试删除方法
 *
 * @author WongChenHoll
 * @date 2022-4-15 星期五 16:00
 **/
@SpringBootTest
public class DeleteTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testDeleteById() {
        if (userRepository.existsById("01UzLIABB610r6QP1Vou")) {
            userRepository.deleteById("01UzLIABB610r6QP1Vou");
        }
    }

    @Test
    public void testDeleteByIds() {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "1FU5LIABB610r6QPnlo7", "1VU5LIABB610r6QPnlo7");
        userRepository.deleteAllById(list);
    }


}
