package com.upchina.spring.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import java.util.Properties;

/**
 * @Description 连接数据库所需要的配置
 * @Date 2020/8/31  10:09
 **/
public class DataSourceUtils {
    public static BasicDataSource createDataSource(String url, String username, String password) throws Exception {
        Properties prop = new Properties();
        //要传递给JDBC驱动程序以建立连接的连接URL。
        prop.setProperty("url", url);
        //用户名
        prop.setProperty("username", username);
        //密码
        prop.setProperty("password", password);
        //驱动类名
        prop.setProperty("driverClassName", "com.mysql.jdbc.Driver");
//        prop.setProperty("driverClassName","com.mysql.cj.jdbc.Driver");        //mysql-connector-java 版本较高时使用

        //校验连接池中的sql语句
        prop.setProperty("validationQuery", "select 1 ");
        //从连接池中引用前是否验证，默认为true
        prop.setProperty("testOnBorrow", "true");
        //创建对象后是否对其进行验证，默认为false
        prop.setProperty("testOnCreate", "true");
        //指示对象在返回到池之前是否要进行验证。
        prop.setProperty("testOnReturn", "true");

        //连接池是否自动管理应程序中使用完毕的连接 默认为false
        prop.setProperty("removeAbandonedOnBorrow", "true");
        prop.setProperty("removeAbandonedOnMaintenance", "true");
        //是否在自动回收超时连接的时候打印连接的超时错误
        prop.setProperty("logAbandoned", "true");
        //如果为真，则连接池在每次调用池连接上的方法时记录堆栈跟踪，并保留最新的堆栈跟踪，
        prop.setProperty("abandonedUsageTracking", "true");
        return BasicDataSourceFactory.createDataSource(prop);
    }

}