package com.walmart.testwalmartbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.walmart")
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = "com.walmart.repository")
public class TestWalmartBackendApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(TestWalmartBackendApplication.class, args);
	}

	
}
