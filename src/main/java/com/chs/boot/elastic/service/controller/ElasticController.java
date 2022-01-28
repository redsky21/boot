package com.chs.boot.elastic.service.controller;

import com.chs.boot.elastic.model.BlogEsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ElasticController {
    private final BlogEsRepository blogEsRepository;

    @GetMapping("/noHello")
    public void saveEla() {
//        blogEsRepository.searchSimilar()
    }
}
