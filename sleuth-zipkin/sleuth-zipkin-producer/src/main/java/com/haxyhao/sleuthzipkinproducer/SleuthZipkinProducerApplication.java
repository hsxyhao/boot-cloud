package com.haxyhao.sleuthzipkinproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hsxyhao
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SleuthZipkinProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SleuthZipkinProducerApplication.class, args);
	}
}
