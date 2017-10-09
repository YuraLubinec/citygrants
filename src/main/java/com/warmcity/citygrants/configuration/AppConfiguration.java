package com.warmcity.citygrants.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AppConfiguration extends WebMvcConfigurerAdapter {
  
//  @Autowired
//  private Environment environment;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
//    for developing only
    registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
//    for production
//    registry.addMapping("/**").allowedMethods("GET", "POST","PUT", "DELETE", "OPTIONS")
//        .allowedOrigins(environment.getRequiredProperty("allow.origin.local"),environment.getRequiredProperty("allow.origin.global"));
  }

}
