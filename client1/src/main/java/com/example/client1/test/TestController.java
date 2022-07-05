package com.example.client1.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @Value("${author.name}")
    private String name;

    @Value("${author.birth}")
    private Integer birth;

    @GetMapping("/1")
    public Object test(){
        return String.format("author name :%s,birth : %d",name,birth);
    }
}
