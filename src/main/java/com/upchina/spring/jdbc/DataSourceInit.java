package com.upchina.spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by anjunli on  2021/12/16
 *
 **/
@Configuration
@Component
public class DataSourceInit {

    @Bean
    DataSourceInitializer initializer(DataSource dataSource) {

        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);

        ClassPathResource classPathResource = new ClassPathResource("schema.sql");
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(classPathResource);
        initializer.setDatabasePopulator(populator);

        return initializer;
    }
}
