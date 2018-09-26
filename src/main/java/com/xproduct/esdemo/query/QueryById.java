package com.xproduct.esdemo.query;


import com.xproduct.esdemo.configuration.EsConfig;
import com.xproduct.esdemo.esdatabase.RestEsFactory;
import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class QueryById {
    private static Logger log = Logger.getLogger(QueryById.class);

    public List<Map<String,Object>> getBookById(String author, String indexName) throws Exception {
        EsConfig.conEsClient();

        // get client from Factory
        RestHighLevelClient client = RestEsFactory.getRestClient();
        // get client deriect set
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(new HttpHost("192.168.32.140", 9200, "http")));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // query specified field
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("author",author);
        boolQueryBuilder.must(queryBuilder);
        searchSourceBuilder.query(boolQueryBuilder);
        // query all field
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        searchSourceBuilder.aggregation(AggregationBuilders.terms("top_10_states").field("state").size(10));
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(indexName);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest);

// for debug use
//        System.out.println(searchResponse.getHits());
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();

        // get ES query value
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println("search result is:" + hit.getSourceAsString());
            Map<String,Object> groupMap = new LinkedHashMap<String,Object>();
            groupMap.put("file", hit.getSourceAsString());
            result.add(groupMap);
        }
        return result;
    }
}
