package com.hsxyhao.sleuthzipkinzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author hsxyhao
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class SleuthZipkinZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SleuthZipkinZuulApplication.class, args);
	}
}
