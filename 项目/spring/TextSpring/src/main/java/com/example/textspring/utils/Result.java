package com.example.textspring.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private String code;   //状态码 200为成功，400为失败
    private String msg;
    private T record;   //实例的整个数据

    public Result(){
    }

    public Result (T record){
        this.record=record;
    }
    public static <T> Result<T> success(T data){
        Result<T> result=new Result<>(data);
        result.setCode("200");
        result.setMsg("成功");
        return result;
    }
    public static Result error(String code,String msg){
        Result result=new Result();
        result.setCode("400");
        result.setMsg("失败");
        return result;
    }
}
