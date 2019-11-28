package com.jmy.controller.forword;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginForward {

    @RequestMapping("/")
    public String loginForward(){
        return "/user/login";
    }
}
