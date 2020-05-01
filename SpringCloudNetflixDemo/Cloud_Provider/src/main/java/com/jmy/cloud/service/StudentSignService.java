package com.jmy.cloud.service;

import com.jmy.cloud.entity.R;
import com.jmy.cloud.entity.StudentSign;
import org.springframework.stereotype.Service;


public interface StudentSignService {
    R sign(StudentSign studentSign);

    R querySignByName(String name);

    R queryAllSign();
}
