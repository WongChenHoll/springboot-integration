package com.jason.elasticsearch.RestHighLevelClient;

import com.alibaba.fastjson.JSONObject;
import com.jason.elasticsearch.project.model.UserModel;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;

/**
 * 测试Document相关API
 *
 * @author WongChenHoll
 * @date 2022-4-13 星期三 17:11
 **/
@SpringBootTest
public class TestDocument {

    @Autowired
    @Qualifier(value = "restHighLevelClient")
    RestHighLevelClient client;

    /**
     * 通过 IndexRequest 创建在指定的index中创建一个文档
     */
    @Test
    public void testCreateDocument() throws IOException {
        IndexRequest request = new IndexRequest(); // 指定index，可以一个index存储多个文档
        request.index("jason_doc_10");
        request.id(); // 使用默认的id
        request.timeout(TimeValue.timeValueSeconds(10)); // 设置超时时间

        UserModel model = new UserModel();
        model.setId("daZhaoLiu");
        model.setName("大赵六");
        model.setAge(69);
        model.setBir(new Date());
        model.setAddress("中国深圳");

        request.source(JSONObject.toJSONString(model), XContentType.JSON);

        // 该异常不影响：java.io.IOException: Unable to parse response body for Response{requestLine=POST /jason_doc_10/_doc?timeout=10s HTTP/1.1, host=http://127.0.0.1:9200, response=HTTP/1.1 201 Created}
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println("请求结果状态：" + response.status());
        System.out.println("响应结果：" + response.toString());
    }

    @Test
    public void testBatchCreateDocument() throws IOException {
        BulkRequest bulk = new BulkRequest();
        IndexRequest request3 = new IndexRequest("jason_doc_10");
        request3.id();
        request3.source("{\"address\":\"中国上海市宝山区\",\"age\":20,\"bir\":1649862303342,\"id\":\"user001\",\"name\":\"大张三\"}", XContentType.JSON);
        IndexRequest request4 = new IndexRequest("jason_doc_10");
        request4.id();
        request4.source("{\"address\":\"中国上海市浦东区\",\"age\":50,\"bir\":1649862303342,\"id\":\"user00100\",\"name\":\"小张三\"}", XContentType.JSON);
        IndexRequest request5 = new IndexRequest("jason_doc_10");
        request5.id();
        request5.source("{\"address\":\"中国上海市黄埔\",\"age\":34,\"bir\":1649862303342,\"id\":\"00user1\",\"name\":\"李四\"}", XContentType.JSON);
        IndexRequest request6 = new IndexRequest("jason_doc_10");
        request6.id();
        request6.source("{\"address\":\"上海普陀区\",\"age\":45,\"bir\":1649862303342,\"id\":\"user0011\",\"name\":\"大李四\"}", XContentType.JSON);
        IndexRequest request7 = new IndexRequest("jason_doc_10");
        request7.id();
        request7.source("{\"address\":\"中国北京市朝阳区\",\"age\":62,\"bir\":1649862303342,\"id\":\"user2201\",\"name\":\"小李四\"}", XContentType.JSON);
        IndexRequest request8 = new IndexRequest("jason_doc_10");
        request8.id();
        request8.source("{\"address\":\"中国北京市东城\",\"age\":42,\"bir\":1649862303342,\"id\":\"78user01\",\"name\":\"王五\"}", XContentType.JSON);
        IndexRequest request9 = new IndexRequest("jason_doc_10");
        request9.id();
        request9.source("{\"address\":\"中国北京市西城区\",\"age\":12,\"bir\":1649862303342,\"id\":\"22\",\"name\":\"大王五\"}", XContentType.JSON);
        IndexRequest request10 = new IndexRequest("jason_doc_10");
        request10.id();
        request10.source("{\"address\":\"北京市海淀区\",\"age\":18,\"bir\":1649862303342,\"id\":\"user01\",\"name\":\"小王五\"}", XContentType.JSON);

        bulk.add(request3);
        bulk.add(request4);
        bulk.add(request5);
        bulk.add(request6);
        bulk.add(request7);
        bulk.add(request8);
        bulk.add(request9);
        bulk.add(request10);
        BulkResponse responses = client.bulk(bulk, RequestOptions.DEFAULT);
        System.out.println("批量新增响应状态：" + responses.status());
        for (BulkItemResponse item : responses.getItems()) {
            System.out.println("批量新增后每个数据的返回状态：" + item.status());
        }
    }

