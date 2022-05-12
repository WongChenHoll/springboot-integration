package com.jason.elasticsearch.project.service;

import com.jason.elasticsearch.project.model.AccountModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author WongChenHoll
 * @date 2022-5-7 17:21
 **/
public interface AccountRepository extends ElasticsearchRepository<AccountModel, String> {
}
