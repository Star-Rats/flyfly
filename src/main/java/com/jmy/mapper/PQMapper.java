package com.jmy.mapper;

import com.jmy.entity.PutQuestion;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PQMapper {

    @Select("select * from b_pq where user_id = #{userId} order by pq_id desc Limit 0,10")
    List<PutQuestion> queryPQOnePageByUserId(Integer userId);

    @Select("select pq_title from b_pq where pq_id = #{pqId}")
    String queryPQTitleByPQId(Integer pqId);

    @Select("select * from b_pq order by pq_id desc")
    List<PutQuestion> queryAllQuestion();

    @Insert("INSERT INTO b_pq(user_id, pq_title, pq_date, pq_text, pq_read, pq_answer, pq_type, pq_status,pq_kiss,pq_like)" +
            "VALUES (#{userId},#{pqTitle} , #{pqDate}, #{pqText}, #{pqRead},#{pqAnswer}, #{pqType}, #{pqStatus},#{pqKiss},#{pqLike});")
    void insertQuestion(PutQuestion question);

    @Select("select * from b_pq where pq_top = '置顶'order by pq_id desc Limit 0,4")
    List<PutQuestion> getQuestionByTop();

    @Select("select * from b_pq where pq_id = #{pqId}")
    PutQuestion queryPQByPQId(Integer pqId);

    @Select("select * from b_pq order by pq_read desc Limit 0,10")
    List<PutQuestion> queryPQByRead();

    @Update("update b_pq set pq_read = pq_read + 1 where pq_id = #{pqId}")
    void updatePQRead(Integer pqId);

    @Update("update b_pq set pq_answer = pq_answer+1 where pq_id = #{pqId}")
    void updatePQAnswer(Integer pqId);

    @Delete("delete from b_pq where pq_id = #{pqId}")
    int deletePutQuestionByPQId(Integer pqId);

    @Update("UPDATE b_pq SET pq_top = '置顶' WHERE pq_id = #{pqId}")
    int updatePQTopByPQId(Integer pqId);

    @Update("UPDATE b_pq SET pq_top = '0' WHERE pq_id = #{pqId}")
    int updatePQNotTopByPQId(Integer pqId);

    @Update("UPDATE b_pq SET pq_lev = '0' WHERE pq_id = #{pqId}")
    int updatePQNotLevByPQId(Integer pqId);

    @Update("UPDATE b_pq SET pq_lev = '精' WHERE pq_id = #{pqId}")
    int updatePQLevByPQId(Integer pqId);

    @Update("update b_pq set pq_answer = pq_answer-1 where pq_id = #{pqId}")
    int descsPQAnswerByPQId(Integer pqId);

    @Update("update b_pq set pq_status ='完结' where pq_id=#{pqId}")
    void updatePQStatusByPQId(Integer pqId);
}
