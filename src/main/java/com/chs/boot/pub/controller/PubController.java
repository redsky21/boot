package com.chs.boot.pub.controller;

import com.chs.boot.pub.model.PubBaseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PubController {
    @PostMapping(path = "/generateHtml")
    public String generateHtml(@RequestBody PubBaseDTO pubBaseDTO){
        return "go";
    }
}
