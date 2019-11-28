package com.jmy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerUser {
    private String username;
    private Integer lev;
    private Integer userId;
    private String url;
    private String answerDate;
    private String answerComment;
    private Integer answerZan;
    private Integer answerAdopt;
    private Integer pqId;
    private Integer answerId;
}
