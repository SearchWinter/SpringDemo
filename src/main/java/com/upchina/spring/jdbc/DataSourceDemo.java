package com.upchina.spring.jdbc;


import com.upchina.spring.utils.DataSourceUtils;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by anjunli on  2021/11/8
 * 使用自定义的数据源
 **/
public class DataSourceDemo {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://192.168.7.51:3306/db_calculate";
        String user = "root";
        String pwd = "qwer12345";
        BasicDataSource dataSource = DataSourceUtils.createDataSource(url, user, pwd);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select * from t_test";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        for(Map map:maps){
            System.out.println(map);
        }
        System.out.println("默认数据源为：" + dataSource.getClass());
        System.out.println("数据库连接实例为：" + dataSource.getConnection());
    }
}
