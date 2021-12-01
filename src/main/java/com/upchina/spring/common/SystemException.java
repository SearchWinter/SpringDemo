package com.upchina.spring.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * 错误码
     */
    private int errorCode;
    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 错误信息
     */
    private Object errorData;
}
