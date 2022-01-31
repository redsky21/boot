package com.chs.boot.gerp.b2b.sync.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TableVO  {

	private String tableCatalog;
	private String tableSchema;
	private String tableName;
	private String tableType;
	private String engine;
	private Long version;
	private String rowFormat;
	private Long tableRows;
	private Long avgRowLength;
	private Long dataLength;
	private Long maxDataLength;
	private Long indexLength;
	private Long dataFree;
	private LocalDateTime createTime;
	private String tableCollation;
	private String createOptions;
	private String tableComment;
	private Long maxIndexLength;
	private String temporary;
	private Long autoIncrement;
	private LocalDateTime updateTime;
}