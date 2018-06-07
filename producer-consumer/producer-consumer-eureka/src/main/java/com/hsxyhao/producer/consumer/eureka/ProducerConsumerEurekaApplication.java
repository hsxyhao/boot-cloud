package com.hsxyhao.producer.consumer.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author hsxyhao
 */

@EnableEurekaServer
@SpringBootApplication
public class ProducerConsumerEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerConsumerEurekaApplication.class, args);
    }
}