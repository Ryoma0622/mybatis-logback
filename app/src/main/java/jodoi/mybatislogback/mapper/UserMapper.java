package jodoi.mybatislogback.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    @Insert("insert into user (username, email, password, created_at) values ('username-1', 'email-1', 'password-1', encrypt('hogehoge', #{key}))")
//    @Update("update user set created_at = encrypt('hogehoge', #{key}) where id = 1")
    int failedInsert(@Param("key") String key);

    @Insert("insert into user (username, email, password) values ('username-1', 'email-1', 'password-1')")
    int insert();

    @Select("SELECT username from User")
    String select();
}
