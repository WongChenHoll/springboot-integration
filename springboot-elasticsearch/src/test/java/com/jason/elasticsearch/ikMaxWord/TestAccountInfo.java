package com.jason.elasticsearch.ikMaxWord;

import com.jason.elasticsearch.commons.enums.BankEnum;
import com.jason.elasticsearch.project.model.AccountModel;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用RestHighLevelClient 测试关于 中文分词器的使用效果。
 *
 * @author WongChenHoll
 * @date 2022-4-29 星期五 17:54
 **/
@SpringBootTest
public class TestAccountInfo {

    public static final String INDEX = "jason_account_info";
    public static final String IK_MAX_WORD = "ik_max_word";

    @Autowired
    @Qualifier(value = "restHighLevelClient")
    RestHighLevelClient client;

    @Test
    public void addData() throws IOException {
        IndexRequest request = new IndexRequest(INDEX);
        request.source("{\"acct_no\":\"001\",\"acct_name\":\"张三\",\"acct_balance\":8023.1,\"open_bank\":\"BJ\",\"open_date\":\"2022-05-01\",\"remark\":\"第一次开户\"}", XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println("添加文档的响应：" + response.status());
        System.out.println("添加的数据：" + response.toString());
    }

    @Test
    public void testQuery() {
        SearchRequest request = new SearchRequest(INDEX);

    }
}
