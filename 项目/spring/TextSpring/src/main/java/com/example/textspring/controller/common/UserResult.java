package com.example.textspring.controller.common;

import lombok.Data;

import java.io.Serializable;

//用来返回与用户相关的信息
@Data
public class UserResult implements Serializable {
    private String code;   //状态码 200为成功，400为失败
    private String msg;
    private Object data;   //实例的整个数据

    public static UserResult succ(Object data){
        UserResult m = new UserResult();
        m.setCode("200");
        m.setMsg("登录成功");
        m.setData(data);
        return m;
    }

    public static UserResult fail(Object data){
        UserResult m = new UserResult();
        m.setCode("400");
        m.setMsg("登录失败");
        m.setData(data);
        return m;
    }


}
