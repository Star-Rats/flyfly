package com.jmy.controller;

import com.jmy.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService = null;

    @RequestMapping("/delete/{messageId}")
    public String deleteMessage(@PathVariable("messageId") Integer messageId){
        messageService.deleteMessageByMessageId(messageId);
        return "/user/getMessage/1";
    }
}
