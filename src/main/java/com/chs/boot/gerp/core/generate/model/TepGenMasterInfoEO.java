package com.chs.boot.gerp.core.generate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TepGenMasterInfoEO {
	private String rowKey;
	private Long masterSeq;
	private Long packageNo;
	private String packageName;
	private String methodType;
	private String tableName;
	private String methodName;
	private String sqlStmt;
	private String voName;
	private String controllerYn;
	private String serviceYn;
	private String initYn;
	private Long initOrderSeq;
	private String lookupType;
	private String controllerMethodName;
	private Long controllerDatasetMethodSeq;
	private String controllerSaveMethodName;
	private Long controllerSaveMethodSeq;

}