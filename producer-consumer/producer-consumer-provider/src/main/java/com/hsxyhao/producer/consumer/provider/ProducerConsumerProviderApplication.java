package com.hsxyhao.producer.consumer.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hsxyhao
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ProducerConsumerProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerConsumerProviderApplication.class, args);
    }
}
