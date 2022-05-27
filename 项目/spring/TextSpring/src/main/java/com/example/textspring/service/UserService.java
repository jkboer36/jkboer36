package com.example.textspring.service;

import com.example.textspring.controller.common.UserResult;
import com.example.textspring.controller.common.UserpasswordDto;
import com.example.textspring.entity.User;
import com.example.textspring.utils.Page;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public Page searchuser(Integer pagenum, Integer pagesize, String user_name);

    public int save(User user);

    public User finduser(Integer user_id);

    UserResult login(User user);

    UserResult register(User user);

    UserResult changepassword(UserpasswordDto userpasswordDto);

}
