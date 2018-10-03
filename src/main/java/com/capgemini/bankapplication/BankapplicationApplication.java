package com.capgemini.bankapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages= {"com.capgemini.bankapplication"})


public class BankapplicationApplication {
	
	public static void main(String[] args) {
		

		SpringApplication.run(BankapplicationApplication.class, args);
	}
}
 
