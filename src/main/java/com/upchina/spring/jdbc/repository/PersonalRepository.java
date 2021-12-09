package com.upchina.spring.jdbc.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonalRepository extends CrudRepository<User, Long> {

    @Query("select * from t_user where age=:age")
    List<User> findUser(int age);


    /**
     * 1、方法里面给参数名与SQL中的参数名相对应
     * 2、SQL语句查询结果的字段名称要和返回的实体类的名称一致，否则将会无法自动赋值
     * */
    @Query("SELECT t1.name,t1.pwd,t1.age,t1.role,t2.name as ROLE_NAME FROM t_user t1\n" +
            "JOIN t_role t2 ON t1.role=t2.role_id\n" +
            "WHERE t1.role=:role")
    List<RoleUser> getRoleUser(@Param("role") int role);

    /**
     * @Query 注解可以和Spring自定义的方法关键字结合使用
     * */
    @Query("select * from t_user where 1=1 and age=:age")
    List<User> getUserByRole(@Param("age") String age, String role);
}
