package com.chs.boot;

import com.chs.boot.elastic.model.Blog;
import com.chs.boot.elastic.model.BlogEsRepository;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    BlogEsRepository blogEsRepository;

    @Test
    void test() {
        Blog blog = new Blog();
        blog.setId("1");
        blog.setContent("내용입니다.");
        blog.setTitle("제목입니다.");
        blogEsRepository.save(blog);
    }


}
