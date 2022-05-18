package com.jason.elasticsearch.project.service;

import com.jason.elasticsearch.commons.enums.BankEnum;
import com.jason.elasticsearch.project.model.AccountModel;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author WongChenHoll
 * @date 2022-5-7 17:21
 **/
public interface AccountRepository extends ElasticsearchRepository<AccountModel, String> {

    /**
     * 根据开户行查询。测试match查询
     */
    List<AccountModel> findByOpenBank(BankEnum openBank);

    List<AccountModel> findByRemark(String remark);


    /**
     * ?0： 占位符
     */
    @Query("{\"term\":{\"remark\":{\"value\":\"?0\"}}}")
    List<AccountModel> queryTermByRemark(String remark);

    //  ?0 表示第一个参数，?1 表示第二个参数
    @Query("{\"terms\":{\"remark\":[\"?0\",\"?1\"]}}")
    List<AccountModel> queryTermsByRemark(String t1, String t2);

}
