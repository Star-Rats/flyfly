package com.jmy.service;

import com.jmy.domain.Question;
import com.jmy.entity.PutQuestion;
import com.jmy.mapper.PQMapper;
import com.jmy.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class jieService {

    @Autowired
    private PQMapper pqMapper;
    @Autowired
    private QuestionMapper questionMapper;
    public List<PutQuestion> getHotRead() {
        return pqMapper.queryPQByRead();
    }

    public List<Question> getQuestionByDate() {
        return questionMapper.queryQuestionByPage(0);
    }

    public List<Question> getQuestionByPage(int pageNum) {
        return questionMapper.queryQuestionByPage(pageNum - 1);
    }
}
