package com.chs.boot.gerp.core.generate.controller;

import static com.chs.boot.common.util.MyBatisUtil.isEmpty;

import com.chs.boot.gerp.b2b.generate.model.SchemaColumnConditionVO;
import com.chs.boot.gerp.b2b.generate.model.SchemaColumnVO;
import com.chs.boot.gerp.core.generate.model.SequenceConditionVO;
import com.chs.boot.gerp.core.generate.model.TepGenMasterInfoEO;
import com.chs.boot.gerp.core.generate.service.GenerateService;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.commons.text.CaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GenerateController {

    @Autowired
    GenerateService generateService;

    @PostMapping("/gerp/gen/hello")
    public void hello(String good) {
    }

    @PostMapping("/gerp/gen/getEoString")
    public String getEOString(
        @RequestBody SchemaColumnConditionVO schemaColumnConditionVO) {

//        String eoName = generateService.makeEOFile("a.b.c.d",schemaColumnConditionVO.getTableName());
//        generateService.insertMapperMethodForTable(-1L,"a.b.c.d",schemaColumnConditionVO.getTableName(),
//            eoName);
//        Long packageNo =  generateService.getNextVal(SequenceConditionVO.builder().sequenceName( "tem_gen_package_no_seq").build());
        String packageName =  schemaColumnConditionVO.getPackageName();

//        generateService.doTableJob(packageNo,packageName,schemaColumnConditionVO.getTableName());
        generateService.doMainJob(schemaColumnConditionVO.getPackageNo());
        return"1";

    }
    
    @PostMapping("/gerp/gen/getEoStringAsZip")
    public ResponseEntity<ByteArrayResource> getEOStringAsZip(
    		@RequestBody SchemaColumnConditionVO schemaColumnConditionVO) {

    	ByteArrayOutputStream byteArrayOutputStream = generateService.doMainJobAsZip(schemaColumnConditionVO.getPackageNo());

        if (byteArrayOutputStream == null) return ResponseEntity.notFound().build();

        byte[] data = byteArrayOutputStream.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(data);

    	return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + generateService.getZipFileName())
                .contentLength(data.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping("/gerp/gen-masters")
    public ResponseEntity<List<TepGenMasterInfoEO>> getAllTepGenMasterInfo() {
        List<TepGenMasterInfoEO> result;
        result = generateService.getAllTepGenMasterInfo(new TepGenMasterInfoEO());
        if (result != null && result.size() > 0) {
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/gerp/gen-masters")
    public ResponseEntity<String> createListOfTepGenMasterInfo(
            @RequestBody List<TepGenMasterInfoEO> listOfTepGenMasterInfo
    ) {
        if (listOfTepGenMasterInfo != null && listOfTepGenMasterInfo.size() > 0) {
            generateService.insertTepGenMasterInfoList(listOfTepGenMasterInfo);
            return ResponseEntity.ok().body("Created");
        }
        return ResponseEntity.badRequest().build();
    }
    
    @PostMapping("/gerp/gen/getNewPackageNo")
    public Long getNewPackageNo() {
        return generateService.getNewPackageNo();
    }

    @PostMapping("/gerp/gen/retrieveColumnSchema")
    public List<SchemaColumnVO> retrieveColumnSchema(
        @RequestBody SchemaColumnConditionVO schemaColumnConditionVO) {
    	return generateService.retrieveColumnSchema(schemaColumnConditionVO);
    }
}
