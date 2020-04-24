package com.demo.elastic.service;

import com.demo.elastic.bean.ElasticData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms.ParsedBucket;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author NekoChips
 * @description 基于 RestHighLevelClient 的 ES Service
 * @date 2020/4/22
 */
@Service
public class ElasticService
{

    private static final Logger logger = LoggerFactory.getLogger(ElasticService.class);

    static final String AGG_SUFFIX = ".keyword";

    @Autowired
    RestHighLevelClient highLevelClient;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * 创建索引
     * @param index 索引名称
     */
    public void buildIndex(String index)
    {
        try
        {
            if (indexExist(index))
            {
                logger.error("index already exist or some problem in indexExist, index: {}", index);
                return;
            }
            CreateIndexRequest request = new CreateIndexRequest(index);
            Settings.Builder setting = Settings.builder().put("index.number_of_shards", 3)
                    .put("index.number_of_replicas", 1);
            request.settings(setting);
            CreateIndexResponse response = highLevelClient.indices().create(request, RequestOptions.DEFAULT);
            if (!response.isAcknowledged())
            {
                logger.error("init index fail, index: {}", index);
                return;
            }
        }
        catch (Exception e)
        {
            logger.error("ElasticService build index fail, index: {}", index);
        }
    }

    /**
     * 判断索引是否已经存在
     * @param index 索引名称
     * @return 是否存在
     */
    public boolean indexExist(String index)
    {
        try
        {
            GetIndexRequest request = new GetIndexRequest(index).local(false).humanReadable(true).includeDefaults(false)
                    .indicesOptions(IndicesOptions.STRICT_EXPAND_OPEN);

            return highLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        }
        catch (Exception e)
        {
            logger.error("ElasticService indexExist fail, index: {}", index);
            return Boolean.TRUE;
        }
    }

    /**
     * 新增索引文档
     * @param index 索引名称
     * @param elasticData elasticBean 封装类
     * @param <T> data 泛型
     */
    public <T> void put(String index, ElasticData<T> elasticData)
    {
        try
        {
            IndexRequest indexRequest = new IndexRequest(index, elasticData.getType(), elasticData.getId());
            indexRequest.source(objectMapper.writeValueAsBytes(elasticData.getData()), XContentType.JSON);
            highLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        }
        catch (IOException e)
        {
            logger.error("ElasticService put error, index: {}, data: {}", index, elasticData.getData());
        }
    }

    /**
     * 根据文档 id 查询数据
     * @param index 索引名称
     * @param id 文档 id
     * @param tClass 对象 class
     * @param <T> 对象泛型
     * @return 对象
     */
    public <T> T searchById(String index, String id, Class<T> tClass)
    {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = searchRequest.source();
        searchSourceBuilder.query(QueryBuilders.matchQuery("id", id));
        List<T> tList = processSearch(searchRequest, tClass);
        if (tList == null || tList.isEmpty())
        {
            return null;
        }
        if (tList.size() > 1)
        {
            throw new RuntimeException("many result founded which need only one");
        }
        return tList.get(0);
    }

    /**
     * 条件查询数据，ES 中执行类似如下语句：
     * GET /spring-elastic-rest/Employee/_search
     * {
     *   "query": {
     *     "bool": {
     *       "must": [
     *         {
     *           "match": {
     *             "lastName": "Smith"
     *           }
     *         },
     *         {
     *           "match": {
     *             "age": 25
     *           }
     *         }
     *       ]
     *     }
     *   }
     * }
     * @param index 索引名称
     * @param queryParams 查询参数
     * @param tClass 对象 class
     * @param <T> 对象泛型
     * @return 对象列表
     */
    public <T> List<T> search(String index, Map<String, Object> queryParams, Class<T> tClass)
    {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = searchRequest.source();
        // 多条件查询 bool
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 使用 must ，即需要满足所有条件
        queryParams.forEach((key, value) -> boolQueryBuilder.must(QueryBuilders.matchQuery(key, value)));
        searchSourceBuilder.query(boolQueryBuilder);
        return processSearch(searchRequest, tClass);
    }

    /**
     * 全文检索，ES 中执行类似如下语句：
     * GET /spring-elastic-rest/Employee/_search
     * {
     *   "query": {
     *     "multi_match": {
     *       "query": "rock Fir",
     *       "fields": ["lastName", "about"]
     *     }
     *   }
     * }
     * @param index 索引名称
     * @param fields 需要检索的字段列表
     * @param keywords 查询关键字
     * @param tClass 对象 class
     * @param <T> 对象泛型
     * @return 对象列表
     */
    public <T> List<T> search(String index, String[] fields, String keywords, Class<T> tClass)
    {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = searchRequest.source();
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(keywords, fields));
        return processSearch(searchRequest, tClass);
    }

    /**
     * 根据字段中的值进行聚合统计, ES 中执行类似如下语句：
     * GET /spring-elastic-rest/Employee/_search
     * {
     *   "aggs": {
     *     "all_interest": {
     *       "terms": {
     *         "field": "interest.keyword"
     *       }
     *     }
     *   }
     * }
     * @param index 索引名称
     * @param name 聚合名称
     * @param field 字段名称
     * @return 统计结果 map
     */
    public Map<String, Long> statisticsByField(String index, String name, String field)
    {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder sourceBuilder = searchRequest.source();
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        sourceBuilder.aggregation(AggregationBuilders.terms(name).field(field + AGG_SUFFIX));
        List<Map<String, Long>> results = processAggSearch(searchRequest, name);
        if (CollectionUtils.isEmpty(results)) {
            logger.error("no result with aggregation, name: {}, field: {}", name, field);
            return null;
        }
        return results.get(0);
    }

    /**
     * 聚合操作的公共方法
     * @param searchRequest SearchRequest 封装对象
     * @param names 聚合名称
     * @return 聚合结果
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Long>> processAggSearch(SearchRequest searchRequest, String... names)
    {
        List<Map<String, Long>> results = new ArrayList<>();
        try
        {
            SearchResponse response = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            Map<String, Aggregation> aggregationMap = response.getAggregations().getAsMap();
            if (names.length == 0)
            {
                logger.error("names must not be null");
                return null;
            }
            results = Arrays.stream(names).map(name ->
            {
                ParsedStringTerms stringTerms = (ParsedStringTerms) aggregationMap.get(name);
                List<ParsedBucket> buckets = (List<ParsedBucket>) stringTerms.getBuckets();
               return buckets.stream()
                        .collect(Collectors.toMap(
                                ParsedBucket::getKeyAsString,
                                ParsedBucket::getDocCount,
                                (x, y) -> x));
                
            }).collect(Collectors.toList());
        }
        catch (IOException e)
        {
            logger.error("process aggregation search with error, searchRequest: {}", searchRequest);
        }
        return results;
    }

    /**
     * 搜索执行公共方法
     * @param searchRequest SearchRequest 封装对象
     * @param tClass 对象 class
     * @param <T> 对象泛型
     * @return list
     */
    private <T> List<T> processSearch(SearchRequest searchRequest, Class<T> tClass)
    {
        try
        {
            SearchResponse response = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            if (hits == null || hits.length == 0)
            {
                logger.info("nothing founded.");
                return null;
            }
            return Arrays.stream(hits).map(SearchHit::getSourceAsMap).map(map ->
            {
                try
                {
                    return objectMapper.readValue(objectMapper.writeValueAsString(map), tClass);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    return null;
                }
            }).collect(Collectors.toList());
        }
        catch (IOException e)
        {
            logger.error("process search with error, searchRequest: {}", searchRequest);
            return null;
        }
    }
}
