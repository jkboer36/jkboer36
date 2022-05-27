package com.example.textspring.controller.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class result {
    private Integer code;
    private String msg;//信息
    private Object data;

    static Integer CODE_200 = 200;//成功
    static Integer CODE_400 = 400;//错误

    public static result error(String msg){
        return new result(CODE_400,msg,null);
    }

    public static result success(String msg) {
        return new result(CODE_200,msg,null);
    }

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
