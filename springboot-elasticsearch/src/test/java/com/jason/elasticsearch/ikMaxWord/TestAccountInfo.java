package com.jason.elasticsearch.ikMaxWord;

import com.jason.elasticsearch.commons.enums.BankEnum;
import com.jason.elasticsearch.commons.utils.PrintUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

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
        request.source("{\"acct_no\":\"030001\",\"acct_name\":\"啦啦啦\",\"acct_balance\":934028.91,\"open_bank\":\"CD\",\"open_date\":\"2022-05-15\",\"remark\":\"体验一下成都银行，顺便办了张卡。\"}", XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println("添加文档的响应：" + response.status());
        System.out.println("添加的数据：" + response.toString());
    }

    @Test
    public void batchAddData() throws IOException {
        BulkRequest bulk = new BulkRequest();
        bulk.add(new IndexRequest(INDEX).source("{\"acct_no\":\"02002\",\"acct_name\":\"张无忌\",\"acct_balance\":932.30,\"open_bank\":\"BJ\",\"open_date\":\"2022-05-03\",\"remark\":\"\"}", XContentType.JSON));
        bulk.add(new IndexRequest(INDEX).source("{\"acct_no\":\"02003\",\"acct_name\":\"张朝阳\",\"acct_balance\":1924892.25,\"open_bank\":\"BJ\",\"open_date\":\"2022-04-20\",\"remark\":\"\"}", XContentType.JSON));
        bulk.add(new IndexRequest(INDEX).source("{\"acct_no\":\"02004\",\"acct_name\":\"马化腾\",\"acct_balance\":42563932.30,\"open_bank\":\"SZ\",\"open_date\":\"2022-01-01\",\"remark\":\"\"}", XContentType.JSON));
        bulk.add(new IndexRequest(INDEX).source("{\"acct_no\":\"02005\",\"acct_name\":\"马云\",\"acct_balance\":5433092083.54,\"open_bank\":\"HZ\",\"open_date\":\"2022-01-03\",\"remark\":\"\"}", XContentType.JSON));
        BulkResponse responses = client.bulk(bulk, RequestOptions.DEFAULT);
        System.out.println("共添加：" + responses.getItems().length + " 条数据");
    }

    @Test
    public void testQueryAll() throws IOException {
        SearchRequest request = new SearchRequest(INDEX);
        request.source(SearchSourceBuilder.searchSource().query(QueryBuilders.matchAllQuery()).sort("open_date", SortOrder.DESC));

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println(response.getHits().getTotalHits());
        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsString());
            PrintUtil.printMap(hit.getSourceAsMap(), "_class");
        }
    }

    /**
     * 查询第一页内容，每页5条数据，并按 acct_balance 字段倒叙排列
     */
    @Test
    public void page() throws IOException {
        SearchRequest request = new SearchRequest(INDEX);
        request.source(SearchSourceBuilder.searchSource().query(QueryBuilders.matchAllQuery()).sort("acct_balance", SortOrder.DESC).from(0).size(5));

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            PrintUtil.printMap(hit.getSourceAsMap(), "_class");
        }
    }

    @Test
    public void match() throws IOException {
        SearchRequest request = new SearchRequest(INDEX);
        request.source(SearchSourceBuilder.searchSource().query(QueryBuilders.matchQuery("open_bank", BankEnum.HF.name())));

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            PrintUtil.printMap(hit.getSourceAsMap(), "_class");
        }
    }

    @Test
    public void matchPhrase() throws IOException {
        SearchRequest request = new SearchRequest(INDEX);
        request.source(SearchSourceBuilder.searchSource().query(QueryBuilders.matchPhraseQuery("remark", "补办的")));

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            PrintUtil.printMap(hit.getSourceAsMap(), "_class");
        }
    }


    @Test
    public void term() throws IOException {
        SearchRequest request = new SearchRequest(INDEX);
        request.source(SearchSourceBuilder.searchSource().query(QueryBuilders.termQuery("remark", "补办的")));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            PrintUtil.printMap(hit.getSourceAsMap(), "_class");
        }
    }


    @Test
    public void scroll() throws IOException {
        Scroll scroll = new Scroll(TimeValue.timeValueMillis(1L));

        SearchRequest request = new SearchRequest(INDEX);
        request.source(SearchSourceBuilder.searchSource().query(QueryBuilders.matchAllQuery()).size(5));
        request.scroll(scroll);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        String scrollId = response.getScrollId();
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            PrintUtil.printMap(hit.getSourceAsMap(), "_class");
        }

        while (hits.length > 0) {
            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(scroll);
            SearchResponse searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
            scrollId = searchResponse.getScrollId();
            hits = searchResponse.getHits().getHits();
            for (SearchHit hit : hits) {
                PrintUtil.printMap(hit.getSourceAsMap(), "_class");
            }
        }

        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        System.out.println("清理scroll是否成功：" + clearScrollResponse.isSucceeded());
    }

}
