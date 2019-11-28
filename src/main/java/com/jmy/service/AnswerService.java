package com.jmy.service;

import com.jmy.domain.AnswerUser;
import com.jmy.entity.Answer;
import com.jmy.entity.Message;
import com.jmy.entity.PutQuestion;
import com.jmy.entity.User;
import com.jmy.mapper.AnswerMapper;
import com.jmy.mapper.MessageMapper;
import com.jmy.mapper.PQMapper;
import com.jmy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisCluster;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AnswerService {
    @Autowired
    private AnswerMapper answerMapper = null;
    @Autowired
    private PQMapper pqMapper = null;
    @Autowired
    private UserMapper userMapper = null;
    @Autowired
    private JedisCluster cluster;
    @Autowired
    private MessageMapper messageMapper = null;
    public List<Answer> getAnswerListByOnePage(Integer userId) {
        return answerMapper.queryAnswerByUserId(userId);
    }

    public PutQuestion queryPutQueston(Integer pqId) {
        return pqMapper.queryPQByPQId(pqId);
    }

    public User getUserByUserId(Integer userId) {
        return userMapper.queryUserByUserId(userId);
    }

    public  List<AnswerUser> getAnswerUserBy(Integer pqId, Integer userId) {
        List<Answer> answers = answerMapper.queryAnswerByPQId(pqId);
        List<AnswerUser> aList = new ArrayList();
        for (int i = 0; i < answers.size(); i++) {
            Answer answer = answers.get(i);
            User user = userMapper.queryUserByUserId(answer.getUserId());
            AnswerUser answerUser = new AnswerUser(user.getUsername(), user.getLev(),
                    user.getUserId(), user.getPhoto(),answer.getAnswerDate(),
                    answer.getAnswerComment(), answer.getAnswerZan(),answer.getAnswerAdopt(),answer.getPqId(),answer.getAnswerId());
            aList.add(answerUser);
        }
        Collections.reverse(aList);
        return aList;
    }

    @Transactional
    public void insertAnswer(Integer pqId, String content, Integer userId) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sf.format(new Date());
        answerMapper.insertAnswer(pqId,userId,content,date);
    }

    public void addPutQuestionRead(Integer pqId) {
        pqMapper.updatePQRead(pqId);
    }

    public void updateUserLike(Integer pqId, User user) {
        // 获取帖子类型 存入用户Redis key
        PutQuestion pq = pqMapper.queryPQByPQId(pqId);
        String key = user.getUserId() + user.getEmail();
        String like = pq.getPqLike();
        Integer likeDegree = 1;
        if (cluster.hget(key, like) != null) {
            likeDegree = Integer.parseInt(cluster.hget(key, like)) + 1;
        }
        cluster.hset(key,like,likeDegree.toString());
    }

    public void addUserCountOfAnswer(Integer userId) {
        userMapper.updateCountOfAnswer(userId);
    }

    public void addPutQuestionAnswer(Integer pqId) {
        pqMapper.updatePQAnswer(pqId);
    }

    public int deleteAnswerByAnswerId(Integer answerId) {
        return answerMapper.deleteAnswerByAnswerId(answerId);
    }

    public int descPQAnswerByPQId(Integer pqId) {
        return pqMapper.descsPQAnswerByPQId(pqId);
    }

    public void adoptAnswer(Integer answerId, Integer pqId) {
        answerMapper.updateAnswerAdoptByAnswerId(answerId);
        pqMapper.updatePQStatusByPQId(pqId);
    }

    public int sendMessageToPQUser(User answerUser, Integer pqId) {
        String answerUsername = answerUser.getUsername();
        PutQuestion pq = pqMapper.queryPQByPQId(pqId);
        String pqTitle = pq.getPqTitle();
        Integer answerUserId = answerUser.getUserId();
        Integer pqUserId = pq.getUserId();

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String answerDate = sf.format(new Date());

        return messageMapper.InsertNewMessage(new Message(0,answerUsername,answerDate,pqId,pqTitle,answerUserId,pqUserId));
    }
}
