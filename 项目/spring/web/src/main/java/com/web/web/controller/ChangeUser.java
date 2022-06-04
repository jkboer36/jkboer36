package com.web.web.controller;

import com.web.web.common.result;
import com.web.web.entity.User;
import com.web.web.mapper.changeuser;
import com.web.web.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/changeuser")
public class ChangeUser {

    @Autowired
    private changeuser changeuser;

    @Autowired
    private UserService userService;

    //新增和修改
    @PostMapping("/change")
    public result save(@RequestBody User user){
        if (userService.save(user)==1){
            return result.success("修改用户信息成功");
        }
        else {
            return result.error("无效用户id");
        }
    }


    //删除
    @PostMapping("/delete")
    public result delete(@RequestBody User user) {
        System.out.println(user.getUser_id());
        if (changeuser.delete(user.getUser_id()) == 1) {
            return result.success("删除用户成功");
        } else {
            return result.error("无效用户id");
        }
    }

}
