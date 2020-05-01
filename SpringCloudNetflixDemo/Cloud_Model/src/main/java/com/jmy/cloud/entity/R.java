package com.jmy.cloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R {
    private int code;
    private String msg;
    private Object data;

    public static R ok(String str) {
        return new R(200,str,null);
    }

    public static R fail(String str) {
        return new R(201,str,null);
    }

    public static R ok(List<StudentSign> stuList) {
        return new R(200,"查询成功!",stuList);
    }
}
