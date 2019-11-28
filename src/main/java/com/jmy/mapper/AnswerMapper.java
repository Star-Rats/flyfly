package com.jmy.mapper;

import com.jmy.entity.Answer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnswerMapper {
    @Select("select * from b_answer where user_id = #{userId} order by answer_id desc Limit 0,3")
    List<Answer> queryAnswerByUserId(Integer userId);

    @Select("select * from b_answer where pq_id = #{pqId}")
    List<Answer> queryAnswerByPQId(Integer pqId);

    @Insert("insert into b_answer(user_id,pq_id,answer_comment,answer_date) values(#{userId},#{pqId},#{content},#{date})")
    void insertAnswer(@Param("pqId") Integer pqId, @Param("userId") Integer userId,@Param("content") String content,String date);

    @Delete("delete from b_answer where answer_id = #{answerId}")
    int deleteAnswerByAnswerId(Integer answerId);

    @Update("update b_answer set answer_adopt=1 where answer_id=#{answerId}")
    void updateAnswerAdoptByAnswerId(Integer answerId);
}
