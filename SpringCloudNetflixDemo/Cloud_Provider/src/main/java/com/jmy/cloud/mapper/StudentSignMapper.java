package com.jmy.cloud.mapper;

import com.jmy.cloud.entity.StudentSign;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentSignMapper {
    @Insert("insert into t_sign(name,class_name,s_time,type) values(#{name},#{className},now(),#{type})")
    @Options(useGeneratedKeys = true,keyProperty = "id") // 获取自增主键的值
    int sign(StudentSign studentSign);

    @Select("select id,name,class_name,s_time,type from t_sign where name = #{name}")
    List<StudentSign> querySignByName(String name);

    @Select("select id,name,class_name,s_time,type from t_sign")
    List<StudentSign> queryAllSign();

}
