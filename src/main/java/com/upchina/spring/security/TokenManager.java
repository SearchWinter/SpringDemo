package com.upchina.spring.security;

import com.upchina.spring.jdbc.repository.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by anjunli on  2021/12/8
 **/
@Component
public class TokenManager implements Serializable {
    private static final long serialVersionID = 7008375124389347049L;
    private static final long TOKEN_VALIDITY = 1 * 60 * 60;

    @Value("${secret")
    private String jwtSecret;

    //生成Token
    public String generateJwtToken(User userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getName())  //设置
                .setIssuedAt(new Date(System.currentTimeMillis()))  //设置token起始时间
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))   //设置token有效时间
                .signWith(SignatureAlgorithm.HS512, jwtSecret)  //选择加密算法
                .compact();
    }

    //校验token

    //根据Token获取里面的信息
    public String getUsernameFromToken(){

        return "";
    }


}
