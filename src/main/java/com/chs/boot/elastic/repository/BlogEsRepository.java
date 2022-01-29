package com.chs.boot.elastic.repository;

import com.chs.boot.elastic.model.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogEsRepository extends ElasticsearchRepository<Blog, String> {

}

