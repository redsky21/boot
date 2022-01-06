package com.chs.boot.pub.controller;

import com.chs.boot.pub.model.PubBaseDTO;
import com.chs.boot.pub.service.PubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PubController {
    @Autowired
    PubService pubService;

    @PostMapping(path = "/generateHtml")
    public String generateHtml(@RequestBody PubBaseDTO pubBaseDTO) {
        pubService.getHtmlString(pubBaseDTO
        );
        return "go";

    }
}
