package com.upchina.spring.jdbc.repository;

import com.upchina.spring.common.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by anjunli on  2021/12/1
 * 通过登录验证，测试 Validated注解的使用
 **/
@RestController
@RequestMapping(value = "/login")
public class LoginUserController {
    @Autowired
    UserRepository userRepository;

    //@Validated 对传参进行校验，有异常则被捕获
    @RequestMapping(value = "/verify")
    public CommonResult verifyUser(@RequestBody @Validated LoginUser loginUser){
        boolean bool = userRepository.existsUserByNameAndPwd(loginUser.getName(), loginUser.getPwd());
        if(bool){
            return CommonResult.success("账户密码正确", 1);
        }else {
            return CommonResult.success("账户或密码错误", -1);
        }
    }

}
