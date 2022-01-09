package com.chs.boot.b2b.generate.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface B2bGenerateMapper {

    String getDb2Dual() throws Exception;

}
