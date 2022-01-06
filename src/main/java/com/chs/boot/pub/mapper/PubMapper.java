package com.chs.boot.pub.mapper;

import com.chs.boot.pub.model.PubTemplateDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PubMapper {
    List<PubTemplateDO> retrieveTemplateSource(PubTemplateDO pubTemplateDO);
}
