package com.chs.boot.elastic.model;

import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Setter
@Document(indexName = "blog")
public class Blog {

    @Id
    private String id;
    private String title;
    private String content;
}

