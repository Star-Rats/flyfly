package com.jmy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PQLike {
    private Integer likeId;
    private Integer pqId;
    private String like;
}
