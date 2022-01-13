package com.chs.boot.gerp.core.generate.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoreColumnVO {
    String dataType;
    String columnName;
}
