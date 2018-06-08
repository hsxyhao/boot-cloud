package com.hsxyhao.zuulsimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author hsxyhao
 */
@EnableZuulProxy
@SpringBootApplication
public class ZuulSimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulSimpleApplication.class, args);
	}
}
