package com.upchina.spring.jdbc.repository.crud;

import com.upchina.spring.common.CommonResult;
import com.upchina.spring.jdbc.repository.page.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
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
    PageRepository pageRepository;

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

    /**
     * 接口中需要传基本数据类型的地方，尽量使用其包装类  以免给一些默认值，影响判断
     * 包装类不给值的情况下就是Null方便判断
     * */
    @RequestMapping("/getUses")
//    public CommonResult getUsers(@RequestParam int age) {
    public CommonResult getUsers(@RequestParam Integer age) {
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

    @RequestMapping("/deleteUser")
    public CommonResult deleteUser(@RequestBody User user) {
        try {
            //此方法关键是要获取主键：DELETE FROM `t_user` WHERE `t_user`.`id` = ?
            userRepository.delete(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResult.success("删除成功", user);
    }

    @RequestMapping("/deleteUsers")
    public int deleteUsers(@RequestParam int age) {
        try {
            userRepository.deleteUsersById(age);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    @RequestMapping("/getUser2")
    public CommonResult getUser2(@RequestParam String age, @RequestParam String role) {
        List<User> user = personalRepository.getUserByRole(age, role);
        for(User user1:user){
            System.out.println(user1);
        }
        return CommonResult.success("查询成功", user);
    }

}
