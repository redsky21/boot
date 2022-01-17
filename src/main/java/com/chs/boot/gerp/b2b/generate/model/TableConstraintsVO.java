package com.chs.boot.gerp.b2b.generate.model;

import lombok.Data;

@Data
public class TableConstraintsVO {

    private String tableName;
    private String constraintType;
    private String constraintName;
    private String columnName;
}
