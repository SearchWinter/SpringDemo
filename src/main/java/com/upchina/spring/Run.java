package com.upchina.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaRepositories
@EnableScheduling
@SpringBootApplication(scanBasePackages="com.upchina.spring")
public class Run {

    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }

}
