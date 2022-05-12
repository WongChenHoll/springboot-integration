package com.jason.elasticsearch.RestHighLevelClient;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * 测试索引相关API
 *
 * @author WongChenHoll
 * @date 2022-4-13 星期三 15:30
 **/
@SpringBootTest
public class TestIndex {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    /**
     * 使用 CreateIndexRequest 创建一个索引
     */
    @Test
    public void testCreateIndex() throws IOException {
        CreateIndexRequest indexRequest = new CreateIndexRequest("jason_user_test");
        CreateIndexResponse response = restHighLevelClient.indices().create(indexRequest, RequestOptions.DEFAULT);
        System.out.println(response.index());
    }

    /**
     * 使用 GetIndexRequest 查询索引
     */
    @Test
    public void testGetIndx() throws IOException {
        GetIndexRequest indexRequest = new GetIndexRequest("jason_user_test");
        IndicesClient client = restHighLevelClient.indices();
        boolean exists = client.exists(indexRequest, RequestOptions.DEFAULT);
        System.out.println("索引是否存在：" + exists);
        if (exists) {
            GetIndexResponse response = client.get(indexRequest, RequestOptions.DEFAULT);
            String[] indices = response.getIndices();
            for (String index : indices) {
                System.out.println("查询到的索引：" + index);
            }
        }
    }

    /**
     * 使用 DeleteIndexRequest 删除索引，如果被删除的索引不存在，则会报异常
     */
    @Test
    public void testDeleteIndex() throws IOException {
        DeleteIndexRequest indexRequest = new DeleteIndexRequest("jason_user_test");
        AcknowledgedResponse response = restHighLevelClient.indices().delete(indexRequest, RequestOptions.DEFAULT);
        System.out.println("索引是否删除成功：" + response.isAcknowledged());
    }
}
