package com.chs.boot.gerp.core.generate.mapper;

import com.chs.boot.gerp.b2b.generate.model.SchemaColumnConditionVO;
import com.chs.boot.gerp.core.generate.model.ConvertDataTypeVO;
import com.chs.boot.gerp.core.generate.model.TepGenTemplateEO;
import com.chs.boot.pub.model.PubTemplateDO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoreGenerateMapper {

    List<ConvertDataTypeVO> getConvertDataType();
    List<TepGenTemplateEO> retrieveTepGenTemplate(TepGenTemplateEO tepGenTemplateEO);

}
