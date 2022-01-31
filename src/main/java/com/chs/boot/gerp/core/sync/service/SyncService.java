package com.chs.boot.gerp.core.sync.service;

import com.chs.boot.gerp.b2b.sync.mapper.B2bSyncMapper;
import com.chs.boot.gerp.b2b.sync.model.TableVO;
import com.chs.boot.gerp.core.sync.mapper.CoreSyncMapper;
import com.chs.boot.gerp.core.sync.model.CoreSyncTableDataVO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
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
//                doDDL(tableName, (String) tableMap.get("Create Table").toString().replace("\"",""));
                if( tableVO.getTableType().equals("BASE TABLE") ){
                    getTableData(tableName);
                }

            });
        });
    }

    private void getTableData(String tableName) {
        List<LinkedHashMap<String, Object>> data = b2bSyncMapper.selectTableData(tableName);
        if (!data.isEmpty()) {
            coreSyncMapper.truncateTable(tableName);
            if(tableName.equals("cmn_time_zone")){
                for(LinkedHashMap<String, Object> changeRow :data){
                    Date existsData = (Date) changeRow.get("apply_year");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(existsData);
                    LocalDate date = LocalDate.of(cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH) + 1,
                        cal.get(Calendar.DAY_OF_MONTH));
                    changeRow.put("apply_year",date.getYear());
                };
            }
            coreSyncMapper.insertCoreDataTable(
                CoreSyncTableDataVO.builder().tableName(tableName).headerMap(data.get(0))
                    .dataListMap(data).build());

        }

    }

    private void doDDL(String tableName, String createStatement) {
        coreSyncMapper.dropTable(tableName);
        coreSyncMapper.createNewTable(createStatement);
    }

    private List<TableVO> getTableList() {
        return b2bSyncMapper.getTableSchema();
    }

}
