package com.upchina.spring.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by anjunli on  2021/11/8
 **/
@SpringBootTest
public class SpringJdbcTest {
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void getCount() throws SQLException {
        System.out.println("默认数据源为：" + this.dataSource.getClass());
        System.out.println("数据库连接实例为：" + this.dataSource.getConnection());
        Integer integer = this.jdbcTemplate.queryForObject("select count(*) from t_group", Integer.class);
        System.out.println("表中共有"+integer+"条数据");
    }

    @Test
    public void insertTest(){
        String sql = "insert into t_test set uid= ?,name =?";
        //添加数据
/*
        this.jdbcTemplate.update(sql, "001", "a");
        this.jdbcTemplate.update(sql, "002", "b");
*/

        String sql2 = "select count(*) from t_test";
/*        String s = this.jdbcTemplate.queryForObject(sql2, String.class);
        System.out.println(s);*/

        /**将一条记录放到1个map里面*/
/*        String sql3 = "select * from t_test where id=1";
        Map<String, Object> map = this.jdbcTemplate.queryForMap(sql3);
        System.out.println(map);*/

        /** 执行查询语句，返回一个List集合，List中存放的是Map类型的数据*/
        String sql4 = "select uid,name from t_test";
/*        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql4);
        JSONArray jsonArray = new JSONArray();
        for(Map<String,Object> map:list){
            Object json = JSON.toJSON(map);
            System.out.println(json);
            jsonArray.add(map);
        }
        System.out.println(jsonArray);*/

        /** query使用rowMap做映射返回一个对象*/
        RowMapper<User> userRowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.uid = rs.getString(1);
                user.name = rs.getString(2);
                return user;
            }
        };
        try {
            List<User> userList = this.jdbcTemplate.query(sql4, userRowMapper);
            for(User user:userList){
                System.out.println(user);
            }
        }catch(Exception e){
            System.out.println("**********");
            e.printStackTrace();
        }
    }
}
class User{
    String uid = "";
    String name = "";

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
