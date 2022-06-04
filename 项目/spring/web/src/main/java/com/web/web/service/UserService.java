package com.web.web.service;

import com.web.web.entity.User;
import com.web.web.mapper.changeuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private changeuser changeuser;

    //id为空则新增，id不为空则修改
    public int save(User user){
        if(user.getUser_id()==null){
            return changeuser.insert(user);
        }else {
            return changeuser.update(user);
        }
    }
}
