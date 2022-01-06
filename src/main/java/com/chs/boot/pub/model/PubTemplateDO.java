package com.chs.boot.pub.model;

import lombok.Data;

@Data
public class PubTemplateDO {
    private String tmpCode;
    private String tmpType;
    private Long orderSeq;
    private String htmlText;
}
