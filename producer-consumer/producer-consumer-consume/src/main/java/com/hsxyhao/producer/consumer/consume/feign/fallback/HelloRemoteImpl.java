package com.hsxyhao.producer.consumer.consume.feign.fallback;

import com.hsxyhao.producer.consumer.consume.feign.HelloRemote;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author wanghao
 * create 2018-06-06 16:28
 **/
@Service
public class HelloRemoteImpl implements HelloRemote {
    @Override
    public String hello() {
        return "Sorry,error message!";
    }
}
