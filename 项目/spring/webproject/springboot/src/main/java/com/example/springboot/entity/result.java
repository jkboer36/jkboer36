package com.example.springboot.entity;

import lombok.Data;



@Data
public class result {
    private Integer code;
    private String msg;
    private Object data;

    public static result fail(Object data,String err_msg){
        result m = new result();
        m.setCode(400);
        m.setMsg(err_msg);
        m.setData(data);
        return m;
    }

    public static result succ(Object data){
        result m = new result();
        m.setCode(200);
        m.setMsg("成功");
        m.setData(data);
        return m;
    }
}
