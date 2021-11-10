package com.upchina.spring.control;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by anjunli on  2021/11/9
 **/
/** 指向对应的配置文件,将配置分为一个个单独的文件，从中读取配置信息*/
//file: 指当前项目根目录  classpath: 指当前项目的类路径，即 resources 目录。
@PropertySource(value = "classpath:person.properties")
//必须是容器中的组件才能用
@Component
//第一种：表示将这个 JavaBean 中的所有属性与配置文件中以“person”为前缀的配置进行绑定。
//@ConfigurationProperties(prefix = "person")
public class Person {
    //第二种：读取配置文件中的某一个配置时
    @Value("${person.name}")
    private String name;
    @Value("${person.age}")
    private String age;
    @Value("${person.addr}")
    private String addr;

    public Person() {
    }

    public Person(String name, String age, String addr) {
        this.name = name;
        this.age = age;
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}
