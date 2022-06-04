package com.web.web.common;

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
}
