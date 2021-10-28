package com.semihbkgr.ecommerce.imageserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ImageServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageServerApplication.class, args);
    }

}
