package com.chs.boot.gerp.core.generate.service;

import static com.chs.boot.common.constant.SystemConstant.BOF;
import static com.chs.boot.common.util.CommonUtil.getListSize;
import static com.chs.boot.common.util.CommonUtil.isNotNullAndEmpty;
import static com.chs.boot.common.util.CommonUtil.nullToEmpty;
import static com.chs.boot.common.util.CommonUtil.replaceSidoName;
import static com.chs.boot.common.util.MyBatisUtil.isEmpty;
import static com.chs.boot.common.util.MyBatisUtil.isNotEmpty;
import static com.chs.boot.common.util.StringUtil.getNewLineString;
import static com.chs.boot.common.util.StringUtil.getTabString;
import static com.chs.boot.common.util.StringUtil.lastIndexString;
import static com.chs.boot.common.util.StringUtil.lowerCaseFirst;
import static com.chs.boot.common.util.StringUtil.replaceLast;
import static com.chs.boot.common.util.StringUtil.upperCaseFirst;

import com.chs.boot.gerp.b2b.generate.mapper.B2bGenerateMapper;
import com.chs.boot.gerp.b2b.generate.model.SchemaColumnConditionVO;
import com.chs.boot.gerp.b2b.generate.model.SchemaColumnVO;
import com.chs.boot.gerp.b2b.generate.model.TableConstraintsVO;
import com.chs.boot.gerp.core.generate.mapper.CoreGenerateMapper;
import com.chs.boot.gerp.core.generate.model.ConvertDataTypeVO;
import com.chs.boot.gerp.core.generate.model.CoreColumnVO;
import com.chs.boot.gerp.core.generate.model.SequenceConditionVO;
import com.chs.boot.gerp.core.generate.model.SequenceVO;
import com.chs.boot.gerp.core.generate.model.TepGenControllerMethodInfoEO;
import com.chs.boot.gerp.core.generate.model.TepGenControllerUnitMethodEO;
import com.chs.boot.gerp.core.generate.model.TepGenFileInfoConditionVO;
import com.chs.boot.gerp.core.generate.model.TepGenFileInfoEO;
import com.chs.boot.gerp.core.generate.model.TepGenFileInfoVO;
import com.chs.boot.gerp.core.generate.model.TepGenMapperMethodInfoConditionVO;
import com.chs.boot.gerp.core.generate.model.TepGenMapperMethodInfoEO;
import com.chs.boot.gerp.core.generate.model.TepGenMapperMethodInfoVO;
import com.chs.boot.gerp.core.generate.model.TepGenMasterInfoEO;
import com.chs.boot.gerp.core.generate.model.TepGenModelInfoEO;
import com.chs.boot.gerp.core.generate.model.TepGenServiceMethodInfoEO;
import com.chs.boot.gerp.core.generate.model.TepGenTemplateEO;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.text.CaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerateService {

    @Autowired
    B2bGenerateMapper b2bGenerateMapper;

    @Autowired
    CoreGenerateMapper coreGenerateMapper;


    public void doMainJob(Long packageNo) {
        TepGenMasterInfoEO tepGenMasterInfoConditionEO = new TepGenMasterInfoEO();
        tepGenMasterInfoConditionEO.setPackageNo(packageNo);
        List<TepGenMasterInfoEO> mainList = coreGenerateMapper.retrieveTepGenMasterInfoListAll(
            tepGenMasterInfoConditionEO);
        if (isNotNullAndEmpty(mainList)) {
            coreGenerateMapper.clearMetaData(packageNo);
            String packageName = mainList.stream().findFirst().get().getPackageName();
            mainList.stream().forEach(tepGenMasterInfoEO -> {
                if (tepGenMasterInfoEO.getMethodType().equals("Q")) {
                    doSqlJob(tepGenMasterInfoEO.getPackageNo(), tepGenMasterInfoEO.getPackageName(),
                        tepGenMasterInfoEO.getMethodType(), tepGenMasterInfoEO.getMethodName(),
                        tepGenMasterInfoEO.getSqlStmt(), tepGenMasterInfoEO.getVoName(),
                        tepGenMasterInfoEO.getControllerYn(), tepGenMasterInfoEO.getServiceYn(),
                        tepGenMasterInfoEO.getInitYn(), tepGenMasterInfoEO.getInitOrderSeq(),
                        tepGenMasterInfoEO.getLookupType(),
                        tepGenMasterInfoEO.getControllerMethodName(),
                        tepGenMasterInfoEO.getControllerDatasetMethodSeq()
                    );
                } else if (tepGenMasterInfoEO.getMethodType().equals("T")) {
                    doTableJob(tepGenMasterInfoEO.getPackageNo(),
                        tepGenMasterInfoEO.getPackageName(),
                        tepGenMasterInfoEO.getTableName());
                } else if (tepGenMasterInfoEO.getMethodType().equals("L")) {
                    doLookupJob(tepGenMasterInfoEO.getPackageNo(),
                        tepGenMasterInfoEO.getPackageName(),
                        tepGenMasterInfoEO.getMethodType(), tepGenMasterInfoEO.getMethodName(),
                        tepGenMasterInfoEO.getSqlStmt(), tepGenMasterInfoEO.getVoName(),
                        tepGenMasterInfoEO.getControllerYn(), tepGenMasterInfoEO.getServiceYn(),
                        tepGenMasterInfoEO.getInitYn(), tepGenMasterInfoEO.getInitOrderSeq(),
                        tepGenMasterInfoEO.getLookupType(),
                        tepGenMasterInfoEO.getControllerMethodName(),
                        tepGenMasterInfoEO.getControllerDatasetMethodSeq()
                    );
                }

            });
            //sql controller method 처리
            Map<Long, String> controllerMethodMap = new HashMap<>();

            coreGenerateMapper.retrieveTepGenControllerUnitMethodListAll(
                TepGenControllerUnitMethodEO.builder()
                    .packageNo(packageNo)
                    .build()).stream().forEach(tepGenControllerUnitMethodEO ->
                controllerMethodMap.put(tepGenControllerUnitMethodEO.getPackageNo(),
                    tepGenControllerUnitMethodEO.getControllerMethodName())
            );
            if (controllerMethodMap.size() > 0) {
                controllerMethodMap.forEach((mapPackageNo, mapControllerMethodName) ->
                    insertControllerMethodForSql(mapPackageNo, packageName, mapControllerMethodName)
                );
                //insertControllerMethodForSql
            }
            makeMapperXml(packageNo, packageName);
            makeMapperJava(packageNo, packageName);
            makeServiceJava(packageNo, packageName);
            makeServiceImplJava(packageNo, packageName);
            makeControllerJava(packageNo, packageName);
        }
    }

    public void doLookupJob(Long packageNo, String packageName, String methodType,
        String methodName,
        String sqlStmt, String voName, String controllerYn, String serviceYn, String initYn,
        Long initOrderSeq, String lookupType, String controllerMethodName, Long datasetSeq) {
        if (isEmpty(methodName)) {
            methodName =
                "retrieve" + CaseUtils.toCamelCase(lookupType.toLowerCase(Locale.ROOT), true, '_'
                );
        }
//        insertSqlServiceMethod(packageNo, packageName, voName, voConditionName, methodName);
        insertLookupServiceMethod(packageNo, packageName, methodName, lookupType);
        insertControllerUnitForLookup(packageNo, packageName, controllerMethodName, methodName,
            lookupType, datasetSeq);
//        insertControllerUnitForSql(packageNo, packageName,
//            isNotEmpty(controllerMethodName) ? controllerMethodName : methodName, methodName,
//            voConditionName, voName, datasetSeq == null ? 0 : datasetSeq);
    }


    public void doSqlJob(Long packageNo, String packageName, String methodType, String methodName,
        String sqlStmt, String voName, String controllerYn, String serviceYn, String initYn,
        Long initOrderSeq, String lookupType, String controllerMethodName, Long datasetSeq) {
        //1. make VO File
        List<LinkedHashMap<String, Object>> result = b2bGenerateMapper.selectSqlStmt(sqlStmt);
        LinkedHashMap<String, Object> protoTypeMap = getMapProtoType(result);
        LinkedHashMap<String, String> resultMap = getTypeMap(protoTypeMap);
        voName = upperCaseFirst(voName);
        if (voName.length() < 3 || !voName.substring(voName.length() - 2, voName.length())
            .equals("VO")) {
            voName = voName + "VO";
        }
        makeVOFile(packageNo, packageName, voName, resultMap);
        //2. makeConditionVO
        String voConditionName = replaceLast(voName, "VO", "ConditionVO");
        makeVOFile(packageNo, packageName, voConditionName, resultMap);

        //3. insert mapper info
//        insertMapperMethodForSql(packageNo, packageName, tableName, eoName);
//        insertSqlMapperMethod(packageNo,packageName,voName,voConditionName,)
        insertMapperMethodForSql(packageNo, packageName, voName, voConditionName, sqlStmt,
            methodName, protoTypeMap);
        //3. insert service info
        insertSqlServiceMethod(packageNo, packageName, voName, voConditionName, methodName);
        //4. insert controller info
//        insertSqlServiceMethod(packageNo,packageName,voName,voConditionName,methodName);
//        insertControllerMethodForSql(packageNo, packageName, methodName, voConditionName, voName);
        insertControllerUnitForSql(packageNo, packageName,
            isNotEmpty(controllerMethodName) ? controllerMethodName : methodName, methodName,
            voConditionName, voName, datasetSeq == null ? 0 : datasetSeq);
    }

    public void doTableJob(Long packageNo, String packageName, String tableName) {
        //1 make EO File
        String eoName = makeEOFile(packageNo, packageName, tableName);
        //2 insert mapper info
        insertMapperMethodForTable(packageNo, packageName, tableName, eoName);
        //3. insert service info
        insertServiceMethodForTable(packageNo, packageName, tableName, eoName);
        //4. insert controller info
        insertControllerMethodForTable(packageNo, packageName, tableName, eoName);
//        makeMapperXml(packageNo, packageName);
//        makeMapperJava(packageNo, packageName);
//        makeServiceJava(packageNo, packageName);
//        makeServiceImplJava(packageNo, packageName);
//        makeControllerJava(packageNo, packageName);
//        getSaveMethodString(packageNo, tableName, eoName, "save");
    }

    private LinkedHashMap<String, Object> getMapProtoType(
        List<LinkedHashMap<String, Object>> maps) {
        LinkedHashMap<String, Object> returnMap = new LinkedHashMap<>();
        for (int inx = 0; inx < maps.size(); inx++) {
            LinkedHashMap<String, Object> map = maps.get(inx);
            for (String key : map.keySet()) {
                if (returnMap.get(key) == null) {
                    returnMap.put(key, map.get(key));
                } else {
                    if (returnMap.get(key) instanceof String && !(map.get(key) instanceof String)) {
                        returnMap.put(key, map.get(key));
                    }
                }
            }
        }
        return returnMap;
    }

    private LinkedHashMap<String, String> getTypeMap(LinkedHashMap<String, Object> map) {
        LinkedHashMap<String, String> returnMap = new LinkedHashMap<>();
        for (String key : map.keySet()) {
            String javaType = "";
            String columnName = "";
            if (map.get(key) != null) {
                String javaE = map.get(key).getClass().toString()
                    .substring(map.get(key).getClass().toString().lastIndexOf(".") + 1) + " ";
                if (javaE.equals("Timestamp ")) {
                    javaE = "LocalDateTime ";
                }
                javaType = map.get(key).getClass().toString()
                    .substring(map.get(key).getClass().toString().lastIndexOf(".") + 1);
                if (javaType.equals("Timestamp")) {
                    javaType = "LocalDateTime";
                }
            } else {
                javaType = "String";
            }
            columnName = CaseUtils.toCamelCase(key, false, '_');
            returnMap.put(columnName, javaType);
        }
        return returnMap;
    }


    public String makeControllerJava(Long packageNo, String packageName) {
        String controllerJavaFileName = "";
        List<TepGenControllerMethodInfoEO> tepGenControllerMethodInfoEOList = coreGenerateMapper.retrieveTepGenControllerMethodInfoListAll(
            TepGenControllerMethodInfoEO.builder().packageNo(packageNo).build());
        if (isNotNullAndEmpty(tepGenControllerMethodInfoEOList)) {
            String controllerJavaString = getTemplateSqlStmtString("ControllerJava");

            StringBuilder contentsStringBuilder = new StringBuilder("");
            AtomicReference<String> controllerPackage = new AtomicReference<>("");
            AtomicReference<String> controllerClassName = new AtomicReference<>("");
            tepGenControllerMethodInfoEOList.stream().forEach(tepGenControllerMethodInfoEO -> {
                contentsStringBuilder.append(getNewLineString());
//                    contentsStringBuilder.append(getTabString(1));

                contentsStringBuilder.append(tepGenControllerMethodInfoEO.getMethodContents());
                controllerPackage.set(tepGenControllerMethodInfoEO.getControllerPackageName());
                controllerClassName.set(tepGenControllerMethodInfoEO.getControllerClassName());
            });
            String controllerFullPath = controllerPackage.get();
            String importModelString = getImportModelString(packageNo);
            String importServiceString = getImportServiceString(packageNo);
            String serviceClassName = coreGenerateMapper.retrieveTepGenServiceMethodInfo(
                    TepGenServiceMethodInfoEO.builder().packageNo(packageNo).build()).stream()
                .findFirst().get().getServiceClassName();
            String serviceInstantName = lowerCaseFirst(serviceClassName);
            StringBuilder controllerContents = new StringBuilder("");

            coreGenerateMapper.retrieveTepGenControllerMethodInfoListAll(
                    TepGenControllerMethodInfoEO.builder().packageNo(packageNo).build()).stream()
                .forEach(tepGenControllerMethodInfoEO -> controllerContents.append(
                    tepGenControllerMethodInfoEO.getMethodContents()));

            controllerJavaString = controllerJavaString.replace("@controllerFullPath",
                controllerFullPath);
            controllerJavaString = controllerJavaString.replace("@importModelString",
                importModelString);
            controllerJavaString = controllerJavaString.replace("@importServiceString",

                importServiceString);
            controllerJavaString = controllerJavaString.replace("@controllerContents",
                contentsStringBuilder.toString());
            controllerJavaString = controllerJavaString.replace("@serviceClassName",
                serviceClassName);
            controllerJavaString = controllerJavaString.replace("@serviceInstantName",
                serviceInstantName);
            controllerJavaString = controllerJavaString.replace("@controllerClassName",
                controllerClassName.get());

            createFile(packageName, controllerClassName.get() + ".java", controllerJavaString);
            //file 생성정보
            coreGenerateMapper.insertMulti(List.of(
                TepGenFileInfoEO.builder().fileName(controllerClassName.get() + ".java")
                    .packageName(packageName).packageNo(packageNo).fileType("controller")
                    .fileContents(controllerJavaString).build()));
        }
        return controllerJavaFileName;
    }

    private void insertControllerMethodForSql(Long packageNo, String packageName,
        String controllerMethodName) {
        //insert method 만들기
        String controllerPackageName = packageName + ".controller";
        String methodAccessor = "public";
        String controllerClassName =
            upperCaseFirst(lastIndexString(packageName, ".")) + "Controller";
        ;
        String methodAnnotationName = null;
        String methodReturnClassName = "ResponseModel";
        String methodParamClassName = "@RequestBody RequestModel";
        String methodParamInstantName = "requestModel";
        String methodContents = getControllerSqlString(packageNo, packageName,
            controllerMethodName);

        coreGenerateMapper.insertTepGenControllerMethodInfoList(List.of(
            TepGenControllerMethodInfoEO.builder().controllerPackageName(controllerPackageName)
                .packageNo(packageNo).controllerClassName(controllerClassName)
                .methodAnnotationName(methodAnnotationName).methodAccessor(methodAccessor)
                .methodReturnClassName(methodReturnClassName).methodName(controllerMethodName)
                .methodParamClassName(methodParamClassName)
                .methodParamInstantName(methodParamInstantName).methodContents(methodContents)
                .build()));
    }

    private void insertControllerUnitForLookup(Long packageNo, String packageName,
        String controllerMethodName, String methodName,
        String lookupType, Long datasetSeq) {
        //insert method 만들기
        String controllerPackageName = packageName + ".controller";
        String methodAccessor = "public";
        String controllerClassName =
            upperCaseFirst(lastIndexString(packageName, ".")) + "Controller";

        String methodAnnotationName = null;
        String methodReturnClassName = "ResponseModel";
        String methodParamClassName = "@RequestBody RequestModel";
        String methodParamInstantName = "requestModel";
        String methodContents = getControllerUnitLookupString(packageName, methodName, lookupType,
            datasetSeq);

        coreGenerateMapper.insertTepGenControllerUnitMethodList(List.of(
            TepGenControllerUnitMethodEO.builder().controllerPackageName(controllerPackageName)
                .packageNo(packageNo).controllerClassName(controllerClassName)
                .methodAnnotationName(methodAnnotationName).methodAccessor(methodAccessor)
                .controllerMethodName(controllerMethodName)
                .methodReturnClassName(methodReturnClassName).methodName(methodName)
                .methodParamClassName(methodParamClassName)
                .methodParamInstantName(methodParamInstantName).methodContents(methodContents)
                .datasetSeq(datasetSeq)
                .build()));
    }

    private String getControllerUnitLookupString(String packageName, String methodName,
        String lookupType, Long datasetSeq) {
        String returnString;
        String templateString = getTemplateSqlStmtString("controllerJavaRetrieveUnit");

        String urlPath = lastIndexString(packageName, ".");
//        String conditionVOName = CaseUtils.toCamelCase(lookupType.toLowerCase(Locale.ROOT),true,'_');
        String conditionVOName = "LookupValuesVO";
        String voName = "LookupValuesVO";
        String conditionVOInstantName =
            CaseUtils.toCamelCase(lookupType.toLowerCase(Locale.ROOT), false, '_')
                + "LookupConditionVO";
        String voInstantName = CaseUtils.toCamelCase(lookupType.toLowerCase(Locale.ROOT), false, '_')
            + "LookupVO";

//        String datasetName = CaseUtils.toCamelCase(tableName, false, '_') + "DatasetName";
        String datasetName =
            CaseUtils.toCamelCase(lookupType.toLowerCase(Locale.ROOT), false, '_') + "DatasetName";

        String baseURL = BOF.getLocalBaseURL() + BOF.getLocalUrlContext();

        returnString = templateString;
        returnString = returnString.replace("@urlPath", urlPath);
        returnString = returnString.replace("@methodName", methodName);
        returnString = returnString.replace("@conditionVOName", conditionVOName);
        returnString = returnString.replace("@conditionVOInstantName", conditionVOInstantName);

        returnString = returnString.replace("@voName", voName);
        returnString = returnString.replace("@voInstantName", voInstantName);

        returnString = returnString.replace("@datasetName", datasetName);

        returnString = returnString.replace("@aURL", baseURL);
        returnString = returnString.replace("@datasetSeq", datasetSeq.toString());
        if (datasetSeq == 0) {
            returnString = returnString.replace("//@0", "");
            returnString = returnString.replace("//@1", "//");
        } else {
            returnString = returnString.replace("//@0", "//");
            returnString = returnString.replace("//@1", "");
        }
//        String simpleDataSetName = datasetName + "Dataset";

        return returnString;
    }

    private void insertControllerUnitForSql(Long packageNo, String packageName,
        String controllerMethodName, String methodName,
        String conditionVOName, String voName, Long datasetSeq) {
        //insert method 만들기
        String controllerPackageName = packageName + ".controller";
        String methodAccessor = "public";
        String controllerClassName =
            upperCaseFirst(lastIndexString(packageName, ".")) + "Controller";

        String methodAnnotationName = null;
        String methodReturnClassName = "ResponseModel";
        String methodParamClassName = "@RequestBody RequestModel";
        String methodParamInstantName = "requestModel";
        String methodContents = getControllerUnitSqlString(packageName, methodName, conditionVOName,
            voName, datasetSeq);

        coreGenerateMapper.insertTepGenControllerUnitMethodList(List.of(
            TepGenControllerUnitMethodEO.builder().controllerPackageName(controllerPackageName)
                .packageNo(packageNo).controllerClassName(controllerClassName)
                .methodAnnotationName(methodAnnotationName).methodAccessor(methodAccessor)
                .controllerMethodName(controllerMethodName)
                .methodReturnClassName(methodReturnClassName).methodName(methodName)
                .methodParamClassName(methodParamClassName)
                .methodParamInstantName(methodParamInstantName).methodContents(methodContents)
                .datasetSeq(datasetSeq)
                .build()));
    }

    private String getControllerUnitSqlString(String packageName, String methodName,
        String conditionVOName, String voName, Long datasetSeq) {
        String returnString;
        String templateString = getTemplateSqlStmtString("controllerJavaRetrieveUnit");

        String urlPath = lastIndexString(packageName, ".");
        String conditionVOInstantName = lowerCaseFirst(conditionVOName);
        String voInstantName = lowerCaseFirst(voName);

//        String datasetName = CaseUtils.toCamelCase(tableName, false, '_') + "DatasetName";
        String datasetName = voInstantName + "DatasetName";

        String baseURL = BOF.getLocalBaseURL() + BOF.getLocalUrlContext();

        returnString = templateString;
        returnString = returnString.replace("@urlPath", urlPath);
        returnString = returnString.replace("@methodName", methodName);
        returnString = returnString.replace("@conditionVOName", conditionVOName);
        returnString = returnString.replace("@conditionVOInstantName", conditionVOInstantName);

        returnString = returnString.replace("@voName", voName);
        returnString = returnString.replace("@voInstantName", voInstantName);

        returnString = returnString.replace("@datasetName", datasetName);

        returnString = returnString.replace("@aURL", baseURL);
        returnString = returnString.replace("@datasetSeq", datasetSeq.toString());
        if (datasetSeq == 0) {
            returnString = returnString.replace("//@0", "");
            returnString = returnString.replace("//@1", "//");
        } else {
            returnString = returnString.replace("//@0", "//");
            returnString = returnString.replace("//@1", "");
        }
//        String simpleDataSetName = datasetName + "Dataset";

        return returnString;
    }

    private String getControllerSqlString(Long packageNo, String packageName,
        String controllerMethodName) {
        String returnString;
        String templateString = getTemplateSqlStmtString("ControllerJavaRetrieveMethod");

        String urlPath = lastIndexString(packageName, ".");

//        String datasetName = CaseUtils.toCamelCase(tableName, false, '_') + "DatasetName";

        String baseURL = BOF.getLocalBaseURL() + BOF.getLocalUrlContext();

        returnString = templateString;
        returnString = returnString.replace("@urlPath", urlPath);
        returnString = returnString.replace("@controllerMethodName", controllerMethodName);

        StringBuilder conditionUnitString = new StringBuilder("");
        coreGenerateMapper.retrieveTepGenControllerUnitMethodListAll(
            TepGenControllerUnitMethodEO.builder()
                .packageNo(packageNo).controllerMethodName(controllerMethodName)
                .build()).stream().forEach(tepGenControllerUnitMethodEO -> {
            conditionUnitString.append(getNewLineString()).append(getTabString(2)
            ).append(tepGenControllerUnitMethodEO.getMethodContents());
        });

        returnString = returnString.replace("@aURL", baseURL);
        returnString = returnString.replace("@conditionUnitString", conditionUnitString);
//        String demoContents = getDemoContents(tableName);
//        returnString = returnString.replace("@simpleDataSetName", simpleDataSetName);
//        returnString = returnString.replace("@demoContents", demoContents);

//        returnString = returnString.replace("@serviceInstantName", serviceInstantName);
        return returnString;
    }

    //
    private void insertControllerMethodForTable(Long packageNo, String packageName,
        String tableName, String eoName) {
        //insert method 만들기
        String controllerPackageName = packageName + ".controller";
        String methodAccessor = "public";
        String controllerClassName =
            upperCaseFirst(lastIndexString(packageName, ".")) + "Controller";
        ;
        String methodAnnotationName = "@Transactional";
        String methodReturnClassName = "ResponseModel";
        String methodParamClassName = "@RequestBody RequestModel";
        String methodParamInstantName = "requestModel";
        String methodName =
            "save" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_') + "List";
        String methodContents = getControllerSaveString(packageName, tableName, eoName, methodName);

        coreGenerateMapper.insertTepGenControllerMethodInfoList(List.of(
            TepGenControllerMethodInfoEO.builder().controllerPackageName(controllerPackageName)
                .packageNo(packageNo).controllerClassName(controllerClassName)
                .methodAnnotationName(methodAnnotationName).methodAccessor(methodAccessor)
                .methodReturnClassName(methodReturnClassName).methodName(methodName)
                .methodParamClassName(methodParamClassName)
                .methodParamInstantName(methodParamInstantName).methodContents(methodContents)
                .tableName(tableName).build()));
    }

    private String getImportServiceString(Long packageNo) {
        TepGenServiceMethodInfoEO tepGenServiceMethodInfoEO = coreGenerateMapper.retrieveTepGenServiceMethodInfo(
                TepGenServiceMethodInfoEO.builder().packageNo(packageNo).build()).stream().findFirst()
            .get();
        return "import " + tepGenServiceMethodInfoEO.getServicePackageName() + "."
            + tepGenServiceMethodInfoEO.getServiceClassName() + ";";
    }

    private String getControllerSaveString(String packageName, String tableName, String eoName,
        String methodName) {
        String returnString = "";
        String templateString = getTemplateSqlStmtString("ControllerJavaSaveMethod");

        String urlPath = lastIndexString(packageName, ".");
        String eoInstantName = lowerCaseFirst(eoName);
//        String datasetName = CaseUtils.toCamelCase(tableName, false, '_') + "DatasetName";
        String datasetName =
            CaseUtils.toCamelCase(tableName.substring(tableName.indexOf("_") + 1), false, '_')
                + "DatasetName";
        ;
        String baseURL = BOF.getLocalBaseURL() + BOF.getLocalUrlContext();

        returnString = templateString;
        returnString = returnString.replace("@methodName", methodName);
        returnString = returnString.replace("@eoName", eoName);
        returnString = returnString.replace("@urlPath", urlPath);
        returnString = returnString.replace("@eoInstantName", eoInstantName);
        returnString = returnString.replace("@datasetName", datasetName);
        returnString = returnString.replace("@aURL", baseURL);

        String simpleDataSetName =
            CaseUtils.toCamelCase(tableName.substring(tableName.indexOf("_") + 1), false, '_')
                + "Dataset";
        String demoContents = getDemoContents(tableName);
        returnString = returnString.replace("@simpleDataSetName", simpleDataSetName);
        returnString = returnString.replace("@demoContents", demoContents);

//        returnString = returnString.replace("@serviceInstantName", serviceInstantName);
        return returnString;
    }

    public String getDemoContents(String tableName) {
        StringBuilder returnString = new StringBuilder("");
        SchemaColumnConditionVO schemaColumnConditionVO = new SchemaColumnConditionVO();
        schemaColumnConditionVO.setTableName(tableName);
        List<SchemaColumnVO> schemaColumnVOList = b2bGenerateMapper.retrieveColumnSchema(
            schemaColumnConditionVO);
        if (isNotNullAndEmpty(schemaColumnVOList)) {
            AtomicInteger inx = new AtomicInteger();

            List<ConvertDataTypeVO> dataTypeVOList = coreGenerateMapper.getConvertDataType();
            Map<String, String> dataTypeMap = new HashMap<>();
            if (getListSize(dataTypeVOList) > 0) {
                dataTypeVOList.forEach(convertDataTypeVO -> {
                    dataTypeMap.put(convertDataTypeVO.getMariaDataType(),
                        convertDataTypeVO.getJavaDataType());
                });
            }
            returnString.append(getNewLineString());
            returnString.append(getTabString(11));
            returnString.append("\"cudsKey\":\"C\"");
            returnString.append(getNewLineString());
            returnString.append(getTabString(11));
            returnString.append("\",rowKey\":\"").append(UUID.randomUUID().toString()).append("\"");

            inx.set(2);
            schemaColumnVOList.stream().forEach(schemaColumnVO -> {
                inx.getAndIncrement();
                if (inx.get() > 1) {
                    returnString.append(getNewLineString());
                    returnString.append(getTabString(11));
                    returnString.append(",");
                }
                returnString.append("\"")
                    .append(CaseUtils.toCamelCase(schemaColumnVO.getColumnName(), false, '_'))
                    .append("\":");
                String javaDataType = dataTypeMap.get(schemaColumnVO.getDataType());
                switch (nullToEmpty(javaDataType)) {
                    case "Boolean":
                        returnString.append("true");
                        break;
                    case "Integer":
                    case "Long":
                        returnString.append("1");
                        break;
                    case "Float":
                    case "Double":
                    case "BigDecimal":
                        returnString.append("1.0");
                        break;
                    case "Date":
                        returnString.append("\"2021-07-09T15:12:13\"");
                        break;
                    case "LocalDateTime":
                        returnString.append("\"2021-08-09T15:12:13\"");
                        break;
                    case "String":
                        returnString.append("\"A\"");
                        break;
                    default:
                        returnString.append("null");
                        break;
                }

            });
        }
        return returnString.toString();
    }

    public Long getNewPackageNo() {
        return getNextVal(SequenceConditionVO.builder().sequenceName("package_no_seq").build());
    }


    public Long getNextVal(SequenceConditionVO sequenceConditionVO) {
        List<SequenceVO> sequenceVOList = coreGenerateMapper.getNextVal(sequenceConditionVO);

        return getListSize(sequenceVOList) > 0 ? sequenceVOList.get(0).getNextVal() : -1L;
    }

    public void insertMapperMethodForSql(Long packageNo, String packageName, String voName,
        String conditionVOName, String sqlStmt, String methodName,
        LinkedHashMap<String, Object> resultMap) {

        String mapperPackageName = packageName + ".mapper";
        String mapperXmlName = upperCaseFirst(lastIndexString(packageName, ".")) + "Mapper_SQL.xml";
        String mapperClassName = upperCaseFirst(lastIndexString(packageName, ".")) + "Mapper";
        String methodAnnotationName = "";
        String methodReturnClassName = "List<" + voName + ">";
        String methodParamClassName = conditionVOName;
        String methodParamInstantName = lowerCaseFirst(conditionVOName);

        //select all 만들기
        insertSqlMapperMethod(packageNo, packageName, voName, conditionVOName, mapperPackageName,
            mapperXmlName, mapperClassName, methodAnnotationName, methodParamClassName,
            methodParamInstantName, methodReturnClassName, methodName, resultMap, sqlStmt);
        //validation

    }

    private String insertMapperMethodForTable(Long packageNo, String packageName, String tableName,
        String eoName) {
        String mapperXmlFileName = "";
        String mapperPackageName = packageName + ".mapper";
        String mapperXmlName = upperCaseFirst(lastIndexString(packageName, ".")) + "Mapper_SQL.xml";
        String mapperClassName = upperCaseFirst(lastIndexString(packageName, ".")) + "Mapper";
        String methodAnnotationName = "@Transactional";
        String methodParamClassName = "List<" + eoName + ">";
        String methodParamInstantName = lowerCaseFirst(eoName) + "List";
        //insert method 만들기
        insertInsertMapperMethod(packageNo, packageName, tableName, eoName, mapperPackageName,
            mapperXmlName, mapperClassName, methodAnnotationName, methodParamClassName,
            methodParamInstantName);
        //update method 만들기
        insertUpdateMapperMethod(packageNo, packageName, tableName, eoName, mapperPackageName,
            mapperXmlName, mapperClassName, methodAnnotationName, methodParamClassName,
            methodParamInstantName);
        //delete method 만들기
        insertDeleteMapperMethod(packageNo, packageName, tableName, eoName, mapperPackageName,
            mapperXmlName, mapperClassName, methodAnnotationName, methodParamClassName,
            methodParamInstantName);
        //select by pk 만들기
        insertSelectByPkMapperMethod(packageNo, packageName, tableName, eoName, mapperPackageName,
            mapperXmlName, mapperClassName, methodAnnotationName, methodParamClassName,
            methodParamInstantName);
        //select all 만들기
        insertSelectListMapperMethod(packageNo, packageName, tableName, eoName, mapperPackageName,
            mapperXmlName, mapperClassName, methodAnnotationName, eoName, lowerCaseFirst(eoName),
            methodParamClassName);
        //validation

        return mapperXmlFileName;
    }

    private String insertServiceMethodForTable(Long packageNo, String packageName, String tableName,
        String eoName) {
        String mapperXmlFileName = "";
        String servicePackageName = packageName + ".service";
        String serviceClassName = upperCaseFirst(lastIndexString(packageName, ".")) + "Service";
        String methodAnnotationName = "@Transactional";
        String methodParamInstantName = lowerCaseFirst(eoName) + "List";
        //save method 만들기
        insertSaveServiceMethod(packageNo, servicePackageName, tableName, eoName, serviceClassName,
            methodAnnotationName, "public", methodParamInstantName);
        //validation
        insertValidationServiceMethod(packageNo, servicePackageName, tableName, eoName,
            serviceClassName, methodAnnotationName, "private", methodParamInstantName);
//        getValidationString(packageNo,tableName,eoName,"AA",eoName,methodParamInstantName);

        return mapperXmlFileName;
    }

    private void insertValidationServiceMethod(Long packageNo, String servicePackageName,
        String tableName, String eoName, String serviceClassName, String methodAnnotationName,
        String method_accessor, String methodParamInstantName) {
        String methodName = "validation" + eoName;
        ;
        String methodContents = getValidationString(packageNo, tableName, eoName, methodName,
            eoName, methodParamInstantName);
        coreGenerateMapper.insertMultiTepGenServiceMethodInfo(List.of(
            TepGenServiceMethodInfoEO.builder().packageNo(packageNo)
                .servicePackageName(servicePackageName).serviceClassName(serviceClassName)
                .methodAccessor(method_accessor).methodAnnotationName(methodAnnotationName)
                .methodReturnClassName("Boolean").methodName(methodName)
                .methodParamClassName(eoName).methodParamInstantName(methodParamInstantName)
                .methodContents(methodContents).tableName(tableName).build()));
    }

    private void insertSaveServiceMethod(Long packageNo, String servicePackageName,
        String tableName, String eoName, String serviceClassName, String methodAnnotationName,
        String method_accessor, String methodParamInstantName) {
        String methodName =
            "save" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_') + "List";
        String methodContents = getSaveMethodString(packageNo, tableName, eoName, methodName,
            eoName, methodParamInstantName);
        String methodParamClassName = "List<" + eoName + ">";
        coreGenerateMapper.insertMultiTepGenServiceMethodInfo(List.of(
            TepGenServiceMethodInfoEO.builder().packageNo(packageNo)
                .servicePackageName(servicePackageName).serviceClassName(serviceClassName)
                .methodAccessor(method_accessor).methodAnnotationName(methodAnnotationName)
                .methodReturnClassName("void").methodName(methodName)
                .methodParamClassName(methodParamClassName)
                .methodParamInstantName(methodParamInstantName).methodContents(methodContents)
                .tableName(tableName).addDatasetParam("Y").build()));
    }

    private void insertLookupServiceMethod(Long packageNo, String packageName, String methodName,
        String lookupType) {
        String servicePackageName = packageName + ".service";
        String serviceClassName = upperCaseFirst(lastIndexString(packageName, ".")) + "Service";
        String method_accessor = "public";
        String methodReturnClassName = "List<LookupValuesVO>";
        String methodParamClassName = "LookupValuesVO";
        String methodParamInstantName = "conditionVO";
        String methodContents = getLookupMethodString(packageNo, methodName, lookupType);

        coreGenerateMapper.insertMultiTepGenServiceMethodInfo(List.of(
            TepGenServiceMethodInfoEO.builder().packageNo(packageNo)
                .servicePackageName(servicePackageName).serviceClassName(serviceClassName)
                .methodAccessor(method_accessor).methodReturnClassName(methodReturnClassName)
                .methodName(methodName).methodParamClassName(methodParamClassName)
                .methodParamInstantName(methodParamInstantName).methodContents(methodContents)
                .addDatasetParam("Y").build()));
    }

    private String getLookupMethodString(Long packageNo, String methodName,
        String lookupType) {
        String returnString = "";
        returnString = getTemplateSqlStmtString("serviceJavaLookup");
        returnString = returnString.replace("@methodName", methodName);
        returnString = returnString.replace("@lookupType", lookupType);
        return returnString;
    }

    private void insertSqlServiceMethod(Long packageNo, String packageName, String voName,
        String conditionVOName, String methodName) {
        String servicePackageName = packageName + ".service";
        String serviceClassName = upperCaseFirst(lastIndexString(packageName, ".")) + "Service";
        String method_accessor = "public";
        String methodReturnClassName = "List<" + voName + ">";
        String methodParamClassName = conditionVOName;
        String methodParamInstantName = lowerCaseFirst(methodParamClassName);
        String methodContents = getSqlMethodString(packageNo, methodName, methodParamClassName,
            methodParamInstantName, methodReturnClassName);

        coreGenerateMapper.insertMultiTepGenServiceMethodInfo(List.of(
            TepGenServiceMethodInfoEO.builder().packageNo(packageNo)
                .servicePackageName(servicePackageName).serviceClassName(serviceClassName)
                .methodAccessor(method_accessor).methodReturnClassName(methodReturnClassName)
                .methodName(methodName).methodParamClassName(methodParamClassName)
                .methodParamInstantName(methodParamInstantName).methodContents(methodContents)
                .addDatasetParam("Y").build()));
    }

    private String getSqlMethodString(Long packageNo, String methodName,
        String methodParamClassName, String methodParamInstantName, String methodReturnClassName) {
        String returnString = "";
        String mapperClassName = getMapperClassName(packageNo);
        String mapperInstantName = lowerCaseFirst(mapperClassName);
        returnString = getTemplateSqlStmtString("serviceJavaSql");
        returnString = returnString.replace("@methodReturnClassName", methodReturnClassName);
        returnString = returnString.replace("@methodName", methodName);
        returnString = returnString.replace("@methodParamClassName", methodParamClassName);
        returnString = returnString.replace("@methodParamInstantName", methodParamInstantName);
        returnString = returnString.replace("@mapperInstantName", mapperInstantName);

        return returnString;
    }

    private void insertSelectListMapperMethod(Long packageNo, String packageName, String tableName,
        String eoName, String mapperPackageName, String mapperXmlName, String mapperClassName,
        String methodAnnotationName, String methodParamClassName, String methodParamInstantName,
        String methodReturnClassName) {
        //insert method 만들기
        String methodName =
            "retrieve" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_')
                + "ListAll";
        String xmlMethodType = "select";
        String sqlStmt = getSelectListString(packageName, tableName, eoName, methodName);

        coreGenerateMapper.insertMultiTepGenMapperMethodInfo(List.of(
            TepGenMapperMethodInfoEO.builder().mapperPackageName(mapperPackageName)
                .packageNo(packageNo).mapperXmlName(mapperXmlName).mapperClassName(mapperClassName)
                .methodAnnotationName(methodAnnotationName).methodName(methodName)
                .methodParamClassName(methodParamClassName)
                .methodParamInstantName(methodParamInstantName).xmlMethodType(xmlMethodType)
                .tableName(tableName).methodReturnClassName(methodReturnClassName).sqlStmt(sqlStmt)
                .build()));
    }

    private void insertSqlMapperMethod(Long packageNo, String packageName, String voName,
        String conditionVOName, String mapperPackageName, String mapperXmlName,
        String mapperClassName, String methodAnnotationName, String methodParamClassName,
        String methodParamInstantName, String methodReturnClassName, String methodName,
        LinkedHashMap<String, Object> resultMap, String queryString) {

        String xmlMethodType = "select";
        String sqlStmt = getSqlListString(packageName, voName, conditionVOName, methodName,
            resultMap, queryString);

        coreGenerateMapper.insertMultiTepGenMapperMethodInfo(List.of(
            TepGenMapperMethodInfoEO.builder().mapperPackageName(mapperPackageName)
                .packageNo(packageNo).mapperXmlName(mapperXmlName).mapperClassName(mapperClassName)
                .methodAnnotationName(methodAnnotationName).methodName(methodName)
                .methodParamClassName(methodParamClassName)
                .methodParamInstantName(methodParamInstantName).xmlMethodType(xmlMethodType)
                .methodReturnClassName(methodReturnClassName).sqlStmt(sqlStmt).build()));
    }

    private String getSqlListString(String packageName, String voName, String conditionVOName,
        String methodName, LinkedHashMap<String, Object> resultMap, String sqlStmt) {
        String returnString = "";
        String templateString = getTemplateSqlStmtString("MapperXmlSql");
        sqlStmt = sqlStmt.replaceFirst("(?i)select", "SELECT uuid() as row_key,");

//        String methodName =
//            "insertMulti" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_');
        //LG CNS Co., Ltd.~  5000 User License
        //GIJWD-MQIJY-OLQWY-KKEMR-PCQMK-KAIKU-NQONU-TIJMS
        String conditionVOFullPathName = packageName + "." + "model." + conditionVOName;
        String resultVOFullPathName = packageName + "." + "model." + voName;

        StringBuilder whereString = new StringBuilder("");
        if (isNotNullAndEmpty(resultMap)) {
            final int[] inx = {0};
            final int[] whereInx = {0};

            resultMap.forEach((colName, jType) -> {

                inx[0] = inx[0] + 1;
                if (inx[0] > 1) {
                    whereString.append(getNewLineString());
                }

                whereString.append(getTabString(3));
                String whereTemplate = getTemplateSqlStmtString("MapperXmlWhere");
                whereTemplate = whereTemplate.replace("@columnName",
                    colName.toLowerCase(Locale.ROOT));
                whereTemplate = whereTemplate.replace("@memberName",
                    CaseUtils.toCamelCase(colName.toLowerCase(Locale.ROOT), false, '_'));
                whereString.append(whereTemplate);

            });

        }
        returnString = templateString;
        returnString = returnString.replace("@methodName", methodName);
        returnString = returnString.replace("@conditionVOFullPathName", conditionVOFullPathName);
        returnString = returnString.replace("@resultVOFullPathName", resultVOFullPathName);
        returnString = returnString.replace("@sqlStmt", getTabString(3) + sqlStmt);
        returnString = returnString.replace("@whereStmt", whereString.toString());

        return returnString;
    }


    private String getSelectListString(String packageName, String tableName, String eoName,
        String methodName) {
        String returnString = "";
        String templateString = getTemplateSqlStmtString("MapperXmlSelect");
//        String methodName =
//            "insertMulti" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_');
        //LG CNS Co., Ltd.~  5000 User License
        //GIJWD-MQIJY-OLQWY-KKEMR-PCQMK-KAIKU-NQONU-TIJMS
        String conditionVOFullPathName = packageName + "." + "model." + eoName;
        String resultVOFullPathName = packageName + "." + "model." + eoName;

        SchemaColumnConditionVO schemaColumnConditionVO = new SchemaColumnConditionVO();
        schemaColumnConditionVO.setTableName(tableName);

        List<SchemaColumnVO> schemaColumnVOList = retrieveColumnSchema(schemaColumnConditionVO);
        StringBuilder whereString = new StringBuilder("");
        StringBuilder columnName = new StringBuilder("");
        if (isNotNullAndEmpty(schemaColumnVOList)) {
            final int[] inx = {0};
            final int[] whereInx = {0};

            schemaColumnVOList.forEach(schemaColumnVO -> {

                inx[0] = inx[0] + 1;
                if (inx[0] > 1) {
                    columnName.append(getNewLineString());
                    whereString.append(getNewLineString());
                }

                columnName.append(getTabString(3));
                columnName.append(",");
                columnName.append(schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT));

                whereString.append(getTabString(3));
                String whereTemplate = getTemplateSqlStmtString("MapperXmlWhere");
                whereTemplate = whereTemplate.replace("@columnName",
                    schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT));
                whereTemplate = whereTemplate.replace("@memberName",
                    CaseUtils.toCamelCase(schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT),
                        false, '_'));
                whereString.append(whereTemplate);

            });

        }
        returnString = templateString;
        returnString = returnString.replace("@methodName", methodName);
        returnString = returnString.replace("@conditionVOFullPathName", conditionVOFullPathName);
        returnString = returnString.replace("@resultVOFullPathName", resultVOFullPathName);
        returnString = returnString.replace("@columnName", columnName.toString());
        returnString = returnString.replace("@tableName", tableName);
        returnString = returnString.replace("@whereString", whereString.toString());

        return returnString;
    }

    private void insertSelectByPkMapperMethod(Long packageNo, String packageName, String tableName,
        String eoName, String mapperPackageName, String mapperXmlName, String mapperClassName,
        String methodAnnotationName, String methodParamClassName, String methodParamInstantName) {
        //insert method 만들기
        String methodName =
            "retrieve" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_')
                + "ListByPk";
        String xmlMethodType = "select";
        String sqlStmt = getSelectByPkString(packageName, tableName, eoName, methodName);

        coreGenerateMapper.insertMultiTepGenMapperMethodInfo(List.of(
            TepGenMapperMethodInfoEO.builder().mapperPackageName(mapperPackageName)
                .packageNo(packageNo).mapperXmlName(mapperXmlName).mapperClassName(mapperClassName)
                .methodAnnotationName(methodAnnotationName).methodName(methodName)
                .methodParamClassName(methodParamClassName)
                .methodParamInstantName(methodParamInstantName).xmlMethodType(xmlMethodType)
                .tableName(tableName).methodReturnClassName(methodParamClassName).sqlStmt(sqlStmt)
                .build()));
    }

    private String getSelectByPkString(String packageName, String tableName, String eoName,
        String methodName) {
        String returnString = "";
        String templateString = getTemplateSqlStmtString("MapperXmlSelectByPK");
//        String methodName =
//            "insertMulti" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_');
        //LG CNS Co., Ltd.~  5000 User License
        //GIJWD-MQIJY-OLQWY-KKEMR-PCQMK-KAIKU-NQONU-TIJMS
        String eoFullPathName = packageName + "." + "model." + eoName;

        SchemaColumnConditionVO schemaColumnConditionVO = new SchemaColumnConditionVO();
        schemaColumnConditionVO.setTableName(tableName);

        List<SchemaColumnVO> schemaColumnVOList = retrieveColumnSchema(schemaColumnConditionVO);
        StringBuilder whereString = new StringBuilder("");
        StringBuilder columnName = new StringBuilder("");
        if (isNotNullAndEmpty(schemaColumnVOList)) {
            final int[] inx = {0};
            final int[] whereInx = {0};
            whereString.append(getTabString(3));
            whereString.append("(");
            schemaColumnVOList.forEach(schemaColumnVO -> {

                inx[0] = inx[0] + 1;
                if (inx[0] > 1) {
                    columnName.append(getNewLineString());
                    columnName.append(getTabString(3));
                    columnName.append(",");

                } else {
                    columnName.append(getTabString(3));
                }
                columnName.append(schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT));

                if (nullToEmpty(schemaColumnVO.getColumnKey()).equals("PRI")) {
                    whereString.append(getNewLineString());
                    whereString.append(getTabString(3));
                    whereInx[0] = whereInx[0] + 1;
                    if (whereInx[0] > 1) {
                        whereString.append("AND ");
                    }

                    whereString.append(schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT));
                    whereString.append(getTabString(1));
                    whereString.append("=");
                    whereString.append(getTabString(1));
                    whereString.append("#{item.");
                    whereString.append(CaseUtils.toCamelCase(
                        schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT), false, '_'));
                    whereString.append("}");
                }
            });
            whereString.append(getNewLineString());
            whereString.append(getTabString(3));
            whereString.append(")");
        }
        returnString = templateString;
        returnString = returnString.replace("@methodName", methodName);
        returnString = returnString.replace("@eoFullPathName", eoFullPathName);
        returnString = returnString.replace("@tableName", tableName);
        returnString = returnString.replace("@whereString", whereString.toString());
        returnString = returnString.replace("@columnName", columnName.toString());

        return returnString;
    }

    private void insertDeleteMapperMethod(Long packageNo, String packageName, String tableName,
        String eoName, String mapperPackageName, String mapperXmlName, String mapperClassName,
        String methodAnnotationName, String methodParamClassName, String methodParamInstantName) {
        //insert method 만들기
        String methodName =
            "delete" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_')
                + "List";
        String xmlMethodType = "delete";
        String sqlStmt = getDeleteString(packageName, tableName, eoName, methodName);

        coreGenerateMapper.insertMultiTepGenMapperMethodInfo(List.of(
            TepGenMapperMethodInfoEO.builder().mapperPackageName(mapperPackageName)
                .packageNo(packageNo).mapperXmlName(mapperXmlName).mapperClassName(mapperClassName)
                .methodAnnotationName(methodAnnotationName).methodName(methodName)
                .methodParamClassName(methodParamClassName)
                .methodParamInstantName(methodParamInstantName).xmlMethodType(xmlMethodType)
                .tableName(tableName).sqlStmt(sqlStmt).build()));
    }


    private String getDeleteString(String packageName, String tableName, String eoName,
        String methodName) {
        String returnString = "";
        String templateString = getTemplateSqlStmtString("MapperXmlDelete");
//        String methodName =
//            "insertMulti" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_');
        //LG CNS Co., Ltd.~  5000 User License
        //GIJWD-MQIJY-OLQWY-KKEMR-PCQMK-KAIKU-NQONU-TIJMS
        String eoFullPathName = packageName + "." + "model." + eoName;

        SchemaColumnConditionVO schemaColumnConditionVO = new SchemaColumnConditionVO();
        schemaColumnConditionVO.setTableName(tableName);

        List<SchemaColumnVO> schemaColumnVOList = retrieveColumnSchema(schemaColumnConditionVO);
        StringBuilder whereString = new StringBuilder("");
        if (isNotNullAndEmpty(schemaColumnVOList)) {
            final int[] inx = {0};
            whereString.append(getTabString(3));
            whereString.append("(");
            schemaColumnVOList.forEach(schemaColumnVO -> {

                if (nullToEmpty(schemaColumnVO.getColumnKey()).equals("PRI")) {
                    whereString.append(getNewLineString());
                    whereString.append(getTabString(3));
                    inx[0] = inx[0] + 1;
                    if (inx[0] > 1) {
                        whereString.append("AND ");
                    }

                    whereString.append(schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT));
                    whereString.append(getTabString(1));
                    whereString.append("=");
                    whereString.append(getTabString(1));
                    whereString.append("#{item.");
                    whereString.append(CaseUtils.toCamelCase(
                        schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT), false, '_'));
                    whereString.append("}");
                }
            });
            whereString.append(getNewLineString());
            whereString.append(getTabString(3));
            whereString.append(")");
        }
        returnString = templateString;
        returnString = returnString.replace("@methodName", methodName);
        returnString = returnString.replace("@eoFullPathName", eoFullPathName);
        returnString = returnString.replace("@tableName", tableName);
        returnString = returnString.replace("@whereString", whereString.toString());

        return returnString;
    }

    private String getValidationString(Long packageNo, String tableName, String eoName,
        String methodName, String methodParamClassName, String methodParamInstantName) {
        String returnString = "";
        //@methodName
        //@eoName
        //@methodParamInstantName
        //@loopEOInstance
        //@nullCheck
        //@dupCheck
        String loopEOInstance = lowerCaseFirst(eoName);
        //@validationMethodName
//        String validationMethodName = "validation" + eoName;
        //@mapperInstanceName
//        String mapperInstanceName = lowerCaseFirst(getMapperClassName(packageNo, tableName));
//        //@mapperDeleteMethodName
//        String mapperDeleteMethodName = getMapperMethodName(packageNo, tableName, "delete");
//        //@mapperUpdateMethodName
//        String mapperUpdateMethodName = getMapperMethodName(packageNo, tableName, "update");
//        //@mapperInsertMethodName
//        String mapperInsertMethodName = getMapperMethodName(packageNo, tableName, "insert");
        String mapperInstanceName = lowerCaseFirst(getMapperClassName(packageNo));

        String templateString = getTemplateSqlStmtString("ServiceValidationMethod");

        templateString = templateString.replace("@methodName", methodName);
        templateString = templateString.replace("@methodParamClassName", methodParamClassName);

        templateString = templateString.replace("@eoName", eoName);
        templateString = templateString.replace("@loopEOInstance", loopEOInstance);
        templateString = templateString.replace("//@nullCheck",
            getNullValidationString(loopEOInstance, tableName));
        templateString = templateString.replace("//@dupCheck",
            getDupValidationString(eoName, loopEOInstance, tableName));
        templateString = templateString.replace("@methodParamInstantName", methodParamInstantName);
        templateString = templateString.replace("@mapperInstanceName", mapperInstanceName);

        return templateString;
    }

    private String getDupValidationString(String eoName, String loopEOInstance, String tableName) {
        String returnString = "";

        TableConstraintsVO conditionVO = new TableConstraintsVO();
        conditionVO.setTableName(tableName);
        //1 pk uk 존재여부 확인
        List<TableConstraintsVO> tableConstraintsVOList = b2bGenerateMapper.retrieveTableConstraints(
            conditionVO);
        if (isNotNullAndEmpty(tableConstraintsVOList)) {
            returnString = getTemplateSqlStmtString("serviceValidationDupCheck");
            StringBuilder setParamString = new StringBuilder("");
            StringBuilder addValidationString = new StringBuilder("");
            StringBuilder serviceValidationDupInListB = new StringBuilder("");
            tableConstraintsVOList.stream().forEach(tableConstraintsVO -> {

                List<TableConstraintsVO> columnUsageVOList = b2bGenerateMapper.retrieveKeyColumnUsage(
                    tableConstraintsVO);
                if (isNotNullAndEmpty(columnUsageVOList)) {
                    //const 하나당
                    String tmpServiceValidationDupInList = getTemplateSqlStmtString(
                        "serviceValidationDupInList");
                    StringBuilder stringBuilder = new StringBuilder("");
                    columnUsageVOList.stream().forEach(columnUsageVO -> {
                        String memberName = CaseUtils.toCamelCase(columnUsageVO.getColumnName(),
                            true, '_');

                        String serviceValidationDupInListMember = getTemplateSqlStmtString(
                            "serviceValidationDupInListMember");
                        serviceValidationDupInListMember = serviceValidationDupInListMember.replace(
                            "@memberName", memberName);
                        stringBuilder.append(getNewLineString())
                            .append(serviceValidationDupInListMember);

                        String templateSetParamString = getTemplateSqlStmtString(
                            "serviceValidationDupCheckSetParam");
                        String addValidationSetParamString = getTemplateSqlStmtString(
                            "serviceValidationDupCheckAddValidationSet");
                        String camelMemberName = CaseUtils.toCamelCase(
                            columnUsageVO.getColumnName(), false, '_');

                        String setParam = templateSetParamString.replace("@memberName", memberName);

                        String addValidationSet = addValidationSetParamString.replace(
                                "@camelMemberName", camelMemberName)
                            .replace("@loopEOInstance", loopEOInstance);
                        setParamString.append(getNewLineString());
                        setParamString.append(setParam);

                        addValidationString.append(getNewLineString());
                        addValidationString.append(addValidationSet);

                    });
                    tmpServiceValidationDupInList = tmpServiceValidationDupInList.replace(
                        "@serviceValidationDupInListMember", stringBuilder);
                    serviceValidationDupInListB.append(getNewLineString())
                        .append(tmpServiceValidationDupInList);
                }

            });

            returnString = returnString.replace("//@setParam", setParamString);
            returnString = returnString.replace("//@inListDupCheck",
                serviceValidationDupInListB.toString());
            String retrieveMethod =
                "retrieve" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_')
                    + "ListAll";

            returnString = returnString.replace("@retrieveMethod", retrieveMethod);

            returnString = returnString.replace("//@addValidationSet", addValidationString);
            returnString = returnString.replace("@loopEOInstance", loopEOInstance);
            returnString = returnString.replace("@eoName", eoName);
        }
        //@loopEOInstance
        //@memberName
        //@camelMemberName

        return returnString;
    }


    private String getNullValidationString(String loopEOInstance, String tableName) {
        String returnString = "";

        SchemaColumnConditionVO schemaColumnConditionVO = new SchemaColumnConditionVO();
        schemaColumnConditionVO.setTableName(tableName);
        List<SchemaColumnVO> schemaColumnVOList = b2bGenerateMapper.retrieveColumnSchema(
            schemaColumnConditionVO);
        if (isNotNullAndEmpty(schemaColumnVOList)) {
            StringBuilder tmpString = new StringBuilder("");

            schemaColumnVOList.stream().filter(
                    schemaColumnVO -> !nullToEmpty(schemaColumnVO.getIsNullable()).equals("YES"))
                .forEach(schemaColumnVO -> {
                    String templateString = getTemplateSqlStmtString("serviceValidationNullCheck");
                    String memberName = CaseUtils.toCamelCase(schemaColumnVO.getColumnName(), true,
                        '_');
                    String camelMemberName = CaseUtils.toCamelCase(schemaColumnVO.getColumnName(),
                        false, '_');
                    templateString = templateString.replace("@loopEOInstance", loopEOInstance);
                    templateString = templateString.replace("@memberName", memberName);
                    templateString = templateString.replace("@camelMemberName", camelMemberName);
                    tmpString.append(getNewLineString()).append(templateString);
                });
            returnString = tmpString.toString();
        }
        //@loopEOInstance
        //@memberName
        //@camelMemberName

        return returnString;
    }

    private String getSaveMethodString(Long packageNo, String tableName, String eoName,
        String methodName, String methodParamClassName, String methodParamInstantName) {
        String returnString = "";
        //@methodName
        //@methodParamClassName
        //@methodParamInstantName
        //@loopEOInstance
        String loopEOInstance = lowerCaseFirst(eoName);
        //@validationMethodName
        String validationMethodName = "validation" + eoName;
        //@mapperInstanceName
        String mapperInstanceName = lowerCaseFirst(getMapperClassName(packageNo));
        //@mapperDeleteMethodName
        String mapperDeleteMethodName = getMapperMethodName(packageNo, tableName, "delete");
        //@mapperUpdateMethodName
        String mapperUpdateMethodName = getMapperMethodName(packageNo, tableName, "update");
        //@mapperInsertMethodName
        String mapperInsertMethodName = getMapperMethodName(packageNo, tableName, "insert");
        String templateString = getTemplateSqlStmtString("ServiceSaveMethod");

        templateString = templateString.replace("@methodName", methodName);
        templateString = templateString.replace("@methodParamClassName", methodParamClassName);
        templateString = templateString.replace("@methodParamInstantName", methodParamInstantName);
        templateString = templateString.replace("@loopEOInstance", loopEOInstance);
        templateString = templateString.replace("@validationMethodName", validationMethodName);
        templateString = templateString.replace("@mapperInstanceName", mapperInstanceName);
        templateString = templateString.replace("@mapperDeleteMethodName", mapperDeleteMethodName);
        templateString = templateString.replace("@mapperUpdateMethodName", mapperUpdateMethodName);
        templateString = templateString.replace("@mapperInsertMethodName", mapperInsertMethodName);

        return templateString;
    }

    private String getMapperClassName(Long packageNo) {
        List<TepGenMapperMethodInfoVO> tepGenMapperMethodInfoVOList = coreGenerateMapper.retrieveTepGenMapperMethodInfo(
            TepGenMapperMethodInfoConditionVO.builder().packageNo(packageNo).build());
        return isNotNullAndEmpty(tepGenMapperMethodInfoVOList) ? tepGenMapperMethodInfoVOList.get(0)
            .getMapperClassName() : "";
    }

    private String getMapperMethodName(Long packageNo, String tableName, String xmlMethodType) {
        List<TepGenMapperMethodInfoVO> tepGenMapperMethodInfoVOList = coreGenerateMapper.retrieveTepGenMapperMethodInfo(
            TepGenMapperMethodInfoConditionVO.builder().packageNo(packageNo).tableName(tableName)
                .xmlMethodType(xmlMethodType).build());
        return isNotNullAndEmpty(tepGenMapperMethodInfoVOList) ? tepGenMapperMethodInfoVOList.get(0)
            .getMethodName() : "";
    }


    private void insertInsertMapperMethod(Long packageNo, String packageName, String tableName,
        String eoName, String mapperPackageName, String mapperXmlName, String mapperClassName,
        String methodAnnotationName, String methodParamClassName, String methodParamInstantName) {
        //insert method 만들기
        String methodName =
            "insert" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_')
                + "List";
        String xmlMethodType = "insert";
        String sqlStmt = getInsertString(packageName, tableName, eoName, methodName);

        coreGenerateMapper.insertMultiTepGenMapperMethodInfo(List.of(
            TepGenMapperMethodInfoEO.builder().mapperPackageName(mapperPackageName)
                .packageNo(packageNo).mapperXmlName(mapperXmlName).mapperClassName(mapperClassName)
                .methodAnnotationName(methodAnnotationName).methodName(methodName)
                .methodParamClassName(methodParamClassName)
                .methodParamInstantName(methodParamInstantName).xmlMethodType(xmlMethodType)
                .tableName(tableName).sqlStmt(sqlStmt).build()));
    }

    private void insertUpdateMapperMethod(Long packageNo, String packageName, String tableName,
        String eoName, String mapperPackageName, String mapperXmlName, String mapperClassName,
        String methodAnnotationName, String methodParamClassName, String methodParamInstantName) {
        //insert method 만들기
        String methodName =
            "update" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_')
                + "List";
        String xmlMethodType = "update";
        String sqlStmt = getUpdateString(packageName, tableName, eoName, methodName);

        coreGenerateMapper.insertMultiTepGenMapperMethodInfo(List.of(
            TepGenMapperMethodInfoEO.builder().mapperPackageName(mapperPackageName)
                .packageNo(packageNo).mapperXmlName(mapperXmlName).mapperClassName(mapperClassName)
                .methodAnnotationName(methodAnnotationName).methodName(methodName)
                .methodParamClassName(methodParamClassName)
                .methodParamInstantName(methodParamInstantName).xmlMethodType(xmlMethodType)
                .tableName(tableName).sqlStmt(sqlStmt).build()));
    }


    private String getUpdateString(String packageName, String tableName, String eoName,
        String methodName) {
        String returnString = "";
        String templateString = getTemplateSqlStmtString("MapperXmlUpdate");
//        String methodName =
//            "insertMulti" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_');
        String eoFullPathName = packageName + "." + "model." + eoName;

        SchemaColumnConditionVO schemaColumnConditionVO = new SchemaColumnConditionVO();
        schemaColumnConditionVO.setTableName(tableName);

        List<SchemaColumnVO> schemaColumnVOList = retrieveColumnSchema(schemaColumnConditionVO);
        StringBuilder matchString = new StringBuilder("");
        StringBuilder whereString = new StringBuilder("");
        if (isNotNullAndEmpty(schemaColumnVOList)) {
            final int[] inx = {0};
            final int[] whereInx = {0};
            schemaColumnVOList.stream().filter(schemaColumnVO ->
                !schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT).equals("created_by")
                    && !schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT)
                    .equals("creation_date")
            ).forEach(schemaColumnVO -> {

                inx[0] = inx[0] + 1;
                if (inx[0] > 1) {
                    matchString.append(getNewLineString());
                    matchString.append(getTabString(4));
                    matchString.append(",");

                } else {
                    matchString.append(getTabString(4));
                    matchString.append(" ");
                }
                matchString.append(schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT));
                matchString.append(getTabString(1));
                matchString.append("=");
                matchString.append(getTabString(1));
                if (schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT)
                    .equals("last_update_date")
                ) {
                    matchString.append("now()");
                } else {
                    matchString.append("#{item.");
                    matchString.append(
                        CaseUtils.toCamelCase(
                            schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT),
                            false, '_'));
                    matchString.append("}");

                }

                if (nullToEmpty(schemaColumnVO.getColumnKey()).equals("PRI")) {
                    whereInx[0] = whereInx[0] + 1;
                    if (whereInx[0] > 1) {
                        whereString.append(getNewLineString());
                    }
                    whereString.append(getTabString(4));
                    whereString.append("AND ");
                    whereString.append(schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT));
                    whereString.append(getTabString(1));
                    whereString.append("=");
                    whereString.append(getTabString(1));

                    whereString.append("#{item.");
                    whereString.append(CaseUtils.toCamelCase(
                        schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT), false, '_'));
                    whereString.append("}");
                }
            });
        }
        returnString = templateString;
        returnString = returnString.replace("@methodName", methodName);
        returnString = returnString.replace("@eoFullPathName", eoFullPathName);
        returnString = returnString.replace("@tableName", tableName);
        returnString = returnString.replace("@matchString", matchString.toString());
        returnString = returnString.replace("@whereString", whereString.toString());

        return returnString;
    }

    private String getInsertString(String packageName, String tableName, String eoName,
        String methodName) {
        String returnString = "";
        String templateString = getTemplateSqlStmtString("MapperXmlInsert");
//        String methodName =
//            "insertMulti" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_');
        String eoFullPathName = packageName + "." + "model." + eoName;
        SchemaColumnConditionVO schemaColumnConditionVO = new SchemaColumnConditionVO();
        schemaColumnConditionVO.setTableName(tableName);

        List<SchemaColumnVO> schemaColumnVOList = retrieveColumnSchema(schemaColumnConditionVO);
        StringBuilder columnName = new StringBuilder("");
        StringBuilder variableName = new StringBuilder("");
        if (isNotNullAndEmpty(schemaColumnVOList)) {
            final int[] inx = {0};
            schemaColumnVOList.forEach(schemaColumnVO -> {

                inx[0] = inx[0] + 1;
                if (inx[0] > 1) {
                    columnName.append(getNewLineString());
                    columnName.append(getTabString(3));
                    columnName.append(",");
                } else {
                    columnName.append(getTabString(3));
                    columnName.append(" ");
                }
                columnName.append(schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT));

//                variableName.append(getNewLineString());
//                variableName.append(getTabString(3));
                if (inx[0] > 1) {
                    variableName.append(getNewLineString());
                    variableName.append(getTabString(3));
                    variableName.append(",");
                } else {
                    variableName.append(getTabString(3));
                    variableName.append(" ");
                }
                if (schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT).equals("creation_date")
                    ||
                    schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT)
                        .equals("last_update_date")
                ) {
                    variableName.append("now()");
                } else {
                    variableName.append("#{item.");
                    variableName.append(
                        CaseUtils.toCamelCase(
                            schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT),
                            false, '_'));
                    variableName.append("}");
                }
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

    public String makeEOFile(Long packageNo, String packageName, String tableName) {
        String eoClassName = CaseUtils.toCamelCase(tableName, true, '_') + "EO";
        String eoString = getEOString(packageNo,packageName, tableName, eoClassName);
        String fileName = eoClassName + ".java";

        createFile(packageName, fileName, eoString);
        //file 생성정보
        coreGenerateMapper.insertMulti(List.of(
            TepGenFileInfoEO.builder().fileName(fileName).packageName(packageName)
                .packageNo(packageNo).fileType("model").fileContents(eoString).build()));
        return eoClassName;
    }

    public String makeVOFile(Long packageNo, String packageName, String voClassName,
        LinkedHashMap<String, String> resultMap) {

        String voString = getVOString(packageName, resultMap, voClassName);
        String fileName = voClassName + ".java";

        createFile(packageName, fileName, voString);
        //model 정보 생성

        List<ConvertDataTypeVO> dataTypeVOList = coreGenerateMapper.getConvertDataType();
        Map<String, String> tsTypeMap = new HashMap<>();
        if (getListSize(dataTypeVOList) > 0) {
            dataTypeVOList.forEach(convertDataTypeVO -> {
                tsTypeMap.put(convertDataTypeVO.getJavaDataType(),
                    convertDataTypeVO.getTsDataType());
            });
        }

        resultMap.forEach((memberName,javaType)->{
            TepGenModelInfoEO tepGenModelInfoEO = new TepGenModelInfoEO();
            tepGenModelInfoEO.setPackageNo(packageNo);
            tepGenModelInfoEO.setPackageName(packageName);
            tepGenModelInfoEO.setVoName(voClassName);
            tepGenModelInfoEO.setJavaType(javaType);
            tepGenModelInfoEO.setTsType(tsTypeMap.get(javaType));
            tepGenModelInfoEO.setInterfaceName("I"+replaceLast(voClassName, "VO", ""));
            tepGenModelInfoEO.setMemberName(memberName);
            tepGenModelInfoEO.setNullYn("Y");
            tepGenModelInfoEO.setLookupYn("N");
            tepGenModelInfoEO.setDatasetName(lowerCaseFirst(replaceLast(voClassName, "VO", ""))+"Dataset");
            coreGenerateMapper.insertTepGenModelInfoList(List.of(tepGenModelInfoEO));
        });

        //file 생성정보
        coreGenerateMapper.insertMulti(List.of(
            TepGenFileInfoEO.builder().fileName(fileName).packageName(packageName)
                .packageNo(packageNo).fileType("model").fileContents(voString).build()));
        return voClassName;
    }

    public String makeMapperXml(Long packageNo, String packageName) {
        String mapperFileName = "";
        List<TepGenMapperMethodInfoVO> tepGenMapperMethodInfoVOList = coreGenerateMapper.retrieveTepGenMapperMethodInfo(
            TepGenMapperMethodInfoConditionVO.builder().packageNo(packageNo).build());
        if (isNotNullAndEmpty(tepGenMapperMethodInfoVOList)) {
            String xmlMapperTemplateString = getTemplateSqlStmtString("MapperXml");
            StringBuilder contentsStringBuilder = new StringBuilder("");
            AtomicReference<String> mapperPackage = new AtomicReference<>("");
            AtomicReference<String> mapperXmlName = new AtomicReference<>("");
            AtomicReference<String> mapperClassName = new AtomicReference<>("");
            tepGenMapperMethodInfoVOList.stream().forEach(tepGenMapperMethodInfoVO -> {
                contentsStringBuilder.append(getNewLineString());
                contentsStringBuilder.append(tepGenMapperMethodInfoVO.getSqlStmt());
                mapperPackage.set(tepGenMapperMethodInfoVO.getMapperPackageName());
                mapperXmlName.set(tepGenMapperMethodInfoVO.getMapperXmlName());
                mapperClassName.set(tepGenMapperMethodInfoVO.getMapperClassName());
            });
            String mapperFullPath = mapperPackage.get() + "." + mapperClassName.get();

            xmlMapperTemplateString = xmlMapperTemplateString.replace("@mapperFullPath",
                mapperFullPath);
            xmlMapperTemplateString = xmlMapperTemplateString.replace("@mapperContents",
                contentsStringBuilder.toString());
            createFile(packageName, mapperXmlName.get(), xmlMapperTemplateString);
            //file 생성정보
            coreGenerateMapper.insertMulti(List.of(
                TepGenFileInfoEO.builder().fileName(mapperXmlName.get()).packageName(packageName)
                    .packageNo(packageNo).fileType("mapperXml")
                    .fileContents(xmlMapperTemplateString).build()));
        }
        return mapperFileName;
    }

    private String getImportModelString(Long packageNo) {
        StringBuilder returnString = new StringBuilder("");
        List<TepGenFileInfoVO> tepGenFileInfoVOList = coreGenerateMapper.retrieveTepGenFileInfo(
            TepGenFileInfoConditionVO.builder().packageNo(packageNo).fileType("model").build());
        HashMap<String, String> distinctMap = new HashMap<>();
        if (tepGenFileInfoVOList != null) {
            tepGenFileInfoVOList.stream().forEach(tepGenFileInfoVO -> {
//                returnString.append(getNewLineString())
//                );
                distinctMap.put(
                    (new StringBuilder().append("import ").append(tepGenFileInfoVO.getPackageName())
                        .append(".model.")
                        .append(tepGenFileInfoVO.getFileName().replace(".java", ";"))).toString(),
                    "");

            });
            distinctMap.forEach((key, val) -> returnString.append(getNewLineString()).append(key));
        }

        return returnString.toString();
    }


    public String makeServiceImplJava(Long packageNo, String packageName) {
        String serviceImplJavaFileName = "";
        List<TepGenServiceMethodInfoEO> tepGenServiceMethodInfoEOList = coreGenerateMapper.retrieveTepGenServiceMethodInfo(
            TepGenServiceMethodInfoEO.builder().packageNo(packageNo).build());
        if (isNotNullAndEmpty(tepGenServiceMethodInfoEOList)) {
            String serviceImplJavaString = getTemplateSqlStmtString("ServiceJavaImpl");

            StringBuilder contentsStringBuilder = new StringBuilder("");
            AtomicReference<String> servicePackage = new AtomicReference<>("");
            AtomicReference<String> serviceClassName = new AtomicReference<>("");
            tepGenServiceMethodInfoEOList.stream().forEach(tepGenMapperMethodInfoVO -> {
                contentsStringBuilder.append(getNewLineString());
//                    contentsStringBuilder.append(getTabString(1));

                contentsStringBuilder.append(tepGenMapperMethodInfoVO.getMethodContents());
                servicePackage.set(tepGenMapperMethodInfoVO.getServicePackageName());
                serviceClassName.set(tepGenMapperMethodInfoVO.getServiceClassName());
            });
            String serviceFullPath = servicePackage.get();

            serviceImplJavaString = serviceImplJavaString.replace("@serviceFullPath",
                serviceFullPath);
            serviceImplJavaString = serviceImplJavaString.replace("@importModelString",
                getImportModelString(packageNo));
            serviceImplJavaString = serviceImplJavaString.replace("@serviceClassName",
                serviceClassName.get());
            serviceImplJavaString = serviceImplJavaString.replace("@serviceContents",
                contentsStringBuilder.toString());
            //mapper 정보조회
            TepGenMapperMethodInfoVO TepGenMapperMethodInfoVO = coreGenerateMapper.retrieveTepGenMapperMethodInfo(
                    TepGenMapperMethodInfoConditionVO.builder().packageNo(packageNo).build()).stream()
                .findFirst().get();
            String mapperPackage = TepGenMapperMethodInfoVO.getMapperPackageName() + "."
                + TepGenMapperMethodInfoVO.getMapperClassName();
            String mapperClassName = TepGenMapperMethodInfoVO.getMapperClassName();
            String mapperInstantName = lowerCaseFirst(mapperClassName);
            serviceImplJavaString = serviceImplJavaString.replace("@mapperPackage", mapperPackage);
            serviceImplJavaString = serviceImplJavaString.replace("@mapperClassName",
                mapperClassName);
            serviceImplJavaString = serviceImplJavaString.replace("@mapperInstantName",
                mapperInstantName);
            createFile(packageName, serviceClassName.get() + "Impl.java", serviceImplJavaString);
            //file 생성정보
            coreGenerateMapper.insertMulti(List.of(
                TepGenFileInfoEO.builder().fileName(serviceClassName.get() + "Impl.java")
                    .packageName(packageName).packageNo(packageNo).fileType("serviceImpl")
                    .fileContents(serviceImplJavaString).build()));
        }
        return serviceImplJavaFileName;
    }

    public String makeServiceJava(Long packageNo, String packageName) {
        String serviceJavaFileName = "";
        List<TepGenServiceMethodInfoEO> tepGenServiceMethodInfoEOList = coreGenerateMapper.retrieveTepGenServiceMethodInfo(
            TepGenServiceMethodInfoEO.builder().packageNo(packageNo).methodAccessor("public")
                .build());
        if (isNotNullAndEmpty(tepGenServiceMethodInfoEOList)) {
            String serviceJavaString = getTemplateSqlStmtString("ServiceJava");
            StringBuilder contentsStringBuilder = new StringBuilder("");
            AtomicReference<String> servicePackage = new AtomicReference<>("");
            AtomicReference<String> serviceClassName = new AtomicReference<>("");
            tepGenServiceMethodInfoEOList.stream().forEach(tepGenMapperMethodInfoVO -> {
                contentsStringBuilder.append(getNewLineString());
                contentsStringBuilder.append(getTabString(1));

                contentsStringBuilder.append(
                    isNotEmpty(tepGenMapperMethodInfoVO.getMethodReturnClassName())
                        ? tepGenMapperMethodInfoVO.getMethodReturnClassName() : "void");
                contentsStringBuilder.append(" ");
                contentsStringBuilder.append(tepGenMapperMethodInfoVO.getMethodName());
                contentsStringBuilder.append("(");
                if (isNotEmpty(tepGenMapperMethodInfoVO.getMethodParamClassName())) {
                    contentsStringBuilder.append(
                        tepGenMapperMethodInfoVO.getMethodParamClassName());
                    contentsStringBuilder.append(" ");
                    contentsStringBuilder.append(
                        tepGenMapperMethodInfoVO.getMethodParamInstantName());
                }
                if (nullToEmpty(tepGenMapperMethodInfoVO.getAddDatasetParam()).equals("Y")) {
                    contentsStringBuilder.append(" ,String dataSetName");
                }
                contentsStringBuilder.append("); ");
                servicePackage.set(tepGenMapperMethodInfoVO.getServicePackageName());
                serviceClassName.set(tepGenMapperMethodInfoVO.getServiceClassName());
            });
            String serviceFullPath = servicePackage.get();

            serviceJavaString = serviceJavaString.replace("@serviceFullPath", serviceFullPath);
            serviceJavaString = serviceJavaString.replace("@importModelString",
                getImportModelString(packageNo));
            serviceJavaString = serviceJavaString.replace("@serviceClassName",
                serviceClassName.get());
            serviceJavaString = serviceJavaString.replace("@serviceContents",
                contentsStringBuilder.toString());

            createFile(packageName, serviceClassName.get() + ".java", serviceJavaString);
            //file 생성정보
            coreGenerateMapper.insertMulti(List.of(
                TepGenFileInfoEO.builder().fileName(serviceClassName.get() + ".java")
                    .packageName(packageName).packageNo(packageNo).fileType("service")
                    .fileContents(serviceJavaString).build()));
        }
        return serviceJavaFileName;
    }

    public String makeMapperJava(Long packageNo, String packageName) {
        String mapperJavaFileName = "";
        List<TepGenMapperMethodInfoVO> tepGenMapperMethodInfoVOList = coreGenerateMapper.retrieveTepGenMapperMethodInfo(
            TepGenMapperMethodInfoConditionVO.builder().packageNo(packageNo).build());
        if (isNotNullAndEmpty(tepGenMapperMethodInfoVOList)) {
            String mapperJavaString = getTemplateSqlStmtString("MapperJava");
            StringBuilder contentsStringBuilder = new StringBuilder("");
            AtomicReference<String> mapperPackage = new AtomicReference<>("");
            AtomicReference<String> mapperClassName = new AtomicReference<>("");
            tepGenMapperMethodInfoVOList.stream().forEach(tepGenMapperMethodInfoVO -> {
                contentsStringBuilder.append(getNewLineString());
                contentsStringBuilder.append(getTabString(1));

                contentsStringBuilder.append(
                    isNotEmpty(tepGenMapperMethodInfoVO.getMethodReturnClassName())
                        ? tepGenMapperMethodInfoVO.getMethodReturnClassName() : "void");
                contentsStringBuilder.append(" ");
                contentsStringBuilder.append(tepGenMapperMethodInfoVO.getMethodName());
                contentsStringBuilder.append("(");
                if (isNotEmpty(tepGenMapperMethodInfoVO.getMethodParamClassName())) {
                    contentsStringBuilder.append(
                        tepGenMapperMethodInfoVO.getMethodParamClassName());
                    contentsStringBuilder.append(" ");
                    contentsStringBuilder.append(
                        tepGenMapperMethodInfoVO.getMethodParamInstantName());
                }
                contentsStringBuilder.append("); ");
                mapperPackage.set(tepGenMapperMethodInfoVO.getMapperPackageName());
                mapperClassName.set(tepGenMapperMethodInfoVO.getMapperClassName());
            });
            String mapperFullPath = mapperPackage.get();

            mapperJavaString = mapperJavaString.replace("@mapperFullPath", mapperFullPath);
            mapperJavaString = mapperJavaString.replace("@importModelString",
                getImportModelString(packageNo));
            mapperJavaString = mapperJavaString.replace("@mapperClassName", mapperClassName.get());
            mapperJavaString = mapperJavaString.replace("@mapperContents",
                contentsStringBuilder.toString());

            createFile(packageName, mapperClassName.get() + ".java", mapperJavaString);
            //file 생성정보
            coreGenerateMapper.insertMulti(List.of(
                TepGenFileInfoEO.builder().fileName(mapperClassName.get() + ".java")
                    .packageName(packageName).packageNo(packageNo).fileType("mapper")
                    .fileContents(mapperJavaString).build()));
        }
        return mapperJavaFileName;
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

    public String getVOString(String packageName, LinkedHashMap<String, String> resultMap,
        String voClassName) {
        String returnString = "";
        //1 get EO Template
        String templateString = getTemplateSqlStmtString("EO");

        //2 get Replace String
        StringBuilder replaceString = new StringBuilder("");
        if (isNotNullAndEmpty(resultMap)) {
            resultMap.forEach((key, val) -> {
                replaceString.append(getNewLineString()).append(getTabString(1)).append("private")
                    .append(" ").append(val).append(" ").append(key).append(";");

            });
        }

        //3 eo name replace
        returnString = templateString.replace("//@EONameHere", voClassName);
        returnString = returnString.replace("//@GenHere", replaceString.toString());
        returnString = returnString.replace("//@PackageNameHere", packageName);
        return returnString;
    }

    public String getEOString(Long packageNo, String packageName, String tableName, String eoClassName) {
        String returnString = "";
        //1 get EO Template
        String templateString = getTemplateSqlStmtString("EO");
        //2 get Column Info
        SchemaColumnConditionVO schemaColumnConditionVO = new SchemaColumnConditionVO();
        schemaColumnConditionVO.setTableName(tableName);

        List<SchemaColumnVO> schemaColumnVOList = retrieveColumnSchema(schemaColumnConditionVO);
        List<CoreColumnVO> coreColumnVOList = new ArrayList<>();
        if (isNotNullAndEmpty(schemaColumnVOList)) {
            schemaColumnVOList.stream().forEach(schemaColumnVO -> {

                coreColumnVOList.add(CoreColumnVO.builder().dataType(schemaColumnVO.getDataType())
                    .columnName(schemaColumnVO.getColumnName()).build()

                );
            });
        }

        //2 get Replace String
        List<TepGenModelInfoEO> tepGenModelInfoEOList  = new ArrayList<>();
        String replaceString = getVOColumnString(coreColumnVOList, 1,tepGenModelInfoEOList);
        tepGenModelInfoEOList.stream().forEach(tepGenModelInfoEO ->
            {
                tepGenModelInfoEO.setPackageNo(packageNo);
                tepGenModelInfoEO.setPackageName(packageName);
                tepGenModelInfoEO.setVoName(eoClassName);
                tepGenModelInfoEO.setInterfaceName("I"+replaceLast(eoClassName, "EO", ""));
                tepGenModelInfoEO.setNullYn("Y");
                tepGenModelInfoEO.setDatasetName(lowerCaseFirst(replaceLast(eoClassName, "EO", ""))+"Dataset");
                coreGenerateMapper.insertTepGenModelInfoList(List.of(tepGenModelInfoEO));

            }
            );
        //3 eo name replace
        returnString = templateString.replace("//@EONameHere", eoClassName);
        returnString = returnString.replace("//@GenHere", replaceString);
        returnString = returnString.replace("//@PackageNameHere", packageName);
        return returnString;
    }

    private String getTemplateSqlStmtString(String templateType) {
        String returnString = "";
        TepGenTemplateEO conditionEO = TepGenTemplateEO.builder().templateType(templateType)
            .build();
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

    public String getVOColumnString(List<CoreColumnVO> coreColumnVOList, int tabInx,List<TepGenModelInfoEO> tepGenModelInfo) {
        String tabString = getTabString(tabInx);
        StringBuilder returnString = new StringBuilder("");
        if (getListSize(coreColumnVOList) > 0) {
            List<ConvertDataTypeVO> dataTypeVOList = coreGenerateMapper.getConvertDataType();
            Map<String, String> dataTypeMap = new HashMap<>();
            Map<String, String> tsTypeMap = new HashMap<>();
            if (getListSize(dataTypeVOList) > 0) {
                dataTypeVOList.forEach(convertDataTypeVO -> {
                    dataTypeMap.put(convertDataTypeVO.getMariaDataType(),
                        convertDataTypeVO.getJavaDataType());
                    tsTypeMap.put(convertDataTypeVO.getMariaDataType(),
                        convertDataTypeVO.getTsDataType());
                });
            }

            List<TepGenModelInfoEO> finalTepGenModelInfo = tepGenModelInfo;
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

                TepGenModelInfoEO tepGenModelInfoEO = new TepGenModelInfoEO();
                tepGenModelInfoEO.setColumnType(coreColumnVO.getDataType());
                tepGenModelInfoEO.setJavaType(isNotNullAndEmpty(dataTypeMap.get(coreColumnVO.getDataType()))
                    ? dataTypeMap.get(coreColumnVO.getDataType()) : "String");
                tepGenModelInfoEO.setColumnName(coreColumnVO.getColumnName());
                tepGenModelInfoEO.setMemberName(CaseUtils.toCamelCase(coreColumnVO.getColumnName(), false, '_'));
                tepGenModelInfoEO.setTsType(isNotNullAndEmpty(tsTypeMap.get(coreColumnVO.getDataType()))
                    ? tsTypeMap.get(coreColumnVO.getDataType()) : "string");
                tepGenModelInfoEO.setLookupYn("N");
                finalTepGenModelInfo.add(tepGenModelInfoEO);
            });


        }
        return returnString.toString();
    }

}
