package com.example.textspring.service.iml;

import com.example.textspring.controller.common.UserResult;
import com.example.textspring.controller.common.UserpasswordDto;
import com.example.textspring.mapper.UserMapper;
import com.example.textspring.entity.User;
import com.example.textspring.service.UserService;
import com.example.textspring.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class UserServiceIml implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page searchuser(Integer pagenum, Integer pagesize, String username) {
        int startId = (pagenum - 1) * pagesize, size = pagesize;
        List<User> data = userMapper.selectPageWithParam(startId,size,username);
        int total = userMapper.selectTotalWithParam(username);
        Page page = new Page();
        page.setTotal(total);
        page.setData(data);
        return page;
    }

    @Override
    //id为空则新增，id不为空则修改
    public int save(User user){
        if(user.getUser_id()==null & user.getUser_name()!=null & user.getUser_account()!=null & user.getUser_password()!=null){
            return userMapper.insert(user);
        }else {
            return userMapper.update(user);
        }
    }

    @Override
    public User finduser(Integer user_id){
        return userMapper.finduser(user_id);
    }

    @Override
    public UserResult login(User user) {
        User usertest = userMapper.finduser_ac(user.getUser_account());

        if (usertest ==null){
            return UserResult.fail(user);
        }
        if (user.getUser_password().equals( usertest.getUser_password())){
            return UserResult.succ(usertest);
        }
        return UserResult.fail(user);
    }

    @Override
    public UserResult register(User user) {
        User test = userMapper.finduser_ac(user.getUser_account());
        if (test != null){
            return UserResult.fail(user);
        }
        userMapper.register(user);
        return UserResult.succ(user);
    }

    @Override
    public UserResult changepassword(UserpasswordDto userpasswordDto) {
        User user = userMapper.finduser_ac(userpasswordDto.getUser_account());
        if (user ==null){
            return UserResult.fail(userpasswordDto);
        }
        if (userpasswordDto.getUser_password().equals( user.getUser_password())){
            userMapper.changepassword(userpasswordDto);
            return UserResult.succ(userpasswordDto);
        }
        return UserResult.fail(userpasswordDto);
    }



}
