package com.zychen.controller;

import com.zychen.domain.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * @author 章源辰
 * @time: 2017/9/24 17:01
 * @describion:
 */
@RestController
public class HelloController {

    private  final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() throws Exception{
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();

        int sleepTime = new Random().nextInt(3000);
        logger.info("sleepTime:" + sleepTime);
        Thread.sleep(sleepTime);

        logger.info("/hello, host:"+ instance.getHost() + ", service_id:" + instance.getServiceId());
        return "hello word";
    }

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello(@RequestParam String name){
        logger.info("hello1被调用");
        return "Hello "+ name;
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public User hello(@RequestHeader String name, @RequestHeader Integer age){
        logger.info("hello2被调用");
        return new User(name, age);
    }

    @RequestMapping(value = "/hello3", method = RequestMethod.POST)
    public String hello(@RequestBody User user){
        logger.info("hello3被调用");
        return "Hello "+ user.getName()+ ", "+ user.getAge();
    }
}
