package com.warmcity.citygrants.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.warmcity.citygrants")
public class CitygrantsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitygrantsApplication.class, args);
	}
}
