package com.hsxyhao.hystrix.consumer.feign.fallback;

import com.hsxyhao.hystrix.consumer.feign.HelloClient;
import org.springframework.stereotype.Service;

/**
 * @author wanghao
 * create 2018-06-06 18:01
 **/
@Service
public class HelloServiceImpl implements HelloClient {
    @Override
    public String hello() {
        return "hystrix,hello";
    }
}
