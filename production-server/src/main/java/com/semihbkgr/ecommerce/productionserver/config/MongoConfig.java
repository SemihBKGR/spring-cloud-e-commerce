package com.semihbkgr.ecommerce.productionserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "com.semihbkgr.ecommerce.productionserver.repository")
public class MongoConfig {

}
