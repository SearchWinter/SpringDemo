package com.upchina.spring.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by anjunli on  2021/11/9
 **/
@Controller
public class HelloControl {
    @Autowired
    private Person person;

    @ResponseBody
    @RequestMapping("/hello")
    public Person hello() {
        //页面上显示的返回值是：由该对象各个属性值组成的json对象
        // {"name":"tom","age":"10","addr":"wh"}
        return person;
    }
}
