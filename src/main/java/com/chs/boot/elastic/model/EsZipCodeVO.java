package com.chs.boot.elastic.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "es_zipcode", createIndex = true)
@Setting(settingPath = "/elasticsearch/settings/settings.json")
@Mapping(mappingPath = "/elasticsearch/mappings/mappings.json")
public class EsZipCodeVO {
    @Id
    private String zipSeq;
    private String corporationCode;
    private Long zipcdId;
    private String zipcd;
    private String zipcdInfo;
    private String city;
    private String gungu;
    private String bunji;
    private Long rgstDate;
    private String rgstUser;
    private Long updtDate;
    private String updtUser;
    private String useYn;
    private Long localRgstDate;
    private Long localUpdtDate;
    private String postType;
    private String roadNm;
    private String basementFg;
    private String dongNm;
    private String expireDt;
    private String insertDt;
    private String dong;
    private String buildingNm;
    private String address_name;
}
