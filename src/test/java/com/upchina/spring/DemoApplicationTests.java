package com.upchina.spring;

import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * Base64多线程环境下，结果是否一致
     * */
    public static class TestBase64 {
        public static void main(String[] args) {
            Base64 base64 = new Base64();
            /// 1233456 MTIzNDU2
            String base64Str = base64.encodeAsString("123456".getBytes());
            System.out.println(base64Str);
            int size = 1000;
            Executor executor = Executors.newFixedThreadPool(size);
            for (int i = 0; i < size; i++) {
                executor.execute(() -> {
                    while (true) {
                        String baseStr = base64.encodeAsString("123456".getBytes());
                        if (!base64Str.equals(baseStr)) {
                            System.out.println(baseStr);
                            System.out.println("not equal");
                            System.exit(0);
                        }
                    }
                });
            }
        }

    }

    @Test
    public void test3(){
        PasswordEncoder pw= new BCryptPasswordEncoder();
        //加密
        String encode=pw.encode("123");
        System.out.println(encode);
        //比较密码
        boolean matches=pw.matches("123",encode);
        System.out.println("==============================");
        System.out.println(matches);
    }

    @Test
    public void test4(){
        int count = 0;
        for(;;){
            count++;
            System.out.println(count);
            if(count==10000)
                break;
        }
    }
}
