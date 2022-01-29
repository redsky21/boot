package com.chs.boot.gerp.core.zipcode.mapper;

import com.chs.boot.gerp.b2b.zipcode.model.ZipCodeVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface CoreZipcodeMapper {

	@Transactional
	void insertBofZipCodeList(List<ZipCodeVO> ZipCodeVOList);
	void updateBofZipCodeList(List<ZipCodeVO> ZipCodeVOList); 
	void deleteBofZipCodeList(List<ZipCodeVO> ZipCodeVOList); 
	List<ZipCodeVO> retrieveBofZipCodeListByPk(List<ZipCodeVO> ZipCodeVOList); 
	List<ZipCodeVO> retrieveBofZipCodeListAll(ZipCodeVO ZipCodeVO); 
    
}
