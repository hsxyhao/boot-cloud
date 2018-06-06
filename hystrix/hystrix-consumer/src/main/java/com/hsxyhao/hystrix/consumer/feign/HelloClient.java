package com.hsxyhao.hystrix.consumer.feign;

import com.hsxyhao.hystrix.consumer.feign.fallback.HelloServiceImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wanghao
 * create 2018-06-06 18:00
 **/
@FeignClient(name = "hystrix-producer", fallback = HelloServiceImpl.class)
public interface HelloClient {
    /**
     * hello
     * @return message
     */
    @GetMapping("/hello")
    String hello();
}
