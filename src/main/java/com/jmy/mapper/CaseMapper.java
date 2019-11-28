package com.jmy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CaseMapper {

    @Update("update blog set zan = zan+1 ")
    void zan();

    @Select("select zan from blog where zan_id = 1")
    Integer queryZan();
}
