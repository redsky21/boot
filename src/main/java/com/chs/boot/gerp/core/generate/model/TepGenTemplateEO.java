package com.chs.boot.gerp.core.generate.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TepGenTemplateEO {
    private Long templateSeq;
    private String templateType;
    private String templateSqlStmt;
}
