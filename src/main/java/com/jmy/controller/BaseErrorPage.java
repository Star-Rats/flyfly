package com.jmy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class BaseErrorPage implements ErrorController {

    Logger logger = LoggerFactory.getLogger(BaseErrorPage.class);

    @Override
    public String getErrorPath() {
        logger.debug("返回错误页面");
        return "other/404";
    }

    @RequestMapping
    public String error() {
        return getErrorPath();
    }
}
