package com.example.client3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Client3Application {

    public static void main(String[] args) {
        SpringApplication.run(Client3Application.class, args);
    }

}
