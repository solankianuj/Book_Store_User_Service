package com.example.bookstoreuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication

public class BookStoreUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStoreUserServiceApplication.class, args);
    }

}
