package com.jmy.fallback;

import com.jmy.cloud.entity.R;
import com.jmy.cloud.entity.StudentSign;
import com.jmy.service.StudentSignService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class MyFallBackFactory implements FallbackFactory<StudentSignService> {
    @Override
    public StudentSignService create(Throwable cause) {
        return new StudentSignService() {
            @Override
            public R sign(StudentSign studentSign) {
                return R.fail("签到服务不可用...");
            }

            @Override
            public R querySignByName(String name) {
                return R.fail("姓名查询服务不可用...");
            }

            @Override
            public R queryAllSign() {
                return R.fail("签到查询服务不可用...");
            }
        };
    }
}
