package com.chs.boot.gerp.core.sync.model;

import java.util.LinkedHashMap;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoreSyncTableDataVO {
    private String tableName;
    private List<LinkedHashMap<String,Object>> dataListMap;
    private LinkedHashMap<String,Object> headerMap;
}
