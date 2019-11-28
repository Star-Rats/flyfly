package com.jmy.controller;

import com.jmy.entity.PutQuestion;
import com.jmy.entity.User;
import com.jmy.service.PutQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private PutQuestionService putQuestionService;

    @PostMapping("/add")
    public String addQuestion(PutQuestion question, HttpSession session, String vercode, HttpServletRequest req){
        if (!vercode.equals("2")) {
            req.setAttribute("msg","系统判定你可能不是人类");
        }
        User user = (User) session.getAttribute("user");
        putQuestionService.addQuestion(question,user);
        return "forward:/index.html";
    }

    // 管理员删帖
    @RequestMapping("/delete")
    public String deleteQuestion(Integer pqId){
        putQuestionService.deletePutQuestionByPQId(pqId);
        return "forward:/index.html";
    }

    // 管理员置顶
    @RequestMapping("/top")
    public String topQuestion(Integer pqId){
        putQuestionService.updateQuestionTopByPQId(pqId);
        System.out.println("置顶成功");
        return "forward:/answer/click?pqId=" + pqId;
    }

    // 管理员取消置顶
    @RequestMapping("/ntop")
    public String notTopQuestion(Integer pqId){
        putQuestionService.updateQuestionNotTopByPQId(pqId);
        System.out.println("取消置顶成功");
        return "forward:/answer/click?pqId=" + pqId;
    }

    // 管理员加精
    @RequestMapping("/lev")
    public String setQuestionLev(Integer pqId){
        putQuestionService.updateQuestionLevByPQId(pqId);
        System.out.println("加精成功");
        return "forward:/answer/click?pqId=" + pqId;
    }

    // 管理员取消加精
    @RequestMapping("/nlev")
    public String setQuestionNotLev(Integer pqId){
        putQuestionService.updateQuestionNotLevByPQId(pqId);
        System.out.println("取消精品成功");
        return "forward:/answer/click?pqId=" + pqId;
    }
}
