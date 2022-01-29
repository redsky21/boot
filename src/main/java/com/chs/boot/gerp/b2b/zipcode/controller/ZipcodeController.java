package com.chs.boot.gerp.b2b.zipcode.controller;


import com.chs.boot.gerp.b2b.zipcode.model.ZipCodeVO;
import com.chs.boot.gerp.b2b.zipcode.service.ZipcodeService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ZipcodeController {


    @Autowired
    ZipcodeService zipcodeService;


    @RequestMapping(method = RequestMethod.GET, path = "/zipcode/retrieveZipCode")
    public void retrieveZipCode() throws Exception {
//        zipcodeService.doTransfer();
        zipcodeService.doSearch();
    }

}