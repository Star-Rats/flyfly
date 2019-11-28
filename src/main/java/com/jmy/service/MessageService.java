package com.jmy.service;

import com.jmy.entity.Message;
import com.jmy.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper = null;

    public List<Message> getMessageByUserId(Integer userId,Integer page) {
        if (page == null) {
            page = 1;
        }
        return messageMapper.queryMessageByUserId(userId,page);
    }

    public int deleteMessageByMessageId(Integer messageId) {
        return messageMapper.deleteMessageByMessageId(messageId);
    }
}
