package com.jianming.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Hello;

/**
 * @author jianming
 * @create 2020-03-17-15:20
 */
@RestController
public class HelloController {

    /**
     * 使用dubbo的@Reference来从注册中心调用远程的服务
     */
    @Reference
    com.jianming.consumer.service.HelloService helloService;


    @RequestMapping("/hello")
    public Hello hello() {
        return helloService.helloWorld("i am consumer!!");
    }

}
