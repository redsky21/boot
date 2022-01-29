package com.chs.boot.gerp.b2b.zipcode.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;


import com.chs.boot.gerp.b2b.zipcode.model.ZipCodeConditionVO;
import com.chs.boot.gerp.b2b.zipcode.model.ZipCodeVO;

@Mapper
public interface ZipcodeMapper {


	List<ZipCodeVO> retrieveZipCode(ZipCodeVO zipCodeConditionVO);
	List<ZipCodeVO> retrieveDistinctZipCode(ZipCodeConditionVO zipCodeConditionVO);
}
