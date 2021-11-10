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
        return person;
    }
}
