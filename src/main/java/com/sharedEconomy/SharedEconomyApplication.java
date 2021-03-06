package com.sharedEconomy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.sharedEconomy.Security.SecurityConfig;
import com.sharedEconomy.controllers.UserController;

@SpringBootApplication
@ComponentScan(basePackageClasses = UserController.class)
@ComponentScan(basePackageClasses = SecurityConfig.class)
public class SharedEconomyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharedEconomyApplication.class, args);
	}

}
