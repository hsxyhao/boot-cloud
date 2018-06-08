package com.hsxyhao.zuuleureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author hsxyhao
 */
@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class ZuulEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulEurekaApplication.class, args);
	}
}
