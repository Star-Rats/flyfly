package com.jmy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LikeMapper {

    @Select("select like_name from b_like")
    List<String> queryLikenames();
}
