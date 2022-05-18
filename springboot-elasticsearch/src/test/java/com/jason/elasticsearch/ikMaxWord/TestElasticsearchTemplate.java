package com.jason.elasticsearch.ikMaxWord;

import com.jason.elasticsearch.project.model.AccountModel;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

/**
 * @author WongChenHoll
 * @date 2022-5-15 星期日 10:39
 **/
@SpringBootTest
public class TestElasticsearchTemplate {

    @Autowired
    @Qualifier(value = "restHighLevelClient")
    RestHighLevelClient client;


    private ElasticsearchRestTemplate restTemplate;

    @BeforeEach
    public void init() {
        restTemplate = new ElasticsearchRestTemplate(client);
    }

    @Test
    public void test1() {
        NativeSearchQuery query = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchAllQuery()).build();

        SearchHits<AccountModel> hits = restTemplate.search(query, AccountModel.class);
        System.out.println("查询结果总共：" + hits.getTotalHits() + " 条数据，分别是：");
        for (SearchHit<AccountModel> hit : hits) {
            System.out.println(hit.getContent().toString());
        }
    }
}
