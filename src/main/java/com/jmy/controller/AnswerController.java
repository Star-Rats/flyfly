package com.jmy.controller;

import com.jmy.domain.AnswerUser;
import com.jmy.entity.PutQuestion;
import com.jmy.entity.User;
import com.jmy.mapper.PQMapper;
import com.jmy.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService = null;
    @RequestMapping("/click")
    public String click(Integer pqId, Model model, HttpSession session){
        PutQuestion putQuestion = answerService.queryPutQueston(pqId);
        User user = answerService.getUserByUserId(putQuestion.getUserId());
        List<AnswerUser> aList = answerService.getAnswerUserBy(pqId,putQuestion.getUserId());

        // 点击帖子触发用户喜好逻辑 以及帖子浏览数加+1逻辑
        answerService.addPutQuestionRead(pqId);
       // 使用redis记录用户喜好
        User nowUser = (User) session.getAttribute("user");
        answerService.updateUserLike(pqId,nowUser);

        // 封装页面返回数据
        model.addAttribute("pq",putQuestion);
        model.addAttribute("pquser",user);
        model.addAttribute("aList",aList);
        return "forward:/jie/detail.html";
    }

    // 采纳建议 此贴终结
    @RequestMapping("/adopt")
    public String adoptAnswer(Integer answerId,Integer pqId){
        answerService.adoptAnswer(answerId,pqId);
        return "forward:/answer/click?pqId=" + pqId;
    }
}
