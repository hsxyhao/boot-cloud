package com.hsxyhao.cloudconfigclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanghao
 * create 2018-06-07 10:19
 **/
@RefreshScope
@RestController
public class HelloConfigController {

    @Value("${cloud.config}")
    private String hello;

    @GetMapping("/hello/config")
    public String helloConfig() {
        return hello;
    }

}
