package com.example.client2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class Client2Application {

    public static void main(String[] args) {
        SpringApplication.run(Client2Application.class, args);
    }

}
