package com.chs.boot.gerp.core.generate.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TepGenFileInfoVO {
	private Long fileSeq;
	private Long packageNo;
	private String packageName;
	private String fileName;
	private String fileContents;
}