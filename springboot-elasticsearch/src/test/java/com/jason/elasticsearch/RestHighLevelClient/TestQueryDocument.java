package com.jason.elasticsearch.RestHighLevelClient;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
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

/**
 * 使用客户端的方式进行document文档的各种查询
 *
 * @author WongChenHoll
 * @date 2022-4-14 星期四 11:01
 **/
@SpringBootTest
public class TestQueryDocument {

    @Autowired
    @Qualifier(value = "restHighLevelClient")
    RestHighLevelClient client;

    /**
     * 分页查询全部
     */
    @Test
    public void testQueryAll() throws IOException {
        // 创建查询请求
        SearchRequest request = new SearchRequest();
        request.indices("jason_doc_10");

        // 创建查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery())
                .from(0)
                .size(20)
                .sort("age", SortOrder.ASC);
        request.source(builder);
        // 查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits()) {
            System.out.println("查询结果：" + hit.getSourceAsString());
        }
    }

    /**
     * 模糊查询：wildcardQuery
     */
    @Test
    public void testLikeQuery() throws IOException {
        // 创建查询请求
        SearchRequest request = new SearchRequest();
        request.indices("jason_doc_10");

        // 创建查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.wildcardQuery("name", "*四*")); // 查询字段name中带有“四”的数据
        request.source(builder);
        // 查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("查询总数：" + hits.getTotalHits().value);
        for (SearchHit hit : hits) {
            System.out.println("查询结果：" + hit.getSourceAsString());
        }
    }

    /**
     * <pre>
     *     matchQuery 查询，会将搜索词分词，再与目标查询字段进行匹配，若分词中的任意一个词与目标字段匹配上，则可查询到。
     * </pre>
     */
    @Test
    public void testMatchQuery() throws IOException {
        // 创建查询请求
        SearchRequest request = new SearchRequest();
        request.indices("jason_doc_10");
        // 创建查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("address", "上海市"));
        request.source(builder);
        // 查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("查询总数：" + hits.getTotalHits());
        for (SearchHit hit : hits.getHits()) {
            System.out.println("查询到的数据：" + hit.getSourceAsString());
        }
    }

    /**
     * 默认使用 match_phrase 时会精确匹配查询的短语，需要全部单词和顺序要完全一样，标点符号除外。
     */
    @Test
    public void testMatchPhraseQuery() throws IOException {
        // 创建查询请求
        SearchRequest request = new SearchRequest();
        request.indices("jason_doc_10");
        // 创建查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchPhraseQuery("address", "上海市"));
        request.source(builder);
        // 查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("查询总数：" + hits.getTotalHits());
        for (SearchHit hit : hits.getHits()) {
            System.out.println("查询到的数据：" + hit.getSourceAsString());
        }
    }

    @Test
    public void testMatchPhrasePrefixQuery() throws IOException {
        // 创建查询请求
        SearchRequest request = new SearchRequest();
        request.indices("jason_doc_10");
        // 创建查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchPhrasePrefixQuery("address", "上海市"));
        request.source(builder);
        // 查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("查询总数：" + hits.getTotalHits());
        for (SearchHit hit : hits.getHits()) {
            System.out.println("查询到的数据：" + hit.getSourceAsString());
        }
    }

    /**
     * 使用 multiMatchQuery 进行多个字段匹配查询
     */
    @Test
    public void testMultiMatchQuery() throws IOException {
        // 创建查询请求
        SearchRequest request = new SearchRequest();
        request.indices("jason_doc_10");
        // 创建查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.multiMatchQuery("上海", "address", "name")); // 查询address或者name中匹配到“上海”分词的数据
        request.source(builder);
        // 查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("查询总数：" + hits.getTotalHits());
        for (SearchHit hit : hits.getHits()) {
            System.out.println("查询到的数据：" + hit.getSourceAsString());
        }
    }

    @Test
    public void testTermQuery() throws IOException {
        // 创建查询请求
        SearchRequest request = new SearchRequest();
        request.indices("jason_doc_10");
        // 创建查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termQuery("address", "中国"));
        request.source(builder);
        // 查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("查询总数：" + hits.getTotalHits());
        for (SearchHit hit : hits.getHits()) {
            System.out.println("查询到的数据：" + hit.getSourceAsString());
        }
    }

    /**
     * 使用 boolQuery 和 must
     */
    @Test
    public void testBoolQueryMust() throws IOException {
        // 创建查询请求
        SearchRequest request = new SearchRequest();
        request.indices("jason_doc_10");
        // 创建查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        // 下面查询条件的意思是：既要address字段中有"上海市"还要name字段中有"大"
        builder.query(QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("address", "上海市"))
                .must(QueryBuilders.matchPhraseQuery("name", "大")));
        request.source(builder);
        // 查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("查询总数：" + hits.getTotalHits());
        for (SearchHit hit : hits.getHits()) {
            System.out.println("查询到的数据：" + hit.getSourceAsString());
        }
    }

    /**
     * 使用 boolQuery 和 should
     */
    @Test
    public void testBoolQueryShould() throws IOException {
        // 创建查询请求
        SearchRequest request = new SearchRequest();
        request.indices("jason_doc_10");
        // 创建查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        // 下面查询条件的意思是：既要address字段中有"上海市"还要name字段中有"大"
        builder.query(QueryBuilders.boolQuery()
                .should(QueryBuilders.matchPhraseQuery("address", "上海"))
                .should(QueryBuilders.matchPhraseQuery("name", "大")));
        request.source(builder);
        // 查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("查询总数：" + hits.getTotalHits());
        for (SearchHit hit : hits.getHits()) {
            System.out.println("查询到的数据：" + hit.getSourceAsString());
        }
    }
}
