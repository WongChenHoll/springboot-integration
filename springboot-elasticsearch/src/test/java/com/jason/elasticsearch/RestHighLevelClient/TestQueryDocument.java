package com.jason.elasticsearch.RestHighLevelClient;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.metrics.ParsedCardinality;
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
     * <pre>
     *  GET /jason_doc_10/_search
     *  {
     *      "query": {
     *        "match_all": {}
     *       },
     *       "from": 0,
     *      "size": 20,
     *      "sort": [
     *      {
     *           "age": {
     *           "order": "desc"
     *           }
     *      }
     *      ]
     *  }
     * </pre>
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
        System.out.println("总数：" + response.getHits().getTotalHits());
        for (SearchHit hit : response.getHits()) {
            System.out.println("查询结果：" + hit.getSourceAsString());
        }
    }

    /**
     * 模糊查询：wildcardQuery
     * <pre>
     *          GET /jason_doc_10/_search
     *          {
     *              "query": {
     *              "wildcard": {
     *                  "name": "*四*"
     *               }
     *             }
     *          }
     * </pre>
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
     *     matchQuery 查询，会将搜索词分词，再与目标查询字段进行匹配，
     *     若分词中的任意一个词与目标字段匹配上，则可查询到。
     * </pre>
     * <pre>
     *      GET /jason_doc_10/_search
     *      {
     *          "query": {
     *          "match": {
     *              "address": "上海市"
     *            }
     *          }
     *      }
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
     * 和 matchPhrasePrefixQuery 差不多
     * <pre>
     *      GET /jason_doc_10/_search
     *      {
     *          "query": {
     *          "match_phrase": {
     *              "address": "上海市"
     *            }
     *          }
     *      }
     * </pre>
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

    /**
     * 使用 matchPhrasePrefixQuery 精确匹配，和 match_phrase 差不多
     * <pre>
     *      GET /jason_doc_10/_search
     *      {
     *          "query": {
     *          "match_phrase_prefix": {
     *              "address": "上海市"
     *            }
     *          }
     *      }
     * </pre>
     */
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
     * <pre>
     *          GET /jason_doc_10/_search
     *          {
     *              "query": {
     *              "multi_match": {
     *                  "query": "上海",
     *                  "fields": [
     *                  "name",
     *                  "address"
     *                  ]
     *                }
     *              }
     *          }
     * </pre>
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

    /**
     * 使用term查询，不会对查询内容进行分词。会将整个作为一个分词。
     * <pre>
     *      GET /jason_doc_10/_search
     * {
     *   "query": {
     *     "term": {
     *       "address": {
     *         "value": "上海市"
     *       }
     *     }
     *   }
     * }
     * </pre>
     */
    @Test
    public void testTermQuery() throws IOException {
        // 创建查询请求
        SearchRequest request = new SearchRequest();
        request.indices("jason_doc_10");
        // 创建查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termQuery("address", "上海市"));
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
     * <pre>
     *          GET /jason_doc_10/_search
     * {
     *   "query": {
     *     "bool": {
     *       "must": [
     *         {
     *           "match": {
     *             "address": "上海市"
     *           }
     *         },
     *         {
     *           "match_phrase": {
     *             "name": "大"
     *           }
     *         }
     *       ]
     *     }
     *   }
     * }
     * </pre>
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
     * <pre>
     *      GET /jason_doc_10/_search
     * {
     *   "query": {
     *     "bool": {
     *       "should": [
     *         {
     *           "match_phrase": {
     *             "address": "上海"
     *           }
     *         },
     *         {
     *           "match_phrase": {
     *             "name": "大"
     *           }
     *         }
     *       ]
     *     }
     *   }
     * }
     * </pre>
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

    /**
     * <pre>
     * 年龄去重后的数量。
     *      请求：
     *      GET /jason_doc_10/_search
     *      {
     *          "query": {
     *          "match_all": {}
     *          },
     *          "size": 0,
     *          "aggregations": {
     *          "u_age": {
     *              "cardinality": {
     *              "field": "age"
     *              }
     *          }
     *          }
     *      }
     *      结果：
     *      {
     *   "took" : 1,
     *   "timed_out" : false,
     *   "_shards" : {
     *     "total" : 1,
     *     "successful" : 1,
     *     "skipped" : 0,
     *     "failed" : 0
     *   },
     *   "hits" : {
     *     "total" : {
     *       "value" : 13,
     *       "relation" : "eq"
     *     },
     *     "max_score" : null,
     *     "hits" : [ ]
     *   },
     *   "aggregations" : {
     *     "u_age" : {
     *       "value" : 12
     *     }
     *   }
     * }
     * </pre>
     */
    @Test
    public void testCountDistinctByAge() throws IOException {
        // 创建查询请求
        SearchRequest request = new SearchRequest();
        request.indices("jason_doc_10");
        // 创建查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        // 根据年龄去重，并起别名为：u_age
        builder.aggregation(AggregationBuilders.cardinality("distic_age").field("age"));
        request.source(builder);
        // 查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        Aggregations aggregations = response.getAggregations();
        ParsedCardinality cardinality = (ParsedCardinality) aggregations.asList().get(0);
        long count = cardinality.getValue();
        System.out.println("去重后的总数：" + count);
    }
}
