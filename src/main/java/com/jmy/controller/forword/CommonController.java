package com.jmy.controller.forword;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {
    @RequestMapping("/{path}.html")
    public String forword(@PathVariable("path") String path){
        return "/common/" + path;
    }
}
