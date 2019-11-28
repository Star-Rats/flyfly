package com.jmy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jmy.entity.User;
import com.jmy.service.UserService;
import com.jmy.utils.CookieUtils;
import com.jmy.utils.MapperUtil;
import com.jmy.utils.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
@RequestMapping("/user")
public class UserHomeController {

    @Autowired
    private UserService userService = null;
    @PostMapping("/login.action")
    public String login(String email, String pass, String vercode, Model model, HttpSession session,
    HttpServletResponse res, HttpServletRequest req){

        User user = userService.doLogin(email, pass);
        String code = (String) session.getAttribute("code");
        if (!code.equalsIgnoreCase(vercode)) {
            model.addAttribute("msg","系统鉴定你可能不是人类~");
            return "forward:/user/login.html";
        }
        if (user != null) {
            model.addAttribute("user",user);
            session.setAttribute("user",user);
            try {
                CookieUtils.setCookie(req,res,"user", MapperUtil.MP.writeValueAsString(user),true);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return "redirect:/index.html";
        } else {
            model.addAttribute("msg","邮箱或密码错误哦~");
            return "forward:/user/login.html";
        }
    }

    @RequestMapping("/verify.action")
    public void verify(HttpServletResponse res,HttpSession session){
        VerifyCode verifyCode = new VerifyCode();
        try {
            verifyCode.drawImage(res.getOutputStream());
            String code = verifyCode.getCode();
            session.setAttribute("code",code);
            System.out.println(code);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
