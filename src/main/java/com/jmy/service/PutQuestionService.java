package com.jmy.service;

import com.jmy.entity.PutQuestion;
import com.jmy.entity.User;
import com.jmy.mapper.PQMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PutQuestionService {

    @Autowired
    private PQMapper pqMapper = null;

    public List<PutQuestion> getAllQuestion() {
        return pqMapper.queryAllQuestion();
    }

    public List<PutQuestion> getPQListByOnePage(Integer userId) {
        return pqMapper.queryPQOnePageByUserId(userId);
    }

    public String getPQTitle(Integer pqId) {
        return pqMapper.queryPQTitleByPQId(pqId);
    }

    public void addQuestion(PutQuestion question, User user) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        question.setUserId(user.getUserId());
        question.setPqAnswer(0);
        question.setPqRead(0);
        question.setPqStatus("热议");
        question.setPqDate(sf.format(new Date()));
        pqMapper.insertQuestion(question);
    }

    public int deletePutQuestionByPQId(Integer pqId) {
        return pqMapper.deletePutQuestionByPQId(pqId);
    }

    public int updateQuestionTopByPQId(Integer pqId) {
        return pqMapper.updatePQTopByPQId(pqId);
    }

    public int updateQuestionNotTopByPQId(Integer pqId) {
        System.out.println(pqId);
        return pqMapper.updatePQNotTopByPQId(pqId);
    }

    public int updateQuestionNotLevByPQId(Integer pqId) {
        return pqMapper.updatePQNotLevByPQId(pqId);
    }

    public int updateQuestionLevByPQId(Integer pqId) {
        return pqMapper.updatePQLevByPQId(pqId);
    }
}
