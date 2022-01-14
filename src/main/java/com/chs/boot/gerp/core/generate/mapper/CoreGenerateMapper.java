package com.chs.boot.gerp.core.generate.mapper;

import com.chs.boot.gerp.b2b.generate.model.SchemaColumnConditionVO;
import com.chs.boot.gerp.core.generate.model.ConvertDataTypeVO;
import com.chs.boot.gerp.core.generate.model.TepGenFileInfoConditionVO;
import com.chs.boot.gerp.core.generate.model.TepGenFileInfoEO;
import com.chs.boot.gerp.core.generate.model.TepGenFileInfoVO;
import com.chs.boot.gerp.core.generate.model.TepGenMapperMethodInfoConditionVO;
import com.chs.boot.gerp.core.generate.model.TepGenMapperMethodInfoEO;
import com.chs.boot.gerp.core.generate.model.TepGenMapperMethodInfoVO;
import com.chs.boot.gerp.core.generate.model.TepGenTemplateEO;
import com.chs.boot.pub.model.PubTemplateDO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoreGenerateMapper {

    List<ConvertDataTypeVO> getConvertDataType();
    List<TepGenTemplateEO> retrieveTepGenTemplate(TepGenTemplateEO tepGenTemplateEO);
    List<TepGenFileInfoVO> retrieveTepGenFileInfo  (
        TepGenFileInfoConditionVO tepGenFileInfoConditionVO);
    void insertMulti(List<TepGenFileInfoEO> tepGenFileInfoEOList);
    void updateMulti(List<TepGenFileInfoEO> tepGenFileInfoEOList);
    void deleteMulti(List<TepGenFileInfoEO> tepGenFileInfoEOList);
    TepGenFileInfoEO retrieveTepGenFileInfoByPk(TepGenFileInfoEO tepGenFileInfoEO);
    List<TepGenMapperMethodInfoVO> retrieveTepGenMapperMethodInfo  (
        TepGenMapperMethodInfoConditionVO tepGenMapperMethodInfoConditionVO);
    void insertMultiTepGenMapperMethodInfo(List<TepGenMapperMethodInfoEO> tepGenMapperMethodInfoEOList);
    void updateMultiTepGenMapperMethodInfo(List<TepGenMapperMethodInfoEO> tepGenMapperMethodInfoEOList);
    void deleteMultiTepGenMapperMethodInfo(List<TepGenMapperMethodInfoEO> tepGenMapperMethodInfoEOList);
    TepGenMapperMethodInfoEO retrieveTepGenMapperMethodInfoByPk(TepGenMapperMethodInfoEO tepGenMapperMethodInfoEO);
}
