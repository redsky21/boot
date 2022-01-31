package com.chs.boot.gerp.core.sync.service;

import com.chs.boot.gerp.b2b.sync.mapper.B2bSyncMapper;
import com.chs.boot.gerp.b2b.sync.model.TableVO;
import com.chs.boot.gerp.core.sync.mapper.CoreSyncMapper;
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
            Map<String,Map> createTableMap = b2bSyncMapper.showCreateTable(tableVO.getTableName());
            createTableMap.forEach((tableName,tableMap)->{
//                tableMap.forEach((innerTableName,createStatement)->{
//                    doDDL((String) innerTableName, String.valueOf(createStatement));
//                });
                doDDL(tableName, (String) tableMap.get("Create Table"));
            });
        });
    }
    private void doDDL(String tableName,String createStatement){
        coreSyncMapper.createNewTable(createStatement);
    }

    private List<TableVO> getTableList() {
        return b2bSyncMapper.getTableSchema();
           }

}
