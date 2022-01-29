package com.chs.boot.gerp.b2b.zipcode.service;

import static com.chs.boot.common.util.MyBatisUtil.isNotEmpty;

import com.chs.boot.elastic.model.EsZipCodeVO;
import com.chs.boot.elastic.repository.EsZipcodeRepository;
import com.chs.boot.gerp.b2b.zipcode.mapper.ZipcodeMapper;
import com.chs.boot.gerp.b2b.zipcode.model.ZipCodeConditionVO;
import com.chs.boot.gerp.b2b.zipcode.model.ZipCodeVO;
import com.chs.boot.gerp.core.zipcode.mapper.CoreZipcodeMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ZipcodeService {

    private final ZipcodeMapper zipcodeMapper;
    private final CoreZipcodeMapper coreZipcodeMapper;
    private final EsZipcodeRepository esZipcodeRepository;
    private final ObjectMapper objectMapper;


//    @Query("{\"match\":{\"address\":{\"query\":\"?0\",\"operator\":\"and\"}}}")
//    List<EsAddressVO> findAllByAddressMatches(String addr, Pageable pageable);
    public void doSearch(){
        Pageable paging = PageRequest.of(0, 10);
        List<EsZipCodeVO>  result  = esZipcodeRepository.findAllByAddressMatches("8단지 강동",paging );
        String breakPoint="";

    }
    public void doTransfer() {
//        retrieveDistinctZipCode().forEach(
//            (distinctZipVO)->{
//                List<ZipCodeVO> atomResult = zipcodeMapper.retrieveZipCode(distinctZipVO);
//                String breakPoint = "";
//                coreZipcodeMapper.insertBofZipCodeList(atomResult);
//            }
//        );

        retrieveDistinctZipCode().forEach(
            (distinctZipVO) -> {
                List<EsZipCodeVO> esZipCodeVOList = new ArrayList<>();
                zipcodeMapper.retrieveZipCode(distinctZipVO)
                    .forEach((zipCodeVO) -> {

                        EsZipCodeVO esZipCodeVO = objectMapper.convertValue(zipCodeVO,
                            EsZipCodeVO.class);
                        StringBuilder fullAddr = new StringBuilder("");

                        if (isNotEmpty(esZipCodeVO.getCity())) {
                            fullAddr.append(esZipCodeVO.getCity());
                        }
                        if (isNotEmpty(esZipCodeVO.getGungu())) {
                            fullAddr.append(" ").append(esZipCodeVO.getGungu());
                        }
                        if (isNotEmpty(esZipCodeVO.getBunji())) {
                            fullAddr.append(" ").append(esZipCodeVO.getBunji());
                        }
                        if (isNotEmpty(esZipCodeVO.getRoadNm())) {
                            fullAddr.append(" ").append(esZipCodeVO.getRoadNm());
                        }
                        if (isNotEmpty(esZipCodeVO.getDongNm())) {
                            fullAddr.append(" ").append(esZipCodeVO.getDongNm());
                        }
                        if (isNotEmpty(esZipCodeVO.getDong())) {
                            fullAddr.append(" ").append(esZipCodeVO.getDong());
                        }
                        if (isNotEmpty(esZipCodeVO.getBuildingNm())) {
                            fullAddr.append(" ").append(esZipCodeVO.getBuildingNm());
                        }
                        esZipCodeVO.setAddress_name(fullAddr.append(" ").toString());
                        String breakpoint = "";
                        esZipCodeVOList.add(esZipCodeVO);
                    });
                ;
                String breakPoint = "";
                esZipcodeRepository.saveAll(esZipCodeVOList);
            }
        );
//        ZipCodeVO conditionVO = new ZipCodeVO();
//        conditionVO.setDong("덕이동");
//        List<EsZipCodeVO> esZipCodeVOList = new ArrayList<>();
//        coreZipcodeMapper.retrieveBofZipCodeListAll(conditionVO)
//            .forEach((zipCodeVO) -> {
//
//                EsZipCodeVO esZipCodeVO = objectMapper.convertValue(zipCodeVO, EsZipCodeVO.class);
//                StringBuilder fullAddr = new StringBuilder("");
//
//                if (isNotEmpty(esZipCodeVO.getCity())) {
//                    fullAddr.append(esZipCodeVO.getCity());
//                }
//                if (isNotEmpty(esZipCodeVO.getGungu())) {
//                    fullAddr.append(" ").append(esZipCodeVO.getGungu());
//                }
//                if (isNotEmpty(esZipCodeVO.getBunji())) {
//                    fullAddr.append(" ").append(esZipCodeVO.getBunji());
//                }
//                if (isNotEmpty(esZipCodeVO.getRoadNm())) {
//                    fullAddr.append(" ").append(esZipCodeVO.getRoadNm());
//                }
//                if (isNotEmpty(esZipCodeVO.getDongNm())) {
//                    fullAddr.append(" ").append(esZipCodeVO.getDongNm());
//                }
//                if (isNotEmpty(esZipCodeVO.getDong())) {
//                    fullAddr.append(" ").append(esZipCodeVO.getDong());
//                }
//                if (isNotEmpty(esZipCodeVO.getBuildingNm())) {
//                    fullAddr.append(" ").append(esZipCodeVO.getBuildingNm());
//                }
//                esZipCodeVO.setAddress_name(fullAddr.append(" ").toString());
//                String breakpoint = "";
//                esZipCodeVOList.add(esZipCodeVO);
//            });
//        esZipcodeRepository.saveAll(esZipCodeVOList);
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
