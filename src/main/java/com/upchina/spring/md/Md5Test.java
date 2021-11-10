package com.upchina.spring.md;

import org.springframework.util.DigestUtils;

/**
 * Created by anjunli on  2021/11/10
 * Spring自带的加密工具
 **/
public class Md5Test {
    public static void main(String[] args) {
        String s1 = "mwKWpCnYotwIq9oh8tXIiazUSCyE7KS6xe1APw4DtJp8ifWlySGV4K7SkhzfP0ZU";
        String s = DigestUtils.md5DigestAsHex(("any"+s1).getBytes());
        System.out.println(s);
    }
}