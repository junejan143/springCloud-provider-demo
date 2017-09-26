package com.zychen.controller;

import com.zychen.dto.User;
import com.zychen.service.HelloService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 章源辰
 * @time: 2017/9/24 17:01
 * @describion:
 */
@RestController
public class RefactorHelloController implements HelloService{

    private  final Logger logger = Logger.getLogger(getClass());

    @Override
    public String hello(@RequestParam String name) {
        return "Hello "+ name;
    }

    @Override
    public User hello(@RequestHeader String name, @RequestHeader Integer age) {
        return new User(name, age);
    }

    @Override
    public String hello(@RequestBody User user) {
        return "Hello "+ user.getName()+ ", "+ user.getAge();
    }


}
