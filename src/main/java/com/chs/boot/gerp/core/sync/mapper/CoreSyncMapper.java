package com.chs.boot.gerp.core.sync.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoreSyncMapper {
    void dropTable(String value);
    void createNewTable(String value);
    void truncateTable(String tableName);
}
