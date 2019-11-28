package com.jmy.mapper;

import com.jmy.entity.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageMapper {

    @Select("select * from b_message where message_pq_user_id = #{userId} order by message_id desc Limit ${(page - 1)*2},2")
    List<Message> queryMessageByUserId(@Param("userId") Integer userId,@Param("page") Integer page);

    @Insert("insert into b_message values(#{messageId},#{messageAnswerUsername},#{messageAnswerDate},#{messagePqId}" +
            ",#{messagePqTitle},#{messageAnswerUserId},#{messagePqUserId})")
    int InsertNewMessage(Message message);

    @Delete("delete from b_message where message_id = #{messageId}")
    int deleteMessageByMessageId(Integer messageId);
}
