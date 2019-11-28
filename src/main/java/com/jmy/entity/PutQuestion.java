package com.jmy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PutQuestion {
    private Integer userId;
    private Integer pqId;
    private String pqTitle;
    private String pqDate;
    private String pqText;
    private Integer pqRead;
    private Integer pqAnswer;
    private String pqType;
    private String pqStatus;
    private Integer pqKiss;
    private String pqTop;
    private String pqLike;
    private String pqLev;
}
