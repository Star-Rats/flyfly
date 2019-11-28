package com.jmy.service;

import com.jmy.entity.Answer;
import com.jmy.entity.PutQuestion;
import com.jmy.entity.User;
import com.jmy.mapper.AnswerMapper;
import com.jmy.mapper.PQMapper;
import com.jmy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PQMapper pqMapper;
    @Autowired
    private AnswerMapper answerMapper;
    public User doLogin(String email, String password) {
        return userMapper.queryUserByEmailAndPassword(email,password);
    }

    public User getAllUserByUserId(Integer userId) {
        return userMapper.queryUserByUserId(userId);
    }

    public List<PutQuestion> getOnePagePutQuestionByUserId(Integer userId) {
        return pqMapper.queryPQOnePageByUserId(userId);
    }

    public List<Answer> getOnePageAnswerByUserId(Integer userId) {
        List<Answer> answers = answerMapper.queryAnswerByUserId(userId);
        for (int i = 0; i < answers.size(); i++) {
            Integer pqId = answers.get(i).getPqId();
            String pqTitle = pqMapper.queryPQTitleByPQId(pqId);
            answers.get(i).setPqTitle(pqTitle);
        }
        return answers;
    }
}
