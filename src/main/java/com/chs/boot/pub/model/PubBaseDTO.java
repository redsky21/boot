package com.chs.boot.pub.model;

import lombok.Data;

import java.util.List;

@Data
public class PubBaseDTO {
    private List<PubItemDTO> searchItemList;
    private List<PubItemDTO> searchButtonList;
    private List<PubItemDTO> gridButtonList;
    private List<PubGridDTO> gridItemList;

}
