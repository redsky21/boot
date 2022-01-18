package com.chs.boot.gerp.b2b.generate.model;

import lombok.Data;
@Data
public class SchemaColumnConditionVO {
	private String tableCatalog;
	private String tableSchema;
	private String tableName;
	private String columnName;
	private Long ordinalPosition;
	private String columnDefault;
	private String isNullable;
	private String dataType;
	private Long characterMaximumLength;
	private Long characterOctetLength;
	private Long numericPrecision;
	private Long numericScale;
	private Long datetimePrecision;
	private String characterSetName;
	private String collationName;
	private String columnType;
	private String columnKey;
	private String extra;
	private String privileges;
	private String columnComment;
	private String isGenerated;
	private String generationExpression;
	private String packageName;
}