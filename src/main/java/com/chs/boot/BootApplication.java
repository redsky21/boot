package com.chs.boot;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@RestController

public class BootApplication {

    public static void main(String[] args) {
//        SpringApplication.run(BootApplication.class, args);
        SpringApplication app = new SpringApplication(BootApplication.class);

        app.addListeners(new ApplicationPidFileWriter());   // pid 를 작성하는 역할을 하는 클래스 선언
        app.run(args);

    }

    @GetMapping("/hello")
    public String hello() {
        return "goodstart3";
    }

    @PostMapping("/hello2")
//    @CrossOrigin("*")
    public LocalDateTime hello2() {
        return LocalDateTime.now();
    }


}
