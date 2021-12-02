package com.upchina.spring.jdbc.repository;

import org.springframework.data.repository.CrudRepository;

/**
 * UserRepository从CrudRepository延伸。 它提供了实体的类型及其主键。
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserById(Long id);

    User queryUserById(Long id);

    boolean existsUserByNameAndPwd(String name, String pwd);
}
