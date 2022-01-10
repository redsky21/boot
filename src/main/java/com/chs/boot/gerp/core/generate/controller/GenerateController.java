package com.chs.boot.gerp.core.generate.controller;

import com.chs.boot.gerp.core.generate.service.GenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateController {
    @Autowired
    GenerateService generateService;

    @PostMapping("/gerp/gen/hello")
    public void hello(String good) {


    }
}
