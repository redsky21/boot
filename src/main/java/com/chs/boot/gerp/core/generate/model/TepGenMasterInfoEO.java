package com.chs.boot.gerp.core.generate.model;

import lombok.Data;

@Data
public class TepGenMasterInfoEO  {
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

}