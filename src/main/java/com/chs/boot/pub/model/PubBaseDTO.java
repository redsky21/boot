package com.chs.boot.pub.model;

import java.util.LinkedHashMap;
import lombok.Data;

import java.util.List;

@Data
public class PubBaseDTO {
    private List<PubItemDTO> searchItemList;
    private List<PubItemDTO> searchButtonList;
    private List<PubItemDTO> gridButtonList;
    private List<PubGridDTO> gridItemList;
    private List<LinkedHashMap<String,Object>> testDataList;

}
