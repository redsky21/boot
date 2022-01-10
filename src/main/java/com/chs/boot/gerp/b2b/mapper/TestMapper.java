package com.chs.boot.gerp.b2b.mapper;

import com.chs.boot.pub.model.PubTemplateDO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {

    String getDb2Dual() throws Exception;

}
