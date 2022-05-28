package com.chs.boot.gerp.core.generate.mapper;

import com.chs.boot.gerp.core.generate.model.ConvertDataTypeVO;
import com.chs.boot.gerp.core.generate.model.SequenceConditionVO;
import com.chs.boot.gerp.core.generate.model.SequenceVO;
import com.chs.boot.gerp.core.generate.model.TepGenControllerMethodInfoEO;
import com.chs.boot.gerp.core.generate.model.TepGenControllerUnitMethodEO;
import com.chs.boot.gerp.core.generate.model.TepGenFileInfoConditionVO;
import com.chs.boot.gerp.core.generate.model.TepGenFileInfoEO;
import com.chs.boot.gerp.core.generate.model.TepGenFileInfoVO;
import com.chs.boot.gerp.core.generate.model.TepGenMapperMethodInfoConditionVO;
import com.chs.boot.gerp.core.generate.model.TepGenMapperMethodInfoEO;
import com.chs.boot.gerp.core.generate.model.TepGenMapperMethodInfoVO;
import com.chs.boot.gerp.core.generate.model.TepGenMasterInfoEO;
import com.chs.boot.gerp.core.generate.model.TepGenModelInfoEO;
import com.chs.boot.gerp.core.generate.model.TepGenServiceMethodInfoEO;
import com.chs.boot.gerp.core.generate.model.TepGenTemplateEO;
import java.util.LinkedHashMap;
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
    List<SequenceVO> getNextVal(SequenceConditionVO sequenceConditionVO);
    List<TepGenServiceMethodInfoEO> retrieveTepGenServiceMethodInfo  (
        TepGenServiceMethodInfoEO TepGenServiceMethodInfoEO);
    void insertMultiTepGenServiceMethodInfo(List<TepGenServiceMethodInfoEO> tepGenServiceMethodInfoEOList);
    void updateMultiTepGenServiceMethodInfo(List<TepGenServiceMethodInfoEO> tepGenServiceMethodInfoEOList);
    void deleteMultiTepGenServiceMethodInfo(List<TepGenServiceMethodInfoEO> tepGenServiceMethodInfoEOList);
    TepGenServiceMethodInfoEO retrieveTepGenServiceMethodInfoByPk(TepGenServiceMethodInfoEO tepGenServiceMethodInfoEO);
    void insertTepGenControllerMethodInfoList(List<TepGenControllerMethodInfoEO> tepGenControllerMethodInfoEOList);
    void updateTepGenControllerMethodInfoList(List<TepGenControllerMethodInfoEO> tepGenControllerMethodInfoEOList);
    void deleteTepGenControllerMethodInfoList(List<TepGenControllerMethodInfoEO> tepGenControllerMethodInfoEOList);
    List<TepGenControllerMethodInfoEO> retrieveTepGenControllerMethodInfoListByPk(List<TepGenControllerMethodInfoEO> tepGenControllerMethodInfoEOList);
    List<TepGenControllerMethodInfoEO> retrieveTepGenControllerMethodInfoListAll(TepGenControllerMethodInfoEO tepGenControllerMethodInfoEOList);
    void insertTepGenMasterInfoList(List<TepGenMasterInfoEO> tepGenMasterInfoEOList);
    void updateTepGenMasterInfoList(List<TepGenMasterInfoEO> tepGenMasterInfoEOList);
    void insertOrUpdateTepGenMasterInfoList(List<TepGenMasterInfoEO> tepGenMasterInfoEOList);
    void deleteTepGenMasterInfoList(List<TepGenMasterInfoEO> tepGenMasterInfoEOList);
    List<TepGenMasterInfoEO> retrieveTepGenMasterInfoListByPk(List<TepGenMasterInfoEO> tepGenMasterInfoEOList);
    List<TepGenMasterInfoEO> retrieveTepGenMasterInfoListAll(TepGenMasterInfoEO tepGenMasterInfoEO);
    void insertTepGenControllerUnitMethodList(List<TepGenControllerUnitMethodEO> tepGenControllerUnitMethodEOList);
    void updateTepGenControllerUnitMethodList(List<TepGenControllerUnitMethodEO> tepGenControllerUnitMethodEOList);
    void deleteTepGenControllerUnitMethodList(List<TepGenControllerUnitMethodEO> tepGenControllerUnitMethodEOList);
    List<TepGenControllerUnitMethodEO> retrieveTepGenControllerUnitMethodListByPk(List<TepGenControllerUnitMethodEO> tepGenControllerUnitMethodEOList);
    List<TepGenControllerUnitMethodEO> retrieveTepGenControllerUnitMethodListAll(TepGenControllerUnitMethodEO tepGenControllerUnitMethodEO);
    void clearMetaData(Long packageNo);
    void insertTepGenModelInfoList(List<TepGenModelInfoEO> tepGenModelInfoEOList);
    void updateTepGenModelInfoList(List<TepGenModelInfoEO> tepGenModelInfoEOList);
    void deleteTepGenModelInfoList(List<TepGenModelInfoEO> tepGenModelInfoEOList);
    List<TepGenModelInfoEO> retrieveTepGenModelInfoListByPk(List<TepGenModelInfoEO> tepGenModelInfoEOList);
    List<TepGenModelInfoEO> retrieveTepGenModelInfoListAll(TepGenModelInfoEO tepGenModelInfoEO);
}
