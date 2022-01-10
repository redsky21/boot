package com.chs.boot.gerp.b2b.generate.mapper;

import com.chs.boot.gerp.b2b.generate.model.SchemaColumnConditionVO;
import com.chs.boot.gerp.b2b.generate.model.SchemaColumnVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface B2bGenerateMapper {

    List<SchemaColumnVO> retrieveColumnSchema(SchemaColumnConditionVO schemaColumnConditionVO);

}
