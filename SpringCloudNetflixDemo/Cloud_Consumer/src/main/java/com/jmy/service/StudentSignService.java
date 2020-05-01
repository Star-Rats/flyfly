package com.jmy.service;

import com.jmy.cloud.entity.R;
import com.jmy.cloud.entity.StudentSign;
import com.jmy.fallback.MyFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-provider",fallbackFactory = MyFallBackFactory.class)
public interface StudentSignService {
    @PostMapping("/provider/sign/save")
    R sign(StudentSign studentSign);

    @GetMapping("/provider/sign/queryByName")
    R querySignByName(@RequestParam("name") String name);

    @GetMapping("/provider/sign/queryAll")
    R queryAllSign();
}
