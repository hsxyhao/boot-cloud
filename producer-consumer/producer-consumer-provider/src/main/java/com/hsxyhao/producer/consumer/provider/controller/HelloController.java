package com.hsxyhao.producer.consumer.provider.controller;

import com.hsxyhao.producer.consumer.provider.entity.Hello;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanghao
 * create 2018-06-06 15:11
 **/
@Slf4j
@RestController
public class HelloController {

    @Value("${hello.msg}")
    private String msg;

    @GetMapping("/hello")
    public String hello() {
        log.info("[ *] request provider com.hsxyhao.zuul.controller");
        return new Hello(msg).getMsg();
    }

}
