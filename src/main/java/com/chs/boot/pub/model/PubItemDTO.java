package com.chs.boot.pub.model;

import lombok.Data;

@Data
public class PubItemDTO {
    private Long sortSeq;
    private String label;
    private String name;
    private String type;
    private String compClass;
    private String compId;
}
