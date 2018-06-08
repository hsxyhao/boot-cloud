package com.hsxyhao.zuulproducer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanghao
 * create 2018-06-08 19:09
 **/
@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        log.info("request two name is "+name);
        try{
            Thread.sleep(10000000);
        }catch ( Exception e){
            log.error(" hello two error",e);
        }
        return "hello "+name+"，this is two message";
    }

}
