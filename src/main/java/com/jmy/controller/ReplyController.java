package com.jmy.controller;

import com.jmy.entity.User;
import com.jmy.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/jie")
public class ReplyController {

    @Autowired
    private AnswerService answerService = null;
    @RequestMapping("/reply")
    public String answer(Integer pqId, String content, HttpSession session){
        User answerUser = (User) session.getAttribute("user");
        Integer userId = answerUser.getUserId();
        answerService.insertAnswer(pqId,content,userId);

        // 用户回答数加一
        answerService.addUserCountOfAnswer(userId);

        // 帖子回复数加一
        answerService.addPutQuestionAnswer(pqId);

        // 记录回帖记录 发送给发帖人
        answerService.sendMessageToPQUser(answerUser,pqId);
        return "forward:/answer/click?pqId=" + pqId;
    }

    @RequestMapping("/delete")
    public String deleteAnswer(Integer answerId, Integer pqId){
        // 删除回答 帖子回答数减一
        answerService.deleteAnswerByAnswerId(answerId);
        answerService.descPQAnswerByPQId(pqId);

        return "forward:/answer/click?pqId=" + pqId;
    }
}
