package com.chs.boot.gerp.core.generate.model;

import lombok.Data;

@Data
public class TepGenControllerMethodInfoEO{
	private Long methodSeq;
	private Long packageNo;
	private String controllerPackageName;
	private String controllerClassName;
	private String methodAnnotationName;
	private String methodAccessor;
	private String methodReturnClassName;
	private String methodName;
	private String methodParamClassName;
	private String methodParamInstantName;
	private String methodContents;
	private String tableName;
	private String addDatasetParam;

}