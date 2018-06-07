package com.hsxyhao.producer.consumer.consume.controller;

import com.hsxyhao.producer.consumer.consume.feign.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanghao
 * create 2018-06-06 16:36
 **/
@RestController
public class MessageController {

    @Autowired
    private HelloRemote helloRemote;

    @GetMapping("/message")
    public String message() {
        return helloRemote.hello();
    }

}
