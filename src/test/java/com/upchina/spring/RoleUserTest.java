package com.upchina.spring;

import com.upchina.spring.jdbc.repository.crud.PersonalRepository;
import com.upchina.spring.jdbc.repository.crud.RoleUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Created by anjunli on  2021/12/22
 **/
@SpringBootTest
public class RoleUserTest {

    @Autowired
    PersonalRepository personalRepository;

    @Test
    public void test1(){
        List<RoleUser> roleUser = personalRepository.getRoleUser(1);
        roleUser.forEach(System.out::println);
    }
}
