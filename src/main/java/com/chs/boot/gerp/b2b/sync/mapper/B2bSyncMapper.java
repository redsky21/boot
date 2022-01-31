package com.chs.boot.gerp.b2b.sync.mapper;

import com.chs.boot.gerp.b2b.sync.model.TableVO;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface B2bSyncMapper {
    List<TableVO> getTableSchema();

    @MapKey("Table")
    Map<String,Map> showCreateTable(String value);

    List<LinkedHashMap<String, Object>> selectTableData(String tableName);
}
