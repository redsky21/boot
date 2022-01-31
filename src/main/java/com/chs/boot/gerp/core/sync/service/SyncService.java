package com.chs.boot.gerp.core.sync.service;

import com.chs.boot.gerp.b2b.sync.mapper.B2bSyncMapper;
import com.chs.boot.gerp.b2b.sync.model.TableVO;
import com.chs.boot.gerp.core.sync.mapper.CoreSyncMapper;
import com.chs.boot.gerp.core.sync.model.CoreSyncTableDataVO;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SyncService {

    private final B2bSyncMapper b2bSyncMapper;
    private final CoreSyncMapper coreSyncMapper;

    public void doSync() {
        List<TableVO> tableList = getTableList();
        tableList.forEach(tableVO -> {
            Map<String, Map> createTableMap = b2bSyncMapper.showCreateTable(tableVO.getTableName());
            createTableMap.forEach((tableName, tableMap) -> {
//                tableMap.forEach((innerTableName,createStatement)->{
//                    doDDL((String) innerTableName, String.valueOf(createStatement));
//                });
//                doDDL(tableName, (String) tableMap.get("Create Table"));
                getTableData(tableName);
            });
        });
    }

    private void getTableData(String tableName) {
        List<LinkedHashMap<String, Object>> data = b2bSyncMapper.selectTableData(tableName);
        if (!data.isEmpty()) {
            coreSyncMapper.truncateTable(tableName);
            if(tableName.equals("tep_cmn_system")){
                String a ="";
            }
            coreSyncMapper.insertCoreDataTable(
                CoreSyncTableDataVO.builder().tableName(tableName).headerMap(data.get(0))
                    .dataListMap(data).build());

        }

    }

    private void doDDL(String tableName, String createStatement) {
        coreSyncMapper.createNewTable(createStatement);
    }

    private List<TableVO> getTableList() {
        return b2bSyncMapper.getTableSchema();
    }

}
