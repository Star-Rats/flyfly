package com.jmy.controller;

import com.jmy.cloud.entity.R;
import com.jmy.cloud.entity.StudentSign;
import com.jmy.service.StudentSignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "学生签到",tags = "学生签到")
public class StudentSignController {

    @Autowired
    private StudentSignService studentSignService;
    // 签到
    @PostMapping("/consumer/sign/save")
    @ApiOperation(value = "学生进行签到",notes = "学生签到信息新增")
    public R sign(@RequestBody StudentSign studentSign){
        return studentSignService.sign(studentSign);
    }

    // 查询指定学生的查询信息
    @RequestMapping("/consumer/sign/queryByName")
    @ApiOperation(value = "查询单个学生的签到信息",notes = "查询单个学生的签到信息")
    public R querySignByName(@RequestParam("name") String name){
        return studentSignService.querySignByName(name);
    }

    // 查询所有签到信息
    @GetMapping("/consumer/sign/queryAll")
    @ApiOperation(value = "查询所有学生的签到信息",notes = "查询所有学生的签到信息")
    public R queryAllSign(){
        return studentSignService.queryAllSign();
    }
}