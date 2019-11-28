package com.jmy.service;

import com.jmy.domain.Question;
import com.jmy.mapper.QuestionMapper;
import com.jmy.utils.MapperUtil;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private QuestionMapper questionMapper = null;
    @Autowired
    private TransportClient client;

    public void createIndex(){
        // 读取数据源
        List<Question> qList = questionMapper.queryAllQuestion();
        // 创建索引文件
        client.admin().indices().prepareCreate("fly").get();
        // 向索引文件中添加document
        try {
            for (Question question : qList){
                String questionJson = MapperUtil.MP.writeValueAsString(question);
                client.prepareIndex("fly","question",question.getPqId().toString())
                        .setSource(questionJson).get();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public List<Question> queryIndex(String query) {
        // 使用client封装查询对象
        MultiMatchQueryBuilder matchQuery = QueryBuilders.multiMatchQuery(query, "pqTitle","username");
        SearchRequestBuilder search = client.prepareSearch("fly");
        SearchResponse response = search.setQuery(matchQuery).setFrom(0).setSize(10).get();

        // 获取Question对象
        SearchHit[] searchHits = response.getHits().getHits();
        List<Question> qList = new ArrayList<>();
        try {
            for (SearchHit hit : searchHits) {
                String questionJson = hit.getSourceAsString();
                qList.add(MapperUtil.MP.readValue(questionJson,Question.class));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return qList;
    }
}
