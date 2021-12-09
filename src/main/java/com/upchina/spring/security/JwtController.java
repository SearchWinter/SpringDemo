package com.upchina.spring.security;

import com.upchina.spring.common.CommonResult;
import com.upchina.spring.jdbc.repository.User;
import com.upchina.spring.jdbc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by anjunli on  2021/12/8
 * JWT(JSON Web Token)
 **/
@RestController
@RequestMapping("/login")
public class JwtController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenManager tokenManager;

    @RequestMapping("/login")
    public CommonResult userLogin(@RequestBody User user){
        User user1 = userRepository.findUserByIdAndName(user.getId(), user.getName());
        String jwtToken = tokenManager.generateJwtToken(user1);
        return CommonResult.success("生成Token", jwtToken);
    }
}
