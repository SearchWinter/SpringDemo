package com.upchina.spring.jdbc.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonalRepository extends CrudRepository<User,Long> {

    @Query("select * from t_user where age=:age")
    List<User> findUser(int age);


    @Query("SELECT t1.name,t1.pwd,t1.age,t1.role,t2.name as ROLE_NAME FROM t_user t1\n" +
            "JOIN t_role t2 ON t1.role=t2.role_id\n" +
            "WHERE t1.role=:role")
    //方法里面给参数名与SQL中的参数名相对应
    List<RoleUser> getRoleUser(@Param("role")int role);
}
