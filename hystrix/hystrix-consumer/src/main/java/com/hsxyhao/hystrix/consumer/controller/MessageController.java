package com.hsxyhao.hystrix.consumer.controller;

import com.hsxyhao.hystrix.consumer.feign.HelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanghao
 * create 2018-06-06 18:05
 **/
@RestController
public class MessageController {

    @Autowired
    private HelloClient helloClient;

    @GetMapping("/message")
    public String message() {
        return helloClient.hello();
    }

}
