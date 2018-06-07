package com.hsxyhao.producer.consumer.consume.feign;

import com.hsxyhao.producer.consumer.consume.feign.fallback.HelloRemoteImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wanghao
 * create 2018-06-06 15:32
 **/
@FeignClient(name = "provider-consumer-provider",fallback = HelloRemoteImpl.class)
public interface HelloRemote {

    /**
     * hello
     * @return message
     */
    @GetMapping("/hello")
    String hello();

}
