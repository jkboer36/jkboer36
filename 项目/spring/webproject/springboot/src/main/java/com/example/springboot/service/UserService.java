package com.example.springboot.service;

import com.example.springboot.entity.user;
import com.example.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public user finduser(Integer user_id){
        return userMapper.finduser(user_id);
    }
}
