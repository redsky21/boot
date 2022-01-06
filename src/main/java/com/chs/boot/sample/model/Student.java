package com.chs.boot.sample.model;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Student {

    private Integer id;
    private String name;
    private String code;
    private LocalDateTime saveDate = LocalDateTime.now();

    public Student(String name, String code) {
        this.name = name;
        this.code = code;
    }
}