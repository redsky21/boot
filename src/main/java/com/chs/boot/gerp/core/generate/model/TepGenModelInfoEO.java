package com.chs.boot.gerp.core.generate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private String datasetName;
    private String memberName;
    private String javaType;
    private String tsType;
    private String nullYn;
    private String lookupYn;
    private String lookupType;
    private String controllerMethodName;
    private Long controllerDatasetMethodSeq;
    private String apiInterfaceParam;
    private String utilApiGetMethodName;
    private String utilApiGetMethodContents;
    private String apiInterfaceRespData;
    private String utilGetFactorMethodName;
    private String utilGetObjectMethodName;
    private String controllerSaveMethodName;
    private Long controllerSaveMethodSeq;
    private String forReactYN;
}