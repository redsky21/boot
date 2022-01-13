package com.chs.boot.gerp.core.generate.service;

import static com.chs.boot.common.util.CommonUtil.getListSize;
import static com.chs.boot.common.util.CommonUtil.isNotNullAndEmpty;
import static com.chs.boot.common.util.StringUtil.getNewLineString;
import static com.chs.boot.common.util.StringUtil.getTabString;

import com.chs.boot.gerp.b2b.generate.mapper.B2bGenerateMapper;
import com.chs.boot.gerp.b2b.generate.model.SchemaColumnConditionVO;
import com.chs.boot.gerp.b2b.generate.model.SchemaColumnVO;
import com.chs.boot.gerp.core.generate.mapper.CoreGenerateMapper;
import com.chs.boot.gerp.core.generate.model.ConvertDataTypeVO;
import com.chs.boot.gerp.core.generate.model.CoreColumnVO;
import com.chs.boot.gerp.core.generate.model.TepGenTemplateEO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.text.CaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerateService {

    @Autowired
    B2bGenerateMapper b2bGenerateMapper;

    @Autowired
    CoreGenerateMapper coreGenerateMapper;



    public String getEOString(String packageName, String tableName, String eoInstanceName) {
        String returnString = "";
        //1 get EO Template
        String templateString = getTemplateSqlStmtString("EO");
        //2 get Column Info
        SchemaColumnConditionVO schemaColumnConditionVO = new SchemaColumnConditionVO();
        schemaColumnConditionVO.setTableName(tableName);

        List<SchemaColumnVO> schemaColumnVOList = retrieveColumnSchema(
            schemaColumnConditionVO);
        List<CoreColumnVO> coreColumnVOList = new ArrayList<>();
        if (isNotNullAndEmpty(schemaColumnVOList)) {
            schemaColumnVOList.stream().forEach(schemaColumnVO -> {

                coreColumnVOList.add(CoreColumnVO.builder().dataType(schemaColumnVO.getDataType())
                    .columnName(schemaColumnVO.getColumnName()).build()

                );
            });
        }

        //2 get Replace String
        String replaceString = getVOColumnString(coreColumnVOList,1);
        //3 eo name replace
        returnString = templateString.replace("//@EONameHere",eoInstanceName);
        returnString = returnString.replace("//@GenHere",replaceString);
        returnString = returnString.replace("//@PackageNameHere",packageName);
        return returnString;
    }

    private String getTemplateSqlStmtString(String templateType) {
        String returnString = "";
        TepGenTemplateEO conditionEO = new TepGenTemplateEO();
        conditionEO.setTemplateType(templateType);
        List<TepGenTemplateEO> tepGenTemplateEOList = coreGenerateMapper.retrieveTepGenTemplate(
            conditionEO);
        if (isNotNullAndEmpty(tepGenTemplateEOList)) {
            returnString = tepGenTemplateEOList.get(0).getTemplateSqlStmt();
        }
        return returnString;
    }


    public List<SchemaColumnVO> retrieveColumnSchema(
        SchemaColumnConditionVO schemaColumnConditionVO) {
        List<SchemaColumnVO> result = b2bGenerateMapper.retrieveColumnSchema(
            schemaColumnConditionVO);
        return result;
    }

    public String getVOColumnString(List<CoreColumnVO> coreColumnVOList, int tabInx) {
        String tabString = getTabString(tabInx);
        StringBuilder returnString = new StringBuilder("");
        if (getListSize(coreColumnVOList) > 0) {
            List<ConvertDataTypeVO> dataTypeVOList = coreGenerateMapper.getConvertDataType();
            Map<String, String> dataTypeMap = new HashMap<>();
            if (getListSize(dataTypeVOList) > 0) {
                dataTypeVOList.forEach(convertDataTypeVO -> {
                    dataTypeMap.put(convertDataTypeVO.getMariaDataType(),
                        convertDataTypeVO.getJavaDataType());
                });
            }

            coreColumnVOList.stream().forEach(coreColumnVO -> {
                returnString.append(tabString);
                returnString.append("private ");
                returnString.append(isNotNullAndEmpty(dataTypeMap.get(coreColumnVO.getDataType()))
                    ? dataTypeMap.get(coreColumnVO.getDataType()) : "String");
                returnString.append(" ");
                returnString.append(CaseUtils.toCamelCase(coreColumnVO.getColumnName(),false,'_'));
                returnString.append(";");
                returnString.append(getNewLineString());
            });


        }
        return returnString.toString();
    }

}
