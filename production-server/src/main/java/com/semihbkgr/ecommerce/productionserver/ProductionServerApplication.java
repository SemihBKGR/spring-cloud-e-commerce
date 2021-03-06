package com.semihbkgr.ecommerce.productionserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductionServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductionServerApplication.class, args);
    }

}
