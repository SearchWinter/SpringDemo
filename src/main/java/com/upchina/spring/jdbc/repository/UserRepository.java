package com.upchina.spring.jdbc.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository extends CrudRepository<User,Long> {
    User findUserById(Long id);

    User queryUserById(Long id);

    boolean existsUserByNameAndPwd(String name, String pwd);
}
