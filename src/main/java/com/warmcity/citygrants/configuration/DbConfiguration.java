package com.warmcity.citygrants.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("com.warmcity.citygrants.repositories")
@PropertySource("classpath:datasource.properties")

public class DbConfiguration{
  
}
