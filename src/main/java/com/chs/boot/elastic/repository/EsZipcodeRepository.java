package com.chs.boot.elastic.repository;

import com.chs.boot.elastic.model.Blog;
import com.chs.boot.elastic.model.EsZipCodeVO;
import java.util.List;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface EsZipcodeRepository extends ElasticsearchRepository<EsZipCodeVO, String> {
    @Query("{\"match\":{\"address_name.analyzed\":{\"query\":\"?0\",\"operator\":\"and\"}}}")
    List<EsZipCodeVO> findAllByAddressMatches(String addr, Pageable pageable);

}
