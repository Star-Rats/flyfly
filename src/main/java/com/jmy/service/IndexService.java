package com.jmy.service;

import com.jmy.domain.Question;
import com.jmy.entity.PutQuestion;
import com.jmy.entity.User;
import com.jmy.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexService {
    @Autowired
    private PQMapper pqMapper = null;
    @Autowired
    private UserMapper userMapper = null;
    @Autowired
    private QuestionMapper questionMapper = null;
    @Autowired
    private JedisCluster cluster = null;
    @Autowired
    private LikeMapper likeMapper = null;
    // 置顶区
    public List<Question> top(){
        List<PutQuestion> pList = pqMapper.getQuestionByTop();
        List<User> uList = new ArrayList();
        List<Question> qList = new ArrayList();
        for (int i = 0; i < pList.size(); i++) {
            PutQuestion putQuestion = pList.get(i);
            User user = userMapper.queryUserByUserId(putQuestion.getUserId());
            uList.add(user);
            Question question = new Question();
            question.setData(putQuestion.getPqDate());
            question.setKiss(user.getKiss());
            question.setLev(user.getLev());
            question.setPqTitle(putQuestion.getPqTitle());
            question.setStatus(putQuestion.getPqStatus());
            question.setType(putQuestion.getPqType());
            question.setUsername(user.getUsername());
            question.setPqId(putQuestion.getPqId());
            question.setUserId(user.getUserId());
            question.setUrl(user.getPhoto());
            question.setRead(putQuestion.getPqRead());
            qList.add(question);
        }
        return qList;
    }

    public List<User> getAnswerUser() {
        return userMapper.getUserTopByAnswerCount();
    }

    public List<PutQuestion> getPutQuestionByRead() {
        return pqMapper.queryPQByRead();
    }

    public List<Question> getQuestionByHot() {
        return questionMapper.queryQuestionByHot();
    }

    public List<Question> getUserLikeQuestion(User user) {

        String key = user.getUserId() + user.getEmail();
        List<String> likes = likeMapper.queryLikenames();
        Map<String,Integer> map = new HashMap();

        for (String like : likes) {
            if (like != null && cluster.hget(key,like) != null) {
                Integer likeDegree = Integer.parseInt(cluster.hget(key,like));
                map.put(like,likeDegree);
            }
        }

        // 遍历map找出前三爱好
        String[] like= new String[3];
        int[] value = new int[3];
        for (String likeKey : map.keySet()) {
            int likeValue = map.get(likeKey);
            for (int i = 0; i < value.length; i++) {
                if (likeValue > value[i]) {
                    // 替换
                    for (int j = value.length-1; j > i; j--) {
                        value[j] = value[j-1];
                        like[j] = like[j-1];
                    }
                    value[i] = likeValue;
                    like[i]= likeKey;
                    break;
                }
            }
        }
        String bestLike = like[0];
        String middleLike = like[1];
        String lastLike = like[2];

        int total = value[0] + value[1] + value[2];
        if (total == 0) {
            total = 1;
        }
        List<Question> bList = questionMapper.queryQuestionByLike(bestLike,20*value[0]/total);
        List<Question> mList = questionMapper.queryQuestionByLike(middleLike,20*value[1]/total);
        List<Question> lList = questionMapper.queryQuestionByLike(lastLike,20*value[2]/total);
        List<Question> list = new ArrayList();

        list.addAll(bList);
        list.addAll(mList);
        list.addAll(lList);

        return list;
    }
}
