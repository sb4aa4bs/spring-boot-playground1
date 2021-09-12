package com.apeiron.abs2.springbootplayground1.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class }) // If wanted to BYPASS SECURITY, but not working
@SpringBootApplication
@ComponentScan(basePackages = {"com.apeiron.abs2.springbootplayground1"})
public class SpringBootPlayground1Application {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootPlayground1Application.class, args);
	}
}
