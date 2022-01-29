package com.chs.boot.gerp.core.zipcode.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BofZipCodeEO {
	private String zipSeq;
	private String corporationCode;
	private Long zipcdId;
	private String zipcd;
	private String zipcdInfo;
	private String city;
	private String gungu;
	private String dong;
	private String bunji;
	private LocalDateTime rgstDate;
	private String rgstUser;
	private LocalDateTime updtDate;
	private String updtUser;
	private String useYn;
	private LocalDateTime localRgstDate;
	private LocalDateTime localUpdtDate;
	private String postType;
	private String roadNm;
	private String basementFg;
	private String dongNm;
	private String buildingNm;
	private String expireDt;
	private String insertDt;

}