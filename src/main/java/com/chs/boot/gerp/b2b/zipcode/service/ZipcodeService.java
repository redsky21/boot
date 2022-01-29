package com.chs.boot.gerp.b2b.zipcode.service;
import com.chs.boot.gerp.b2b.zipcode.mapper.ZipcodeMapper;
import com.chs.boot.gerp.b2b.zipcode.model.ZipCodeConditionVO;
import com.chs.boot.gerp.b2b.zipcode.model.ZipCodeVO;
import com.chs.boot.gerp.core.zipcode.mapper.CoreZipcodeMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ZipcodeService {

    private final ZipcodeMapper zipcodeMapper;
    private final CoreZipcodeMapper coreZipcodeMapper;
    public void doTransfer(){
        retrieveDistinctZipCode().forEach(
            (distinctZipVO)->{
                List<ZipCodeVO> atomResult = zipcodeMapper.retrieveZipCode(distinctZipVO);
                String breakPoint = "";
                coreZipcodeMapper.insertBofZipCodeList(atomResult);
            }
        );

    }
    public List<ZipCodeVO> retrieveDistinctZipCode() {
        List<ZipCodeVO> result = zipcodeMapper.retrieveDistinctZipCode(
            ZipCodeConditionVO.builder().build());
        return result;
    }

    public List<ZipCodeVO> retrieveZipCode(ZipCodeVO conditionVO) {
        List<ZipCodeVO> result = zipcodeMapper.retrieveZipCode(conditionVO);
        return result;
    }

}
