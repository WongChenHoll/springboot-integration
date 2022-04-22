package com.jason.elasticsearch.ElasticsearchRepository;

import com.jason.elasticsearch.project.model.UserModel;
import com.jason.elasticsearch.project.service.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 测试 ElasticsearchRepository 接口中的 find系列方法
 *
 * @author WongChenHoll
 * @date 2022-4-15 星期五 15:03
 **/
@SpringBootTest
public class FindTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCount() {
        System.out.println("总数：" + userRepository.count());
        System.out.println("ID[YQwXJ4AB0sa_iFrDltgc]是否存在:" + userRepository.existsById("YQwXJ4AB0sa_iFrDltgc"));
    }

    /**
     * 查询全部
     */
    @Test
    public void testFindAll() {
        Iterable<UserModel> all = userRepository.findAll();
        for (UserModel model : all) {
            System.out.println(model.toString());
        }
    }

    /**
     * 查询全部，并且按照年龄降序排列
     */
    @Test
    public void testFindAllOrderByDesc() {
        Iterable<UserModel> all = userRepository.findAll(Sort.by(Sort.Order.desc("age")));
        for (UserModel model : all) {
            System.out.println(model.toString());
        }
    }

    /**
     * 分页查询
     */
    @Test
    public void testFindAllWithPage() {
        // 查询第一页，每页5个数据
        Pageable pageable = Pageable.ofSize(5).withPage(0);
        Iterable<UserModel> all = userRepository.findAll(pageable);
        for (UserModel model : all) {
            System.out.println(model.toString());
        }
    }

    /**
     * 分页查询
     */
    @Test
    public void testFindAllWithPageAndOrder() {
        // 查询第一页，每页5个数据，并按年龄倒序排列
        PageRequest page = PageRequest.of(0, 5, Sort.by(Sort.Order.desc("age")));
        Iterable<UserModel> all = userRepository.findAll(page);
        for (UserModel model : all) {
            System.out.println(model.toString());
        }
    }

    /**
     * 分页查询
     */
    @Test
    public void testFindAllWithNextPageAndOrder() {
        // 翻页查询，每页5个数据。并按年龄降序配列
        PageRequest page = PageRequest.of(0, 5, Sort.by(Sort.Order.desc("age")));
        do {
            Iterable<UserModel> all = userRepository.findAll(page);
            if (!all.iterator().hasNext()) {
                return;
            }
            for (UserModel model : all) {
                System.out.println(model.toString());
            }
            page = page.next();
        } while (true);
    }

    /**
     * 根据ID查询
     */
    @Test
    public void testFindById() {
        // 根据单个ID查询
        Optional<UserModel> optional = userRepository.findById("MGaqNoABfXKo_Zq7mfma");
        System.out.println("ID=[MGaqNoABfXKo_Zq7mfma]的数据：" + optional.get().toString());

        // 根据多个ID查询
        List<String> ids = new ArrayList<>();
        Collections.addAll(ids, "TQzkIoAB0sa_iFrDpdgu", "Uwx8I4AB0sa_iFrDXtj5");
        Iterable<UserModel> iterable = userRepository.findAllById(ids);
        for (UserModel userModel : iterable) {
            System.out.println("多个ID查询结果：" + userModel.toString());
        }
    }
}
