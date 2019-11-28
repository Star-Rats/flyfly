package com.jmy.mapper;

import com.jmy.domain.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {

    @Select("SELECT pq_lev as pqLev,pq_id,pq_title,pq_date as data,pq_kiss as kiss,pq_type as type,b_user.user_id,photo as url,username,pq_answer as answer,lev\n" +
            "from b_pq INNER JOIN b_user ON b_pq.user_id = b_user.user_id order by pq_id desc Limit ${page * 8},8")
    List<Question> queryQuestionByPage(Integer page);

    @Select("SELECT pq_lev as pqLev,pq_id,pq_title,pq_date as data,pq_kiss as kiss,pq_type as type,pq_status as status ,b_user.user_id,photo as url,username,pq_answer as answer,lev\n" +
            "from b_pq INNER JOIN b_user ON b_pq.user_id = b_user.user_id order by b_pq.pq_answer desc Limit 0 ,10")
    List<Question> queryQuestionByHot();

    @Select("SELECT pq_lev as pqLev,pq_id,pq_title,pq_date as data,pq_kiss as kiss,pq_type as type,pq_status as status ,b_user.user_id,photo as url,username,pq_answer as answer,lev\n" +
            "from b_pq INNER JOIN b_user ON b_pq.user_id = b_user.user_id")
    List<Question> queryAllQuestion();

    @Select("SELECT pq_lev as pqLev,pq_id,pq_title,pq_date as data,pq_kiss as kiss,pq_type as type,pq_status as status ,b_user.user_id,photo as url,username,pq_answer as answer,lev\n" +
            "from b_pq INNER JOIN b_user ON pq_like = #{like} and b_pq.user_id = b_user.user_id order by RAND() Limit #{sum}")
    List<Question> queryQuestionByLike(@Param("like") String like,@Param("sum") int sum);
}
