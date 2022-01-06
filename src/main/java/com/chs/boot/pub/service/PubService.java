package com.chs.boot.pub.service;

import com.chs.boot.pub.mapper.PubMapper;
import com.chs.boot.pub.model.PubBaseDTO;
import com.chs.boot.pub.model.PubGridDTO;
import com.chs.boot.pub.model.PubTemplateDO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class PubService {
    @Autowired
    PubMapper pubMapper;
    @Autowired
    ObjectMapper objectMapper;

    public String getHtmlString(PubBaseDTO pubBaseDTO) {
        String headerConstText = makeConstHeaderText();
        String scriptTopText = makeConstScriptTopText();
        String columnLayoutText = makeColumnLayoutText(pubBaseDTO.getGridItemList());
        return "";
    }

    private String makeFooterLayoutText(List<PubGridDTO> pubGridDTOList) {
        StringBuilder headerStringBuilder = new StringBuilder("");
        List<PubGridDTO> realColumnList = new ArrayList<>();
        if (getListSize(pubGridDTOList) > 0) {
            for(PubGridDTO pubGridDTO: pubGridDTOList ){
                if ( isCheckRealColumn(pubGridDTO,pubGridDTOList) ){
                    realColumnList.add(pubGridDTO);
                }
            }
            List<PubGridDTO> sumGridDTOList = pubGridDTOList.stream().filter(pubGridDTO ->
                 pubGridDTO.getSumFlag().equals("Y")
            ).collect(Collectors.toList());

            if( getListSize(sumGridDTOList) >0){

            }
        }
        return headerStringBuilder.toString();
    }

    private Boolean isCheckRealColumn(PubGridDTO pubGridDTO,List<PubGridDTO> pubGridDTOList){
        Boolean isCheckRealColumn = true;
        if(!isEmpty(pubGridDTO.getDataField())){
            for(PubGridDTO pubGridDTO1: pubGridDTOList ){
                if(!isEmpty(pubGridDTO1.getDataField())){
                    if( pubGridDTO.getDataField().equals(pubGridDTO1.getDataField())){
                        return false;
                    }
                }
            }
        }
        return isCheckRealColumn;
    }


    private String makeColumnLayoutText(List<PubGridDTO> pubGridDTOList) {
        StringBuilder headerStringBuilder = new StringBuilder("");
        if (getListSize(pubGridDTOList) > 0) {
            headerStringBuilder.append("\tvar columnLayout = [");
            headerStringBuilder.append("\n");
            pubGridDTOList.stream().forEach(pubGridDTO -> {

//                if(!isEmpty( pubGridDTO.getDataField())){
//                    headerStringBuilder.append("\t\t\tdataField : ");
//                    headerStringBuilder.append("\"");
//                    headerStringBuilder.append(pubGridDTO.getDataField());
//                    headerStringBuilder.append("\",");
//                    headerStringBuilder.append("\n");
//                }
                if (isEmpty(pubGridDTO.getGroupDataField())) {
                    headerStringBuilder.append("\t\t{\n");
                    LinkedHashMap<String, Object> atomMap = objectMapper.convertValue(pubGridDTO, LinkedHashMap.class);
                    atomMap.forEach((mapKey, mapVal) -> {
                        if (!isEmpty(mapVal) && !mapKey.equals("groupDataField")) {
                            headerStringBuilder.append("\t\t\t");
                            headerStringBuilder.append(mapKey);
                            headerStringBuilder.append(" : ");
                            if (mapVal instanceof String) {
                                headerStringBuilder.append("\"");
                            }
                            headerStringBuilder.append(mapVal.toString());
                            if (mapVal instanceof String) {
                                headerStringBuilder.append("\"");
                            }
                            headerStringBuilder.append(",");
                            headerStringBuilder.append("\n");
                        }
                    });
                    //child Find
                    AtomicReference<Long> matchCnt = new AtomicReference<>(0L);
                    pubGridDTOList.stream().forEach(innerPubGridDTO -> {

                        if (!isEmpty(innerPubGridDTO.getGroupDataField()) && !isEmpty(pubGridDTO.getDataField())) {
                            if (innerPubGridDTO.getGroupDataField().equals(pubGridDTO.getDataField())) {
                                matchCnt.getAndSet(matchCnt.get() + 1);
                                if(matchCnt.get() ==1){
                                    headerStringBuilder.append("\t\t\tchildren :[\n");
                                }
                                headerStringBuilder.append("\t\t\t\t{\n");
                                LinkedHashMap<String, Object> innerAtomMap = objectMapper.convertValue(innerPubGridDTO, LinkedHashMap.class);
                                innerAtomMap.forEach((mapKey, mapVal) -> {
                                    if (!isEmpty(mapVal) && !mapKey.equals("groupDataField")) {
                                        headerStringBuilder.append("\t\t\t\t\t");
                                        headerStringBuilder.append(mapKey);
                                        headerStringBuilder.append(" : ");
                                        if (mapVal instanceof String) {
                                            headerStringBuilder.append("\"");
                                        }
                                        headerStringBuilder.append(mapVal.toString());
                                        if (mapVal instanceof String) {
                                            headerStringBuilder.append("\"");
                                        }
                                        headerStringBuilder.append(",");
                                        headerStringBuilder.append("\n");
                                    }
                                });
                                headerStringBuilder.append("\t\t\t\t},\n");
                            }
                        }

                    });
                    if( matchCnt.get() >0){
                        headerStringBuilder.append("\t\t\t\t]\n");
                    }
                    headerStringBuilder.append("\t\t},\n");
                }


            });
            headerStringBuilder.append("\t];");
        }
        return headerStringBuilder.toString();
    }


    private Boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            String oString = (String) o;
            if (oString.equals("")) {
                return true;
            }
        }
        return false;
    }

    private Long getListSize(List list) {
        Long size = 0L;
        if (list != null && list.size() > 0) {
            size = Long.valueOf(list.size());
        }
        return size;
    }

    private String makeConstHeaderText() {
        PubTemplateDO conditionDO = new PubTemplateDO();
        StringBuilder headerStringBuilder = new StringBuilder("");
        conditionDO.setTmpType("header");
        List<PubTemplateDO> list = retrieveTemplateSource(conditionDO);
        list.stream().forEach((pubTemplateDO -> {
            headerStringBuilder.append(pubTemplateDO.getHtmlText());
            headerStringBuilder.append("\n");
        }));
        return headerStringBuilder.toString();
    }

    private String makeConstScriptTopText() {
        PubTemplateDO conditionDO = new PubTemplateDO();
        StringBuilder headerStringBuilder = new StringBuilder("");
        conditionDO.setTmpType("headerScriptTop");
        List<PubTemplateDO> list = retrieveTemplateSource(conditionDO);
        list.stream().forEach((pubTemplateDO -> {
            headerStringBuilder.append("\t");
            headerStringBuilder.append(pubTemplateDO.getHtmlText());
            headerStringBuilder.append("\n");
        }));
        return headerStringBuilder.toString();
    }

    public List<PubTemplateDO> retrieveTemplateSource(PubTemplateDO pubTemplateDO) {
        return pubMapper.retrieveTemplateSource(pubTemplateDO);
    }
}
