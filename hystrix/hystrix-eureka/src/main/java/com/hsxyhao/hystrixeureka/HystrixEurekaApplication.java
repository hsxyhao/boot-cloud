package com.hsxyhao.hystrixeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author hsxyhao
 */
@EnableEurekaServer
@SpringBootApplication
public class HystrixEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixEurekaApplication.class, args);
	}
}
