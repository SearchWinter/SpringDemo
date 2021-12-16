package com.upchina.spring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by anjunli on  2021/12/9
 * JWT方法测试
 **/
public class JwtTest {
    private static String jwtSecret = "gWyLCIRARv5ZL#3!3cDTdM^E3v7nd7XPCxTc4zDevJ#kdNtWrUWKjIvDbgNPdu5WLSu0jDLEMqxafA1J0#ZHrZe94CaQsKNuK%o";

    public static void main(String[] args) {
        String name = "tom";
        HashMap<String, Object> claims = new HashMap<>();
        JwtBuilder builder = Jwts.builder();
        String token = builder.setClaims(claims)
                .setSubject(name)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
        System.out.println(token);
    }

    @Test
    public void getNameFromToken(){
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b20iLCJleHAiOjE2MzkwNDQ5NzEsImlhdCI6MTYzOTA0NDM3MX0.6RZsRKlZ4khyjiMNiDU2YMsAhgmhk2jtC5TGt6DNauqcS3nvJxPCI-AgOvoB9KXAiuuBK_mbkBfpysGUPGb2wA";
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        System.out.println(claims.getSubject());
    }
}
