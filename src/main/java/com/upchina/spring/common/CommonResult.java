package com.upchina.spring.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 异常处理，返回统一格式的数据*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult {
    private int ret;
    private String message;
    private Object data;

    public static CommonResult success(String message, Object data) {
        return new CommonResult(0, message, data);
    }

    public static CommonResult error(String message, Object data) {
        return new CommonResult(-1, message, data);
    }
}
