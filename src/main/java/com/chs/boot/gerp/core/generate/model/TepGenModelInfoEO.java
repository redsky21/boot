package com.chs.boot.gerp.core.generate.model;

import lombok.Builder;
import lombok.Data;

@Data
public class TepGenModelInfoEO {

    private String rowKey;
    private Long modelSeq;
    private Long packageNo;
    private String packageName;
    private String columnName;
    private String columnType;
    private String voName;
    private String interfaceName;
    private String memberName;
    private String javaType;
    private String tsType;
    private String nullYn;
    private String lookupYn;
    private String datasetName;
    private String lookupType;
    private String controllerMethodName;
    private Long controllerDatasetMethodSeq;
    private String apiInterfaceParam;
    private String utilApiGetMethodName;
    private String utilApiGetMethodContents;
    private String apiInterfaceRespData;
}