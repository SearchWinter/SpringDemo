package com.upchina.spring.jdbc.repository;

import lombok.Data;

/**
 * Created by anjunli on  2021/12/1
 * 保存结果集的实体类，字段不用全部对应，没有的值为NULL
 **/
@Data
public class RoleUser {
    private Long id;
    private String name;
    private String pwd;
    private int age;
    private int role;
    //如果属性名用驼峰命名法，查询结果中的别名必须有下划线(role_name) 否则值为NULL
    private String roleName;

}
