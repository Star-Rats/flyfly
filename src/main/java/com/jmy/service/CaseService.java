package com.jmy.service;

import com.jmy.mapper.CaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseService {

    @Autowired
    private CaseMapper caseMapper;
    public void great() {
        caseMapper.zan();
    }

    public Integer getZan() {
        return caseMapper.queryZan();
    }
}
