package com.upchina.spring.jdbc.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * UserRepository从CrudRepository延伸。 它提供了实体的类型及其主键。
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserById(Long id);

    User queryUserById(Long id);

    boolean existsUserByNameAndPwd(String name, String pwd);

    boolean existsUserByName(String name);

    /**1、更新这种操作要添加 Modifying注解
     * 2、返回值根据sql的返回值来
     */
    @Modifying
    @Query("UPDATE t_user SET NAME=:name,age=:age WHERE id=:id")
    int updateUser(@Param("name") String name, @Param("age") int age, @Param("id") long id);

}
