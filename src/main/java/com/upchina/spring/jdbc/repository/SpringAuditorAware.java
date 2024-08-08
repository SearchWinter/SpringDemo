package com.upchina.spring.jdbc.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Created by anjunli on  2021/12/15
 **/
@Configuration
public class SpringAuditorAware implements AuditorAware {
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("system");
    }
}
