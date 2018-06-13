package com.hsxyhao.producer.consumer.consume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author wanghao
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class ProducerConsumerConsumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerConsumerConsumeApplication.class, args);
    }
}
