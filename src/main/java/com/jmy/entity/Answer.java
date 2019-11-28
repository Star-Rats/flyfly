package com.jmy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    private Integer userId;
    private Integer answerId;
    private Integer pqId;
    private String pqTitle;
    private String answerDate;
    private String answerComment;
    private Integer answerZan;
    private Integer answerAdopt;
}
