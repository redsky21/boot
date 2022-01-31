package com.chs.boot.gerp.core.sync.mapper;

import com.chs.boot.gerp.core.sync.model.CoreSyncTableDataVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface CoreSyncMapper {
    void dropTable(String value);
    void createNewTable(String value);
    void truncateTable(String tableName);
    @Transactional
    void insertCoreDataTable(CoreSyncTableDataVO coreSyncTableDataVO);
}
