package com.jianming.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import pojo.Hello;

/**
 * @Service 需要是dubbo的service
 * 再用@Component把service加入到容器中
 *
 * @author jianming
 * @create 2020-03-10-18:22
 */
@Service
@Component
public class HelloServiceImpl implements com.jianming.consumer.service.HelloService {
    @Override
    public Hello helloWorld(String hello) {
        System.out.println("hello = [" + hello + "]");
        return new Hello("hello world",new String[]{"dubbo","hello","RPC"});
    }
}
