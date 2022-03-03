package com.blabla.upload.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UploadApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(UploadApiApplication.class, args);
    }
}
