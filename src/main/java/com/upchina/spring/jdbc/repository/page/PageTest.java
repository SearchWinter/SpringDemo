package com.upchina.spring.jdbc.repository.page;

import com.upchina.spring.jdbc.repository.crud.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by anjunli on  2021/12/20
 * PagingAndSortingRepository
 * 分页查询用法
 **/
@SpringBootTest
public class PageTest {
    @Autowired
    PageRepository pageRepository;

    /**
     * 分页
     * */
    @Test
    public void test1() {
        //page从0开始
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<User> userPage = pageRepository.findAll(pageRequest);
        System.out.println("数据的总条数：" + userPage.getTotalElements());
        System.out.println("总页数：" + userPage.getTotalPages());

        List<User> users = userPage.getContent();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 对单列排序
     * Sort:该对象封装了排序规则以及指定的排序字段(对象的属性来表示)
     * direction:排序规则
     * properties:指定做排序的属性
     */
    @Test
    public void test2() {
        List<User> users = (List)pageRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        for(User user:users){
            System.out.println(user);
        }
    }

    /**
     * 多列的排序处理
     * */
    @Test
    public void test3() {
        Sort.Order order1 = new Sort.Order(Sort.Direction.ASC, "id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "age");
        Sort sort = Sort.by(order2, order1);
        List<User> users = (List)pageRepository.findAll(sort);
        for(User user:users){
            System.out.println(user);
        }
    }

    @Test
    public void test4(){
        String str = null;
        Assert.notNull(str,"this string is Null");
    }

}
