package com.warmcity.citygrants.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.warmcity.citygrants", exclude = SecurityAutoConfiguration.class)
public class CitygrantsApplication {

  public static void main(String[] args) {

    SpringApplication.run(CitygrantsApplication.class, args);
  }
}
