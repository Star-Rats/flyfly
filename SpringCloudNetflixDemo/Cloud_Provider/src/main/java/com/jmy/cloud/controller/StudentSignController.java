package com.jmy.cloud.controller;

import com.jmy.cloud.entity.R;
import com.jmy.cloud.entity.StudentSign;
import com.jmy.cloud.service.StudentSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class StudentSignController {

    @Autowired
    private StudentSignService studentSignService;
    // 签到
    @PostMapping("/provider/sign/save")
    public R sign(@RequestBody StudentSign studentSign){
        return studentSignService.sign(studentSign);
    }

    // 查询指定学生的查询信息
   @RequestMapping("/provider/sign/queryByName")
    public R querySignByName(@RequestParam("name") String name){
        return studentSignService.querySignByName(name);
    }

    // 查询所有签到信息
    @GetMapping("/provider/sign/queryAll")
    public R queryAllSign(){
        int i = 1/0;
        return studentSignService.queryAllSign();
    }
}
