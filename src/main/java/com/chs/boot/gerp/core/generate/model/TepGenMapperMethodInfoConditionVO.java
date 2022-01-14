package com.chs.boot.gerp.core.generate.model;

import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;
import java.time.LocalDateTime;
@Data
@Builder
public class TepGenMapperMethodInfoConditionVO {
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