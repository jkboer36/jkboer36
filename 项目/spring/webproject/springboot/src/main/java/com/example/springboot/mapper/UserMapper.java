package com.example.springboot.mapper;

import com.example.springboot.entity.user;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
   user finduser(Integer user_id);
}
