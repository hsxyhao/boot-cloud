package com.hsxyhao.zuulproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hsxyhao
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ZuulProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulProducerApplication.class, args);
	}
}
