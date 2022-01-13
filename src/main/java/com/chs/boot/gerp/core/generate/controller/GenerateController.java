package com.chs.boot.gerp.core.generate.controller;

import com.chs.boot.gerp.b2b.generate.model.SchemaColumnConditionVO;
import com.chs.boot.gerp.b2b.generate.model.SchemaColumnVO;
import com.chs.boot.gerp.core.generate.service.GenerateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateController {

    @Autowired
    GenerateService generateService;

    @PostMapping("/gerp/gen/hello")
    public void hello(String good) {
    }

    @PostMapping("/gerp/gen/getEoString")
    public List<SchemaColumnVO> retrieveColumnSchema(
        @RequestBody SchemaColumnConditionVO schemaColumnConditionVO) {
        return generateService.getEOString(schemaColumnConditionVO.getTableName(),isEmpty());
    }

    @PostMapping("/gerp/gen/retrieveColumnSchema")
    public List<SchemaColumnVO> retrieveColumnSchema(
        @RequestBody SchemaColumnConditionVO schemaColumnConditionVO) {
        return generateService.retrieveColumnSchema(schemaColumnConditionVO);
    }
}
