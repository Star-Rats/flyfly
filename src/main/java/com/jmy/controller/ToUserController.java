package com.jmy.controller;

import com.jmy.entity.Answer;
import com.jmy.entity.PutQuestion;
import com.jmy.entity.User;
import com.jmy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class ToUserController {
    @Autowired
    private UserService userService = null;

    @RequestMapping("/home")
    public String toUserHome(Integer userId, Model model){
        User user = userService.getAllUserByUserId(userId);
        List<PutQuestion> onePage = userService.getOnePagePutQuestionByUserId(userId);
        List<Answer> aList = userService.getOnePageAnswerByUserId(userId);

        model.addAttribute("onePage",onePage);
        model.addAttribute("user",user);
        model.addAttribute("aList",aList);
        return "/user/home";
    }
}
