package com.jason.elasticsearch.ElasticsearchRepository;

import com.jason.elasticsearch.project.model.UserModel;
import com.jason.elasticsearch.project.service.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author WongChenHoll
 * @date 2022-4-15 星期五 15:43
 **/
@SpringBootTest
public class SaveTest {

    @Autowired
    private UserRepository userRepository;

    /**
     * 保存单个用户，会报异常，但是数据会成功保存
     */
    @Test
    public void testSave() {
        UserModel model = new UserModel();
        model.setUserId("test_save_01");
        model.setName("测试save用户");
        model.setAge(10);
        UserModel save = userRepository.save(model);
        System.out.println("保存的用户信息：" + save);
    }

    @Test
    public void testBatchSave() {
        UserModel m1 = new UserModel();
        m1.setUserId("test_save_02");
        m1.setName("测试save用户_02");
        m1.setAge(20);

        UserModel m2 = new UserModel();
        m2.setUserId("test_save_03");
        m2.setName("测试save用户_03");
        m2.setAge(30);

        ArrayList<UserModel> userModels = new ArrayList<>();
        Collections.addAll(userModels, m1, m2);

        Iterable<UserModel> all = userRepository.saveAll(userModels);
        for (UserModel userModel : all) {
            System.out.println("批量保存的用户：" + userModel);
        }

    }
}
