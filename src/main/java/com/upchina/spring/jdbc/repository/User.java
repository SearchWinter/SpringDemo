package com.upchina.spring.jdbc.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

/**
 * Created by anjunli on  2021/11/25
 * 和表中的字段一一对应
 **/
@Data
@Table("t_user")
public class User {
    @Id
    private long id;
    private String name;
    private String pwd;
    private int age;
    @LastModifiedDate
    private Date update_time;
}
