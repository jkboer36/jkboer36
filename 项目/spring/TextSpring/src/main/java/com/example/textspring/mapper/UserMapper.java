package com.example.textspring.mapper;

import com.example.textspring.controller.common.UserpasswordDto;
import com.example.textspring.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    //查询满足条件的某一页用户
    @Select("select * from user where user_name like concat(\"%\",#{username},\"%\") limit #{pagenum},#{pagesize}")
    List<User> selectPageWithParam(Integer pagenum, Integer pagesize, String username);

    //查询满足条件的所有用户数
    @Select("select count(*) from user where user_name like concat(\"%\",#{username},\"%\")")
    int selectTotalWithParam(String username);

    @Insert("INSERT into user(user_account, user_name, user_password, user_root) VALUES " +
            "(#{user_account}, #{user_name}, #{user_password}, #{user_root})")
    int insert(User user);

    int update(User user);

    //删除
    @Delete("delete from user where user_id = #{user_id}")
    Integer delete(@Param("user_id") Integer user_id);

    User finduser(Integer user_id);

    User finduser_ac(String User_account);

    Integer register(User user);

    void changepassword(UserpasswordDto userpasswordDto);


}
