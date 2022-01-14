package com.chs.boot.gerp.core.generate.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TepGenMapperMethodInfoVO {
	private Long methodSeq;
	private Long packageNo;
	private String mapperPackageName;
	private String mapperXmlName;
	private String mapperClassName;
	private String methodAnnotationName;
	private String methodReturnClassName;
	private String methodName;
	private String methodParamClassName;
	private String methodParamInstantName;
	private String xmlMethodType;
	private String sqlStmt;
	private String tableName;
}