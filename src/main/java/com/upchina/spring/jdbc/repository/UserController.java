package com.upchina.spring.jdbc.repository;

import com.upchina.spring.common.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anjunli on  2021/11/25
 * 测试方法
 **/
@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @RequestMapping("/getById")
    public User getUserById(@RequestParam Long id) {
        User userById = userRepository.findUserById(id);
        return userById;
    }

    @ResponseBody
    @RequestMapping("/getAll")
    public Iterable<User> getFindAllUser() {
        Iterable<User> all = userRepository.findAll();
        return all;
    }

    @RequestMapping("/query")
    public User query(@RequestParam Long id) {
        User user = userRepository.queryUserById(id);
        return user;
    }

    //指定请求路径，及请求方法
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@RequestBody List<User> user) {
        ArrayList<User> users = new ArrayList<>();
        users.addAll(user);
        //批量 使用Postman测试，传入的是User组成的JSON数组  不包含id，就是添加
        userRepository.saveAll(users);
        return "success save user";
    }

    /**
     * save方法注意事项：
     * 1、包含id，就是更新； 但如果id在表中不存在的话就会报错
     * 2、没有给定值的字段数据会清空 String->null int->0
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult updateUser(@RequestBody User user) {
        //单条
        boolean exists = userRepository.existsUserByName(user.getName());
        if (exists) {
            return CommonResult.error("数据已经存在!", user);
        } else {
            userRepository.save(user);
            return CommonResult.success("更新成功", user);
        }
    }

    @RequestMapping("/getUses")
    public CommonResult getUsers(@RequestParam int age) {
        List<User> users = personalRepository.findUser(age);
        return CommonResult.success("更新成功", users);
    }

    @RequestMapping("/getRoleUser")
    public CommonResult getRoleUsers(@RequestParam int roleId) {
        List<RoleUser> roleUsers = personalRepository.getRoleUser(roleId);
        return CommonResult.success("查询成功", roleUsers);
    }

    /**
     * 1、RequestBody 传入的对象，JOSN中key和类相对应，可以没有会自动补充成一个完整的对象，默认值：String -> null int->0
     */
    @RequestMapping("/updateUser2")
    public CommonResult updateUser2(@RequestBody User user) {
        try {
            userRepository.updateUser(user.getName(), user.getAge(), user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResult.success("更新成功", user);
    }


}
