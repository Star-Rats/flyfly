package com.jmy.mapper;

import com.jmy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from b_user where email=#{email} and password=#{password}")
    User queryUserByEmailAndPassword(@Param("email") String email,@Param("password") String password);

    @Select("select * from b_user where user_id = #{userId}")
    User queryUserByUserId(Integer userId);

    @Select("select * from b_user order by count_of_answer desc Limit 0,12")
    List<User> getUserTopByAnswerCount();

    @Update("update b_user set count_of_answer = count_of_answer+1  where user_id = #{userId}")
    void updateCountOfAnswer(Integer userId);
}
