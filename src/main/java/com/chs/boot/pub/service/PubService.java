package com.chs.boot.pub.service;

import com.chs.boot.pub.mapper.PubMapper;
import com.chs.boot.pub.model.PubBaseDTO;
import com.chs.boot.pub.model.PubGridDTO;
import com.chs.boot.pub.model.PubItemDTO;
import com.chs.boot.pub.model.PubTemplateDO;
import com.chs.boot.pub.model.PubWordDO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        String makeFooterLayoutText = makeFooterLayoutText(pubBaseDTO.getGridItemList());
        String makeConstHeaderBottomText = makeConstHeaderBottomText();
        String makeBodyTopText = makeBodyTopText();
        String makeContentTopText = makeContentTopText();
        String makeSearchCondition = makeSearchCondition(pubBaseDTO.getSearchItemList(),
            pubBaseDTO.getSearchButtonList());
        String makeGridHeaderText = makeGridHeaderText();
        String makeGridButtonText = makeGridButtonText(pubBaseDTO.getGridButtonList());
        String makeContentBottomText = makeContentBottomText();
        String returnString = headerConstText + getNewLineString()
            + scriptTopText + getNewLineString()
            + columnLayoutText + getNewLineString()
            + makeFooterLayoutText + getNewLineString()
            + makeConstHeaderBottomText + getNewLineString()
            + makeBodyTopText + getNewLineString()
            + makeContentTopText + getNewLineString()
            + makeSearchCondition + getNewLineString()
            + makeGridHeaderText + getNewLineString()
            + makeGridButtonText + getNewLineString()
            + makeContentBottomText + getNewLineString();
        //testData 추가
        String testDataList = getTestDataString(pubBaseDTO.getTestDataList(),
            pubBaseDTO.getGridItemList());
        if(!isEmpty(testDataList)){
            returnString = returnString.replace("var gridData = [];//replace here","var gridData ="+testDataList+";");
        }
        return returnString;
    }

    private String getTestDataString(List<LinkedHashMap<String, Object>> testDataList,
        List<PubGridDTO> gridItemList) {
        String returnString = "";
        HashMap<String, String> gridItemMap = new HashMap<>();
        if (getListSize(gridItemList) > 0) {
            gridItemList.stream().forEach(pubGridDTO -> {
                if (!isEmpty(pubGridDTO.getDataField())) {
                    gridItemMap.put(pubGridDTO.getDataField(),
                        isEmpty(pubGridDTO.getDataType()) == null ? "string"
                            : pubGridDTO.getDataType());
                }
            });
        }

        if (!isEmpty(testDataList)) {
            List<LinkedHashMap<String, Object>> tmpResultList = new ArrayList<>();
            testDataList.stream().forEach(testData -> {
//                testData.remove("rowId");
//                tmpResultList.add(testData);
                LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
                for (String key : testData.keySet()) {
                    if (!key.equals("rowId")) {
                        String dataType = gridItemMap.get(key);
                        Object val = testData.get(key);
                        if (!isEmpty(dataType) && dataType.equals("numeric")
                            && val instanceof String) {
                            try {
                                int k = ((String) val).indexOf(".");
                                if( k < 0 ){
                                    Long a = Long.parseLong((String) val);
                                    linkedHashMap.put(key,a);
                                }
                                else{
                                    linkedHashMap.put(key, Double.parseDouble((String) val));
                                }



                            } catch (Exception e) {
                                linkedHashMap.put(key, 0);
                            }

                        } else {
                            linkedHashMap.put(key, val);
                        }

                    }

                }
                tmpResultList.add(linkedHashMap);
            });
            try {
                returnString = objectMapper.writeValueAsString(tmpResultList);
            } catch (JsonProcessingException e) {
                returnString = "";
            }
        }
        return returnString;
    }


    private String makeGridButtonText(List<PubItemDTO> gridButtonList) {
        StringBuilder returnStringBuilder = new StringBuilder("");
        if (getListSize(gridButtonList) > 0) {
            returnStringBuilder.append(getNewLineString());
            returnStringBuilder.append(getTabString(4));
            returnStringBuilder.append("<div class=\"formGroup\">");
            gridButtonList.stream().forEach(gridButtonAtom -> {
                returnStringBuilder.append(getAtomButtonString(gridButtonAtom, 5L));
            });

            returnStringBuilder.append(getNewLineString());
            returnStringBuilder.append(getTabString(4));
            returnStringBuilder.append("</div>");
        }
        return returnStringBuilder.toString();
    }

    private String makeSearchCondition(List<PubItemDTO> searchItemList,
        List<PubItemDTO> searchButtonList) {
        StringBuilder returnStringBuilder = new StringBuilder("");
        if (getListSize(searchItemList) > 0 || getListSize(searchButtonList) > 0) {
            returnStringBuilder.append("\t\t\t<div class=\"inquiryBox dtShort type02\">\n");
        }

        if (getListSize(searchItemList) > 0) {
            returnStringBuilder.append("\t\t\t\t<dl>");
            returnStringBuilder.append(getSearchItemString(searchItemList));
            returnStringBuilder.append(getNewLineString());
            returnStringBuilder.append("\t\t\t\t</dl>");
        }

        if (getListSize(searchButtonList) > 0) {
            returnStringBuilder.append(getNewLineString());
            returnStringBuilder.append(getTabString(4));
            returnStringBuilder.append("<div class=\"btnGroup right\">");
            searchButtonList.stream().forEach(searchButtonAtom -> {
                returnStringBuilder.append(getAtomButtonString(searchButtonAtom, 5L));
            });

            returnStringBuilder.append(getNewLineString());
            returnStringBuilder.append(getTabString(4));
            returnStringBuilder.append("</div>");
        }

        if (getListSize(searchItemList) > 0 || getListSize(searchButtonList) > 0) {
            returnStringBuilder.append(getNewLineString());
            returnStringBuilder.append(getTabString(3));
            returnStringBuilder.append("</div>");
            returnStringBuilder.append(getNewLineString());
        }

        return returnStringBuilder.toString();
    }

    private String getAtomButtonString(PubItemDTO pubItemDTO, Long tabIndex) {
        int tabInx = (tabIndex == null ? 0 : tabIndex.intValue());
        StringBuilder returnStringBuilder = new StringBuilder("");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx));
        returnStringBuilder.append("<button type=\"button\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 1));
        returnStringBuilder.append("name=\"");
        returnStringBuilder.append(isEmpty(pubItemDTO.getName()) ? "" : pubItemDTO.getName());
        returnStringBuilder.append("\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 1));
        returnStringBuilder.append("class=\"");
        returnStringBuilder.append(
            isEmpty(pubItemDTO.getCompClass()) ? "" : pubItemDTO.getCompClass());
        returnStringBuilder.append("\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 1));
        returnStringBuilder.append("id=\"");
        returnStringBuilder.append(isEmpty(pubItemDTO.getCompId()) ? "" : pubItemDTO.getCompId());
        returnStringBuilder.append("\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 1));
        returnStringBuilder.append(">");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 2));
        returnStringBuilder.append(isEmpty(pubItemDTO.getLabel()) ? "" : pubItemDTO.getLabel());

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx));
        returnStringBuilder.append("</button>");

        return returnStringBuilder.toString();
    }

    private String getSearchItemString(List<PubItemDTO> searchItemList) {
        StringBuilder returnStringBuilder = new StringBuilder("");
        for (int inx = 0; inx < searchItemList.size(); inx++) {
            PubItemDTO conditionDTO = searchItemList.get(inx);
            if (conditionDTO.getType().equals("DatePicker")) {
                returnStringBuilder.append(getDateFieldString(conditionDTO, 5L));
            } else if (conditionDTO.getType().equals("Select")) {
                returnStringBuilder.append(getSelectString(conditionDTO, 5L));

            } else if (conditionDTO.getType().equals("Radio")) {

            } else if (conditionDTO.getType().equals("TextField")) {
                returnStringBuilder.append(getTextFieldString(conditionDTO, 5L));


            } else if (conditionDTO.getType().equals("Checkbox")) {
                returnStringBuilder.append(getCheckGroupStartString(conditionDTO, 5L));
                returnStringBuilder.append(getCheckFieldString(conditionDTO, 5L, (long) inx));
                if (inx != (searchItemList.size() - 1)) {
                    while (!isEmpty(searchItemList.get(inx + 1).getType()) &&
                        searchItemList.get(inx + 1).getType().equals("Checkbox")) {
                        inx++;
                        PubItemDTO innerConditionDTO = searchItemList.get(inx);
                        returnStringBuilder.append(
                            getCheckFieldString(innerConditionDTO, 5L, (long) inx));
                    }

                }

                returnStringBuilder.append(getCheckGroupEndString(conditionDTO, 5L));

            } else if (conditionDTO.getType().equals("Button")) {
                returnStringBuilder.append(getButtonString(conditionDTO, 5L));

            }
        }
        return returnStringBuilder.toString();
    }

    private String getButtonString(PubItemDTO pubItemDTO, Long tabIndex) {
        int tabInx = (tabIndex == null ? 0 : tabIndex.intValue());
        StringBuilder returnStringBuilder = new StringBuilder("");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx));
        returnStringBuilder.append("<dd>");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 1));
        returnStringBuilder.append("<button type=\"button\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 2));
        returnStringBuilder.append("name=\"");
        returnStringBuilder.append(isEmpty(pubItemDTO.getName()) ? "" : pubItemDTO.getName());
        returnStringBuilder.append("\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 2));
        returnStringBuilder.append("class=\"");
        returnStringBuilder.append(
            isEmpty(pubItemDTO.getCompClass()) ? "" : pubItemDTO.getCompClass());
        returnStringBuilder.append("\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 2));
        returnStringBuilder.append("id=\"");
        returnStringBuilder.append(isEmpty(pubItemDTO.getCompId()) ? "" : pubItemDTO.getCompId());
        returnStringBuilder.append("\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 2));
        returnStringBuilder.append(">");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 3));
        returnStringBuilder.append(isEmpty(pubItemDTO.getLabel()) ? "" : pubItemDTO.getLabel());

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 1));
        returnStringBuilder.append("</button>");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx));
        returnStringBuilder.append("</dd>");
        return returnStringBuilder.toString();
    }

    private String getSelectString(PubItemDTO pubItemDTO, Long tabIndex) {
        int tabInx = (tabIndex == null ? 0 : tabIndex.intValue());
        StringBuilder returnStringBuilder = new StringBuilder("");
        if (!isEmpty(pubItemDTO.getLabel())) {
            returnStringBuilder.append(getDtString(pubItemDTO.getLabel(), tabIndex));
        }
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx));
        returnStringBuilder.append("<dd>");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 1));
        returnStringBuilder.append("<div class=\"formWrap\">");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 2));
        returnStringBuilder.append("<select");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 3));
        returnStringBuilder.append("name=\"");
        returnStringBuilder.append(isEmpty(pubItemDTO.getName()) ? "" : pubItemDTO.getName());
        returnStringBuilder.append("\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 3));
        returnStringBuilder.append("class=\"");
        returnStringBuilder.append(
            isEmpty(pubItemDTO.getCompClass()) ? "" : pubItemDTO.getCompClass());
        returnStringBuilder.append("\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 3));
        returnStringBuilder.append("id=\"");
        returnStringBuilder.append(isEmpty(pubItemDTO.getCompId()) ? "" : pubItemDTO.getCompId());
        returnStringBuilder.append("\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 2));
        returnStringBuilder.append(">");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 3));
        returnStringBuilder.append("<option>전체</option>");

        for (int inx = 0; inx < 3; inx++) {
            returnStringBuilder.append(getNewLineString());
            returnStringBuilder.append(getTabString(tabInx + 3));
            String optString = (isEmpty(pubItemDTO.getLabel()) ? "option" + inx
                : pubItemDTO.getLabel() + inx);
            returnStringBuilder.append("<option>" + optString + "</option>");

        }

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 2));
        returnStringBuilder.append("</select>");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 1));
        returnStringBuilder.append("</div>");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx));
        returnStringBuilder.append("</dd>");
        return returnStringBuilder.toString();
    }

    private String getTextFieldString(PubItemDTO pubItemDTO, Long tabIndex) {
        int tabInx = (tabIndex == null ? 0 : tabIndex.intValue());
        StringBuilder returnStringBuilder = new StringBuilder("");
        if (!isEmpty(pubItemDTO.getLabel())) {
            returnStringBuilder.append(getDtString(pubItemDTO.getLabel(), tabIndex));
        }
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx));
        returnStringBuilder.append("<dd>");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 1));
        returnStringBuilder.append("<div class=\"formWrap\">");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 2));
        returnStringBuilder.append("<input");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 3));
        returnStringBuilder.append("type=\"text\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 3));
        returnStringBuilder.append("name=\"");
        returnStringBuilder.append(isEmpty(pubItemDTO.getName()) ? "" : pubItemDTO.getName());
        returnStringBuilder.append("\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 3));
        returnStringBuilder.append("value=\"\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 3));
        returnStringBuilder.append("class=\"");
        returnStringBuilder.append(
            isEmpty(pubItemDTO.getCompClass()) ? "" : pubItemDTO.getCompClass());
        returnStringBuilder.append("\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 3));
        returnStringBuilder.append("placeholder=\"");
        returnStringBuilder.append(
            isEmpty(pubItemDTO.getPlaceholder()) ? "" : pubItemDTO.getPlaceholder());
        returnStringBuilder.append("\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 3));
        returnStringBuilder.append("id=\"");
        returnStringBuilder.append(isEmpty(pubItemDTO.getCompId()) ? "" : pubItemDTO.getCompId());
        returnStringBuilder.append("\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 2));
        returnStringBuilder.append("/>");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 1));
        returnStringBuilder.append("</div>");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx));
        returnStringBuilder.append("</dd>");
        return returnStringBuilder.toString();
    }


    private String getCheckFieldString(PubItemDTO pubItemDTO, Long tabIndex, Long checkIndex) {
        int tabInx = (tabIndex == null ? 0 : tabIndex.intValue());
        StringBuilder returnStringBuilder = new StringBuilder("");
//        if (!isEmpty(pubItemDTO.getLabel())) {
//            returnStringBuilder.append(getDtString(pubItemDTO.getLabel(), tabIndex));
//        }
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx));
        returnStringBuilder.append("<input");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 1));
        returnStringBuilder.append("type=\"checkbox\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 1));
        returnStringBuilder.append("id=\"");
        String itemId =
            isEmpty(pubItemDTO.getCompId()) ? "checkbox" + checkIndex : pubItemDTO.getCompId();
        returnStringBuilder.append(itemId);
        returnStringBuilder.append("\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 1));
        returnStringBuilder.append("class=\"type01\"");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 1));
        returnStringBuilder.append("checked=\"\"");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx));
        returnStringBuilder.append("/>");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx));
        returnStringBuilder.append("<label for=\"");
        returnStringBuilder.append(itemId);
        returnStringBuilder.append("\">");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 1));
        returnStringBuilder.append("<span>");
        returnStringBuilder.append(pubItemDTO.getLabel());
        returnStringBuilder.append("</span>");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx));
        returnStringBuilder.append("</label>");

        return returnStringBuilder.toString();
    }

    private String getCheckGroupStartString(PubItemDTO pubItemDTO, Long tabIndex) {
        int tabInx = (tabIndex == null ? 0 : tabIndex.intValue());
        StringBuilder returnStringBuilder = new StringBuilder("");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx));
        returnStringBuilder.append("<div class=\"");
        returnStringBuilder.append(
            isEmpty(pubItemDTO.getCompClass()) ? "checkGroup" : pubItemDTO.getCompClass());
        returnStringBuilder.append("\">");
        return returnStringBuilder.toString();
    }

    private String getCheckGroupEndString(PubItemDTO pubItemDTO, Long tabIndex) {
        int tabInx = (tabIndex == null ? 0 : tabIndex.intValue());
        StringBuilder returnStringBuilder = new StringBuilder("");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx));
        returnStringBuilder.append("</div>");
        return returnStringBuilder.toString();
    }

    public String retrievePubWord(PubWordDO pubWordDO){
        List<PubWordDO> list = pubMapper.retrievePubWord(pubWordDO);
        return list.size() ==0 ? "" : list.get(0).getFieldName();
    }

    private String getDateFieldString(PubItemDTO pubItemDTO, Long tabIndex) {
        int tabInx = (tabIndex == null ? 0 : tabIndex.intValue());
        StringBuilder returnStringBuilder = new StringBuilder("");
        if (!isEmpty(pubItemDTO.getLabel())) {
            returnStringBuilder.append(getDtString(pubItemDTO.getLabel(), tabIndex));
        }
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx));
        returnStringBuilder.append("<dd>");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 1));
        returnStringBuilder.append("<div class=\"formWrap\">");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 2));
        returnStringBuilder.append("<div class=\"");
        returnStringBuilder.append(
            isEmpty(pubItemDTO.getCompClass()) ? "dateWrap" : pubItemDTO.getCompClass());
        returnStringBuilder.append("\">");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 3));
        returnStringBuilder.append("<input");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 4));
        returnStringBuilder.append("type=\"text\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 4));
        returnStringBuilder.append("name=\"");
        returnStringBuilder.append(isEmpty(pubItemDTO.getName()) ? "date" : pubItemDTO.getName());
        returnStringBuilder.append("\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 4));
        returnStringBuilder.append("value=\"01/05/2022\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 4));
        returnStringBuilder.append("class=\"inp\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 4));
        returnStringBuilder.append("id=\"datepicker");
//        returnStringBuilder.append(isEmpty(pubItemDTO.getCompId()) ? "datepicker" : pubItemDTO.getCompId());

        returnStringBuilder.append("\"");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 3));
        returnStringBuilder.append("/>");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 3));
        returnStringBuilder.append("<button");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 4));
        returnStringBuilder.append("class=\"datepickerBtn\"");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 4));
        returnStringBuilder.append("title=\"날짜입력\"");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 3));
        returnStringBuilder.append("></button>");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 2));
        returnStringBuilder.append("</div>");

        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx + 1));
        returnStringBuilder.append("</div>");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabInx));
        returnStringBuilder.append("</dd>");
        return returnStringBuilder.toString();
    }

    private Boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    private String getDtString(String label, Long tabIndex) {
        StringBuilder returnStringBuilder = new StringBuilder("");
        returnStringBuilder.append(getNewLineString());
        returnStringBuilder.append(getTabString(tabIndex == null ? 0 : tabIndex.intValue()));
        returnStringBuilder.append("<dt>");
        returnStringBuilder.append(label);
//        returnStringBuilder.append(getNewLineString());
//        returnStringBuilder.append(getTabString(tabIndex == null ? 0 : tabIndex.intValue()));
        returnStringBuilder.append("</dt>");
        return returnStringBuilder.toString();
    }

    private String getNewLineString() {
        return "\n";
    }


    private String getTabString(int index) {
        StringBuilder returnStringBuilder = new StringBuilder("");
        for (int inx = 0; inx < index; inx++) {
            returnStringBuilder.append("\t");
        }
        return returnStringBuilder.toString();
    }


    private String makeFooterLayoutText(List<PubGridDTO> pubGridDTOList) {
        StringBuilder headerStringBuilder = new StringBuilder("");
        List<PubGridDTO> realColumnList = new ArrayList<>();
        if (getListSize(pubGridDTOList) > 0) {

            pubGridDTOList.stream().forEach(pubGridDTO -> {
                if (isCheckRealColumn(pubGridDTO, pubGridDTOList)) {
                    realColumnList.add(pubGridDTO);
                }
            });
            List<PubGridDTO> sumGridDTOList = pubGridDTOList.stream().filter(pubGridDTO ->
                pubGridDTO.getSumFlag() != null && pubGridDTO.getSumFlag().equals("Y")
            ).collect(Collectors.toList());

            if (getListSize(sumGridDTOList) > 0) {
                headerStringBuilder.append("\tvar footerLayout = [\n");
                headerStringBuilder.append("\t\t{\n");
                headerStringBuilder.append("\t\t\tlabelText: \"∑\",\n");
                headerStringBuilder.append("\t\t\tpositionField: \"#base\",\n");
                headerStringBuilder.append("\t\t},\n");

                AtomicReference<Long> inx = new AtomicReference<>(0L);
                AtomicReference<Boolean> isFirst = new AtomicReference<>(false);
                realColumnList.stream().forEach(realColumn -> {

                        inx.getAndSet(inx.get() + 1);
                        if (inx.get() == 1) {
                            isFirst.set(true);
                            headerStringBuilder.append("\t\t{\n");
                            headerStringBuilder.append("\t\t\tdataField: \"");
                            headerStringBuilder.append(realColumn.getDataField());
                            headerStringBuilder.append("\",\n");
                            headerStringBuilder.append("\t\t\tpositionField: \"");
                            headerStringBuilder.append(realColumn.getDataField());
                            headerStringBuilder.append("\",\n");
                            headerStringBuilder.append("\t\t\tlabelText: \"합계\",\n");


                        }
                        if (isSumColumn(realColumn, sumGridDTOList)) {
                            if (isFirst.get()) {
                                headerStringBuilder.append("\t\t\tcolSpan: \"");
                                headerStringBuilder.append(inx.get()-1);
                                headerStringBuilder.append("\",\n\t\t},\n");
                                isFirst.set(false);
//                                inx.set(0L);
                            }
                            headerStringBuilder.append(sumFieldString(realColumn));

                        }
                    }
                );
                headerStringBuilder.append("\t\t];");
            }
        }
        return headerStringBuilder.toString();
    }

    private String sumFieldString(PubGridDTO pubGridDTO) {
        StringBuilder returnString = new StringBuilder("");
        returnString.append("\t\t{\n");
        returnString.append("\t\t\tdataField: \"");
        returnString.append(pubGridDTO.getDataField());
        returnString.append("\",\n");
        returnString.append("\t\t\tpositionField: \"");
        returnString.append(pubGridDTO.getDataField());
        returnString.append("\",\n");
        returnString.append("\t\t\toperation: \"SUM\",\n");
        returnString.append("\t\t\tstyle: \"auiRight\",\n");
        returnString.append("\t\t\tcolSpan: 1,\n");
        returnString.append("\t\t\tformatString: \"#,##0\",\n");
        returnString.append("\t\t},\n");
        return returnString.toString();
    }

    private Boolean isSumColumn(PubGridDTO pubGridDTO, List<PubGridDTO> sumGridDTOList) {
        Boolean isSumColumn = false;
        for (PubGridDTO sumRow : sumGridDTOList) {
            if (pubGridDTO.getDataField().equals(sumRow.getDataField())) {
                return true;
            }
        }
        return isSumColumn;
    }

    private Boolean isCheckRealColumn(PubGridDTO pubGridDTO, List<PubGridDTO> pubGridDTOList) {
        Boolean isCheckRealColumn = true;
        if (!isEmpty(pubGridDTO.getDataField())) {
            for (PubGridDTO pubGridDTO1 : pubGridDTOList) {
                if (!isEmpty(pubGridDTO1.getDataField())) {
                    if (pubGridDTO.getDataField().equals(pubGridDTO1.getGroupDataField())) {
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
                    LinkedHashMap<String, Object> atomMap = objectMapper.convertValue(pubGridDTO,
                        LinkedHashMap.class);
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

                        if (!isEmpty(innerPubGridDTO.getGroupDataField()) && !isEmpty(
                            pubGridDTO.getDataField())) {
                            if (innerPubGridDTO.getGroupDataField()
                                .equals(pubGridDTO.getDataField())) {
                                matchCnt.getAndSet(matchCnt.get() + 1);
                                if (matchCnt.get() == 1) {
                                    headerStringBuilder.append("\t\t\tchildren :[\n");
                                }
                                headerStringBuilder.append("\t\t\t\t{\n");
                                LinkedHashMap<String, Object> innerAtomMap = objectMapper.convertValue(
                                    innerPubGridDTO, LinkedHashMap.class);
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
                    if (matchCnt.get() > 0) {
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

    private String makeBodyTopText() {
        PubTemplateDO conditionDO = new PubTemplateDO();
        StringBuilder headerStringBuilder = new StringBuilder("");
        conditionDO.setTmpType("bodyTop");
        List<PubTemplateDO> list = retrieveTemplateSource(conditionDO);
        list.stream().forEach((pubTemplateDO -> {
            headerStringBuilder.append(pubTemplateDO.getHtmlText());
            headerStringBuilder.append("\n");
        }));
        return headerStringBuilder.toString();
    }

    private String makeContentTopText() {
        PubTemplateDO conditionDO = new PubTemplateDO();
        StringBuilder headerStringBuilder = new StringBuilder("");
        conditionDO.setTmpType("contentTop");
        List<PubTemplateDO> list = retrieveTemplateSource(conditionDO);
        list.stream().forEach((pubTemplateDO -> {
            headerStringBuilder.append(pubTemplateDO.getHtmlText());
            headerStringBuilder.append("\n");
        }));
        return headerStringBuilder.toString();
    }

    private String makeGridHeaderText() {
        PubTemplateDO conditionDO = new PubTemplateDO();
        StringBuilder headerStringBuilder = new StringBuilder("");
        conditionDO.setTmpType("gridHeader");
        List<PubTemplateDO> list = retrieveTemplateSource(conditionDO);
        list.stream().forEach((pubTemplateDO -> {
            headerStringBuilder.append(pubTemplateDO.getHtmlText());
            headerStringBuilder.append("\n");
        }));
        return headerStringBuilder.toString();
    }


    private String makeConstHeaderBottomText() {
        PubTemplateDO conditionDO = new PubTemplateDO();
        StringBuilder headerStringBuilder = new StringBuilder("");
        conditionDO.setTmpType("headerScriptBottom");
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

    private String makeContentBottomText() {
        PubTemplateDO conditionDO = new PubTemplateDO();
        StringBuilder headerStringBuilder = new StringBuilder("");
        conditionDO.setTmpType("contentBottom");
        List<PubTemplateDO> list = retrieveTemplateSource(conditionDO);
        list.stream().forEach((pubTemplateDO -> {
            headerStringBuilder.append(pubTemplateDO.getHtmlText());
            headerStringBuilder.append("\n");
        }));
        return headerStringBuilder.toString();
    }

    public List<PubTemplateDO> retrieveTemplateSource(PubTemplateDO pubTemplateDO) {
        return pubMapper.retrieveTemplateSource(pubTemplateDO);
    }

}
