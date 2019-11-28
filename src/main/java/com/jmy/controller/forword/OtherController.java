package com.jmy.controller.forword;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("other")
public class OtherController {
    @RequestMapping("/{path}.html")
    public String forword(@PathVariable("path") String path){
        return "/other/" + path;
    }
}
