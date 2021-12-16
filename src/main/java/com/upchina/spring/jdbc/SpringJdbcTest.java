package com.upchina.spring.jdbc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        System.out.println("表中共有" + integer + "条数据");
    }

    @Test
    public void insertTest() {
        String sql = "insert into t_test set uid= ?,name =?";
        //添加数据
        this.jdbcTemplate.update(sql, "001", "a");
        this.jdbcTemplate.update(sql, "002", "b");
    }

    @Test
    public void deleteTest(){
        String sql = "delete from t_test where uid=?";
        this.jdbcTemplate.update(sql, "003");
    }

    /** 批量插入数据*/
    @Test
    public void batchInsertTest(){
        User user1 = new User("003", "c");
        User user2 = new User("004", "d");
        User user3 = new User("005", "e");
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        insertBatch(users);
    }
    public void insertBatch(final List<User> users){

        String sql = "INSERT INTO t_test (uid, name) VALUES (?, ?)";

        this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                User customer = users.get(i);
                ps.setString(1, users.get(i).uid);
                ps.setString(2, users.get(i).name);
            }

            @Override
            public int getBatchSize() {
                //控制插入的条数
                return users.size();
            }
        });
    }

    @Test
    public void queryTest() {
        String sql2 = "select count(*) from t_test";
        String s = this.jdbcTemplate.queryForObject(sql2, String.class);
        System.out.println(s);
    }

    /**
     * 将一条记录放到1个map里面
     */
    @Test
    public void queryMapTest() {
        String sql3 = "select count(*) as sum from t_test ";
        Map<String, Object> map = this.jdbcTemplate.queryForMap(sql3);
        System.out.println(map);
    }

    /**
     * 执行查询语句，返回一个List集合，List中存放的是Map类型的数据
     */
    @Test
    public void queryListTest() {
        String sql4 = "select uid,name from t_test";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql4);
        JSONArray jsonArray = new JSONArray();
        for(Map<String,Object> map:list){
            Object json = JSON.toJSON(map);
            System.out.println(map);
            jsonArray.add(map);
        }
        System.out.println(jsonArray);
    }

    /**
     * query使用rowMap做映射返回一个对象
     */
    @Test
    public void queryRowMapTest() {
        String sql = "select uid,name from t_test";
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
            List<User> userList = this.jdbcTemplate.query(sql, userRowMapper);
            for (User user : userList) {
                System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println("**********");
            e.printStackTrace();
        }
    }

    @Test
    public void queryTest2(){
        String sql = "select uid from t_test";
        List<Map<String, Object>> maps = this.jdbcTemplate.queryForList(sql);
        for(Map<String,Object> map:maps){
            Object uid = map.get("uid");
            System.out.println(uid);
        }
    }
}

class User {
    String uid = "";
    String name = "";

    public User() {
    }

    public User(String uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
