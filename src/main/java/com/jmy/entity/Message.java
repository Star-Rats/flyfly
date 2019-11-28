package com.jmy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Integer messageId;
    private String messageAnswerUsername;
    private String messageAnswerDate;
    private Integer messagePqId;
    private String messagePqTitle;
    private Integer messageAnswerUserId;
    private Integer messagePqUserId;
}
