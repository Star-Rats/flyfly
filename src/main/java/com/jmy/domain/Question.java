package com.jmy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private String pqTitle;
    private String username;
    private Integer lev;
    private String data;
    private String status;
    private Integer kiss;
    private String type;
    private String url;
    private Integer read;
    private Integer pqId;
    private Integer userId;
    private Integer answer;
    private String pqLev;
}
