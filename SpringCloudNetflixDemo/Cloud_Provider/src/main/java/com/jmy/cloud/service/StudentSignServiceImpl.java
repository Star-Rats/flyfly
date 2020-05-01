package com.jmy.cloud.service;

import com.jmy.cloud.entity.R;
import com.jmy.cloud.entity.StudentSign;
import com.jmy.cloud.mapper.StudentSignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentSignServiceImpl implements StudentSignService {

    @Autowired
    private StudentSignMapper studentSignMapper;

    @Override
    public R sign(StudentSign studentSign) {
        //每一堂课需要签到 9:30-9:40 第一次
        // 10:50-11:00 2:30-2:40 3:50-4:00
        StudentSign sign = new StudentSign();
        sign.setName(studentSign.getName());
        sign.setClassName(studentSign.getClassName());
        sign.setType("课前签到");
        if (studentSignMapper.sign(sign) == 1) {
            return R.ok("签到成功");
        } else {
            return R.fail("签到失败!");
        }
    }

    @Override
    public R querySignByName(String name) {
        List<StudentSign> stuList = studentSignMapper.querySignByName(name);
        if (stuList != null) {
            return R.ok(stuList);
        } else {
            return R.fail("暂无数据!");
        }
    }

    @Override
    public R queryAllSign() {
        List<StudentSign> signList = studentSignMapper.queryAllSign();
        if (signList.size() != 0) {
            return R.ok(signList);
        } else {
            return R.fail("暂无数据!");
        }
    }
}
