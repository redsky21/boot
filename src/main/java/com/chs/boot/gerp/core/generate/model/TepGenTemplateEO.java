package com.chs.boot.gerp.core.generate.model;

import lombok.Data;

@Data
public class TepGenTemplateEO {
    private Long templateSeq;
    private String templateType;
    private String templateSqlStmt;
}
