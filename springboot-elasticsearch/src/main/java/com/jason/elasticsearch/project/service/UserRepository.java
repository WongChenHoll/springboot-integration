package com.jason.elasticsearch.project.service;

import com.jason.elasticsearch.project.model.UserModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 使用 ElasticsearchRepository 接口
 *
 * @author WongChenHoll
 * @date 2022-4-13 星期三 15:26
 **/
public interface UserRepository extends ElasticsearchRepository<UserModel, String> {

    /**
     * 查询年龄等于 age 的用户列表
     */
    List<UserModel> findByAge(int age);

    /**
     * 查询姓名中包含 name 的用户列表
     */
    List<UserModel> findByName(String name);

    /**
     * 查询地址中包含 address 的用户列表
     * 等同于 {@link UserRepository#queryByAddress(java.lang.String)}
     */
    List<UserModel> findByAddress(String address);

    /**
     * 查询地址中包含 address 的用户列表
     * 等同于 {@link UserRepository#findByAddress(java.lang.String)}
     */
    List<UserModel> queryByAddress(String address);

//    /**
//     * 根据 年龄去重，TODO:有问题
//     */
//    @Query("{\"query\": {\"match_all\": {}},\"size\": 0,\"aggregations\": {\"u_age\": {\"cardinality\": {\"field\": \"age\"}}}}")
//    int countDistinctByAge();

    /**
     * 查询地址中包含 name 的第一个用户，同{@link UserRepository#findFirstByAddress(java.lang.String)}
     */
    UserModel findTopByAddress(String name);

    /**
     * 查询地址中包含 name 的第一个用户，同{@link UserRepository#findTopByAddress(java.lang.String)}
     */
    UserModel findFirstByAddress(String address);

}