    /**
     * 通过 DeleteRequest 删除指定index下的指定id的文档
     */
    @Test
    public void testDeleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest("jason_doc_10", "Vgx8I4AB0sa_iFrDXtj5"); // 第一个参数是：index，第二个参数是：id
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        System.out.println("删除文档状态：" + response.status());
    }

    /**
     * 批量删除
     */
    @Test
    public void testBatchDeleteDocument() throws IOException {
        BulkRequest bulk = new BulkRequest();

        bulk.add(new DeleteRequest("jason_doc_10", "TgxQI4AB0sa_iFrDMdhW"));
        bulk.add(new DeleteRequest("jason_doc_10", "TwxtI4AB0sa_iFrDwNgY"));
        bulk.add(new DeleteRequest("jason_doc_10", "UAxvI4AB0sa_iFrDANg2"));
        bulk.add(new DeleteRequest("jason_doc_10", "UQx6I4AB0sa_iFrDFtjM"));
        bulk.add(new DeleteRequest("jason_doc_10", "Ugx8I4AB0sa_iFrDXtj5"));
        bulk.add(new DeleteRequest("jason_doc_10", "VAx8I4AB0sa_iFrDXtj5"));
        bulk.add(new DeleteRequest("jason_doc_10", "VQx8I4AB0sa_iFrDXtj5"));
        bulk.add(new DeleteRequest("jason_doc_10", "Vwx8I4AB0sa_iFrDXtj5"));
        bulk.add(new DeleteRequest("jason_doc_10", "WAx8I4AB0sa_iFrDXtj5"));
        bulk.add(new DeleteRequest("jason_doc_10", "WQx8I4AB0sa_iFrDXtj5"));

        BulkResponse response = client.bulk(bulk, RequestOptions.DEFAULT);
        System.out.println("删除文档状态：" + response.status());
    }

    /**
     * 通过 UpdateRequest 根据指定的id修改一个文档
     */
    @Test
    public void testUpdateDocument() throws IOException {
        UpdateRequest request = new UpdateRequest();
        request.index("jason_doc_10");
        request.id("1");

        UserModel model = new UserModel();
        model.setId("user_01_update");
        model.setName("用户01__update");
        model.setAge(22);
        model.setBir(new Date());
        model.setAddress("上海__update");

        request.doc(JSONObject.toJSONString(model), XContentType.JSON);

        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        System.out.println("更新后的响应状态：" + response.status());
    }

    /**
     * 通过 GetRequest 查询指定index和id的文档
     */
    @Test
    public void testGetDocument() throws IOException {
        GetRequest getRequest = new GetRequest("jason_doc_10", "TQzkIoAB0sa_iFrDpdgu");
        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        if (exists) {
            GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
            System.out.println("查询的文档：" + response.getSourceAsString());
        }
    }

    /**
     * SearchSourceBuilder 查询全部
     *
     */
    @Test
    public void testSearchDocument() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("jason_doc_10");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery())
                .from(0)
                .size(10)
                .postFilter(QueryBuilders.matchAllQuery())
                .sort("age", SortOrder.DESC);

        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        System.out.println("响应状态：" + response.status());
        SearchHits hits = response.getHits();
        System.out.println("符合条件的文档总数：" + hits.getTotalHits());

        SearchHit[] searchHits = hits.getHits();
        for (SearchHit searchHit : searchHits) {
            System.out.println("每个文档数据：" + searchHit.getSourceAsString());
        }
    }
}
