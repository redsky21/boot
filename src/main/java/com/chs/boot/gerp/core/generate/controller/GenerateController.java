package com.chs.boot.gerp.core.generate.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateController {


    @PostMapping("/gerp/gen/hello")
    public String hello(String good){
        return "good night";
    }
}
