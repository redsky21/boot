package com.chs.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }
    @GetMapping("/hello")
    public String hello(){
        return "goodstart";
    }

    @PostMapping("/hello2")
//    @CrossOrigin("*")
    public String hello2(){
        return "goodstart2";
    }

}
