package com.web.web.mapper;

import com.web.web.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface changeuser {

    //新增或修改
    @Insert("INSERT into user(user_account, user_name, user_password, user_root) VALUES " +
            "(#{user_account}, #{user_name}, #{user_password}, 1)")
    int insert(User user);

    int update(User user);

    //删除
    @Delete("delete from user where user_id = #{user_id}")
    Integer delete(@Param("user_id") Integer user_id);

}
