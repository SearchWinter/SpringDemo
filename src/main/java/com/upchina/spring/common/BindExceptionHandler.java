package com.upchina.spring.common;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * 统一异常处理
 */
//@RestControllerAdvice
public class BindExceptionHandler {

    //ajax form提交  单@Validated
    @ExceptionHandler(BindException.class)
    public CommonResult handleBindException(BindException e) throws JSONException {
        List<FieldError> allErrors = e.getFieldErrors();
        JSONObject errorFiled = new JSONObject();
        for (FieldError errorMessage : allErrors) {
            errorFiled.put(errorMessage.getField(), errorMessage.getDefaultMessage());
        }
        return new CommonResult(-2, "输入参数错误,请注意", errorFiled);
    }

    //ajax json提交  @RequestBody @Validated
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) throws JSONException {
        JSONObject errorFiled = new JSONObject();
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        for (ObjectError allError : allErrors) {
            FieldError fieldError = (FieldError) allError;
            errorFiled.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new CommonResult(-2, "输入参数错误,请注意", errorFiled);
    }

    // 自定义异常返回
    @ExceptionHandler(SystemException.class)
    public CommonResult handleSystemException(SystemException e) {
        return new CommonResult(-1, e.getErrorMsg(), e.getErrorData());
    }

    //处理空指针的异
    @ExceptionHandler(value = NullPointerException.class)
    public CommonResult exceptionHandler(NullPointerException e) {
        //log.error ....
        return new CommonResult(-1, "系统空指针异常！", e.getMessage());
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public CommonResult sqlException(SQLIntegrityConstraintViolationException e){
        return new CommonResult(-1, "数据保存失败！", e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public CommonResult exceptionHandler(HttpServletRequest req, Exception e) {
        //log.error ....
        return new CommonResult(-1, "系统异常！", e.getMessage());
    }

}
