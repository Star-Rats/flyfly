package com.jmy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userId;
    private String username;
    private Integer sex;
    private String city;
    private String password;
    private String joindate;
    private String sign;
    private Integer lev;
    private String message;
    private String email;
    private String photo;
    private Integer kiss;
    private Integer countOfAnswer;
    private Integer admin;
    private Integer countOfQuestion;
}
