package com.example.client4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Client4Application {

    @Value("${author.name}")
    private String name;

    @Value("${author.birth}")
    private Integer birth;

    @GetMapping("/")
    public String home(){
        return "welcome to consul.consul can be used insteadof eureka.bus";
    }

    @GetMapping("/config")
    public String testConfig(){
        return String.format("author name : %s \n,birth: %d",name,birth);
    }

    public static void main(String[] args) {
        SpringApplication.run(Client4Application.class, args);
    }

}
