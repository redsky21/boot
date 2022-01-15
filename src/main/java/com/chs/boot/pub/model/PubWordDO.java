package com.chs.boot.pub.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PubWordDO {
    private Long wordSeq;
    private String label;
    private String fieldName;
}
