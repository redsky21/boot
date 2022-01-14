package com.chs.boot.gerp.core.generate.service;

import static com.chs.boot.common.util.CommonUtil.getListSize;
import static com.chs.boot.common.util.CommonUtil.isNotNullAndEmpty;
import static com.chs.boot.common.util.StringUtil.getNewLineString;
import static com.chs.boot.common.util.StringUtil.getTabString;
import static com.chs.boot.common.util.StringUtil.lastIndexString;
import static com.chs.boot.common.util.StringUtil.lowerCaseFirst;
import static com.chs.boot.common.util.StringUtil.upperCaseFirst;

import com.chs.boot.common.util.StringUtil;
import com.chs.boot.gerp.b2b.generate.mapper.B2bGenerateMapper;
import com.chs.boot.gerp.b2b.generate.model.SchemaColumnConditionVO;
import com.chs.boot.gerp.b2b.generate.model.SchemaColumnVO;
import com.chs.boot.gerp.core.generate.mapper.CoreGenerateMapper;
import com.chs.boot.gerp.core.generate.model.ConvertDataTypeVO;
import com.chs.boot.gerp.core.generate.model.CoreColumnVO;
import com.chs.boot.gerp.core.generate.model.TepGenFileInfoEO;
import com.chs.boot.gerp.core.generate.model.TepGenMapperMethodInfoEO;
import com.chs.boot.gerp.core.generate.model.TepGenTemplateEO;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.text.CaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GenerateService {

    @Autowired
    B2bGenerateMapper b2bGenerateMapper;

    @Autowired
    CoreGenerateMapper coreGenerateMapper;

    public String insertMapperMethodForTable(Long packageNo, String packageName,
        String tableName, String eoName) {
        String mapperXmlFileName = "";
        insertInsertMapperMethod(packageNo, packageName, tableName, eoName);
        //update method 만들기
        //delete method 만들기
        //validation
        return mapperXmlFileName;
    }

    private void insertInsertMapperMethod(Long packageNo, String packageName,
        String tableName, String eoName) {
        //insert method 만들기

        String mapperPackageName = packageName + ".mapper";
        String mapperXmlName = upperCaseFirst(lastIndexString(packageName, ".")) + "Mapper.xml";
        String mapperClassName = upperCaseFirst(lastIndexString(packageName, ".")) + ".java";
        String methodAnnotationName = "@Transactional";
        String methodName =
            "insertMulti" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_');
        String methodParamClassName = eoName;
        String methodParamInstantName = lowerCaseFirst(methodParamClassName);
        String xmlMethodType = "insert";
        String sqlStmt = getInsertString(packageName, tableName, eoName, methodName);

        coreGenerateMapper.insertMultiTepGenMapperMethodInfo(List.of(
            TepGenMapperMethodInfoEO.builder().mapperPackageName(mapperPackageName)
                .mapperXmlName(mapperXmlName)
                .mapperClassName(mapperClassName)
                .methodAnnotationName(methodAnnotationName)
                .methodName(methodName)
                .methodParamClassName(methodParamClassName)
                .methodParamInstantName(methodParamInstantName)
                .xmlMethodType(xmlMethodType)
                .tableName(tableName)
                .sqlStmt(sqlStmt).build()));
    }


    private String getInsertString(String packageName,
        String tableName, String eoName, String methodName) {
        String returnString = "";
        String templateString = getTemplateSqlStmtString("MapperXmlInsert");
//        String methodName =
//            "insertMulti" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_');
        String eoFullPathName = packageName + "." + "model." + eoName;

        SchemaColumnConditionVO schemaColumnConditionVO = new SchemaColumnConditionVO();
        schemaColumnConditionVO.setTableName(tableName);

        List<SchemaColumnVO> schemaColumnVOList = retrieveColumnSchema(
            schemaColumnConditionVO);
        StringBuilder columnName = new StringBuilder("");
        StringBuilder variableName = new StringBuilder("");
        if (isNotNullAndEmpty(schemaColumnVOList)) {
            final int[] inx = {0};
            schemaColumnVOList.forEach(schemaColumnVO -> {
                columnName.append(getNewLineString());
                columnName.append(getTabString(3));
                inx[0] = inx[0] + 1;
                if (inx[0] > 0) {
                    columnName.append(",");

                }
                columnName.append(schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT));

                variableName.append(getNewLineString());
                variableName.append(getTabString(3));
                if (inx[0] > 0) {
                    variableName.append(",");

                }
                variableName.append("#{item.");
                variableName.append(
                    CaseUtils.toCamelCase(schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT),
                        false, '_'));
                variableName.append("}");
            });
        }
        returnString = templateString;
        returnString = returnString.replace("@methodName", methodName);
        returnString = returnString.replace("@eoFullPathName", eoFullPathName);
        returnString = returnString.replace("@tableName", tableName);
        returnString = returnString.replace("@columnName", columnName.toString());
        returnString = returnString.replace("@variableName", variableName.toString());

        return returnString;
    }

    ;

    public String makeEOFile(String packageName, String tableName) {
        String eoClassName = CaseUtils.toCamelCase(tableName, true, '_') + "EO";
        String eoString = getEOString(packageName, tableName, eoClassName);
        String fileName = eoClassName + ".java";
        createFile(packageName, fileName, eoString);
        //file 생성정보
        coreGenerateMapper.insertMulti(List.of(
            TepGenFileInfoEO.builder().fileName(fileName).packageName(packageName)
                .fileContents(eoString).build()));
        return eoClassName;
    }

    public void createFile(String packageName, String fileName, String fileContents) {
        File mainDir = new File("c:" + File.separatorChar + "chsWorkNew");
        //한폴더에 몰자
        String[] g = packageName.split("[.]");
        String strDir = g[g.length - 1];
        String dirName = "c:" + File.separatorChar + "chsWorkNew" + File.separatorChar + strDir;
        if (!mainDir.isDirectory()) {
            mainDir.mkdir();
        }
        File dir = new File(dirName);
        if (!dir.isDirectory()) {
            dir.mkdir();
        }
        Path filePath = Paths.get(dirName, File.separatorChar + fileName);
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(filePath.toString(), false);
            bw = new BufferedWriter(fw);
            bw.write(fileContents);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getEOString(String packageName, String tableName, String eoClassName) {
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
        String replaceString = getVOColumnString(coreColumnVOList, 1);
        //3 eo name replace
        returnString = templateString.replace("//@EONameHere", eoClassName);
        returnString = returnString.replace("//@GenHere", replaceString);
        returnString = returnString.replace("//@PackageNameHere", packageName);
        return returnString;
    }

    private String getTemplateSqlStmtString(String templateType) {
        String returnString = "";
        TepGenTemplateEO conditionEO = TepGenTemplateEO.builder().templateType(templateType).build();
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
                returnString.append(
                    CaseUtils.toCamelCase(coreColumnVO.getColumnName(), false, '_'));
                returnString.append(";");
                returnString.append(getNewLineString());
            });


        }
        return returnString.toString();
    }

}
