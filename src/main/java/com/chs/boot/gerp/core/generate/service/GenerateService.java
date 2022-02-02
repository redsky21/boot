package com.chs.boot.gerp.core.generate.service;

import static com.chs.boot.common.constant.SystemConstant.BOF;
import static com.chs.boot.common.util.CommonUtil.getListSize;
import static com.chs.boot.common.util.CommonUtil.isNotNullAndEmpty;
import static com.chs.boot.common.util.CommonUtil.nullToEmpty;
import static com.chs.boot.common.util.MyBatisUtil.isEmpty;
import static com.chs.boot.common.util.MyBatisUtil.isNotEmpty;
import static com.chs.boot.common.util.StringUtil.getNewLineString;
import static com.chs.boot.common.util.StringUtil.getTabString;
import static com.chs.boot.common.util.StringUtil.lastIndexString;
import static com.chs.boot.common.util.StringUtil.lowerCaseFirst;
import static com.chs.boot.common.util.StringUtil.replaceLast;
import static com.chs.boot.common.util.StringUtil.upperCaseFirst;

import com.chs.boot.common.util.StringUtil;
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
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.text.CaseUtils;
import org.apache.ibatis.annotations.Case;
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
                        tepGenMasterInfoEO.getControllerDatasetMethodSeq(),
                        tepGenMasterInfoEO.getMasterSeq());
                } else if (tepGenMasterInfoEO.getMethodType().equals("T")) {
                    doTableJob(tepGenMasterInfoEO.getPackageNo(),
                        tepGenMasterInfoEO.getPackageName(), tepGenMasterInfoEO.getTableName(),
                        tepGenMasterInfoEO.getMasterSeq());
                } else if (tepGenMasterInfoEO.getMethodType().equals("L")) {
                    doLookupJob(tepGenMasterInfoEO.getPackageNo(),
                        tepGenMasterInfoEO.getPackageName(), tepGenMasterInfoEO.getMethodType(),
                        tepGenMasterInfoEO.getMethodName(), tepGenMasterInfoEO.getSqlStmt(),
                        tepGenMasterInfoEO.getVoName(), tepGenMasterInfoEO.getControllerYn(),
                        tepGenMasterInfoEO.getServiceYn(), tepGenMasterInfoEO.getInitYn(),
                        tepGenMasterInfoEO.getInitOrderSeq(), tepGenMasterInfoEO.getLookupType(),
                        tepGenMasterInfoEO.getControllerMethodName(),
                        tepGenMasterInfoEO.getControllerDatasetMethodSeq(),
                        tepGenMasterInfoEO.getMasterSeq());
                } else if (tepGenMasterInfoEO.getMethodType().equals("C")) {
                    doSqlJob(tepGenMasterInfoEO.getPackageNo(), tepGenMasterInfoEO.getPackageName(),
                        tepGenMasterInfoEO.getMethodType(), tepGenMasterInfoEO.getMethodName(),
                        tepGenMasterInfoEO.getSqlStmt(), tepGenMasterInfoEO.getVoName(),
                        tepGenMasterInfoEO.getControllerYn(), tepGenMasterInfoEO.getServiceYn(),
                        tepGenMasterInfoEO.getInitYn(), tepGenMasterInfoEO.getInitOrderSeq(),
                        tepGenMasterInfoEO.getLookupType(),
                        tepGenMasterInfoEO.getControllerMethodName(),
                        tepGenMasterInfoEO.getControllerDatasetMethodSeq(),
                        tepGenMasterInfoEO.getMasterSeq());
                    if (isNotEmpty(tepGenMasterInfoEO.getControllerSaveMethodName()) && isNotEmpty(
                        tepGenMasterInfoEO.getTableName())) {
                        doComplexJob(tepGenMasterInfoEO);
                    }
                }

            });
            //sql controller method 처리
            Map<Long, String> controllerMethodMap = new HashMap<>();

            coreGenerateMapper.retrieveTepGenControllerUnitMethodListAll(
                    TepGenControllerUnitMethodEO.builder().packageNo(packageNo).build()).stream()
                .forEach(tepGenControllerUnitMethodEO -> controllerMethodMap.put(
                    tepGenControllerUnitMethodEO.getPackageNo(),
                    tepGenControllerUnitMethodEO.getControllerMethodName()));
            if (controllerMethodMap.size() > 0) {
                controllerMethodMap.forEach(
                    (mapPackageNo, mapControllerMethodName) -> insertControllerMethodForSql(
                        mapPackageNo, packageName, mapControllerMethodName));
                //insertControllerMethodForSql
            }
            //complex 작업
            makeComplexController(packageNo);
            makeMapperXml(packageNo, packageName);
            makeMapperJava(packageNo, packageName);
            makeServiceJava(packageNo, packageName);
            makeServiceImplJava(packageNo, packageName);
            makeControllerJava(packageNo, packageName);
            makeReactTSFile(packageNo, packageName);
            makeReactUtilFile(packageNo, packageName);
            makeReactApiFile(packageNo, packageName);
            makeReactStoreFile(packageNo, packageName);
        }
    }

    private void makeComplexController(Long packageNo) {
        //check
        Map<String, TepGenMasterInfoEO> complexMap = new LinkedHashMap<>();
        coreGenerateMapper.retrieveTepGenMasterInfoListAll(TepGenMasterInfoEO.builder()
            .packageNo(packageNo)
            .methodType("C")
            .build()).forEach(tepGenMasterInfoEO -> {
            complexMap.put(tepGenMasterInfoEO.getControllerSaveMethodName(), tepGenMasterInfoEO);
        });
        complexMap.forEach((controllerSaveMethodName, tepGenMasterInfoEO) -> {
            String controllerJavaSaveComplexMethod = getTemplateSqlStmtString(
                "ControllerJavaSaveComplexMethod");
            StringBuilder lineString = new StringBuilder("");
            coreGenerateMapper.retrieveTepGenMasterInfoListAll(TepGenMasterInfoEO.builder()
                .packageNo(packageNo)
                .controllerSaveMethodName(controllerSaveMethodName)
                .build()).forEach((rowEO -> {
                    String controllerJavaSaveComplexAtom = getTemplateSqlStmtString(
                        "ControllerJavaSaveComplexAtom");
                    String voName = rowEO.getVoName();
                    String voInstanceName = lowerCaseFirst(rowEO.getVoName());
                    String simpleDatasetName = replaceLast(voInstanceName, "VO", "DatasetName");
                    Long datasetIndex = rowEO.getControllerDatasetMethodSeq() == null ? 0L
                        : rowEO.getControllerDatasetMethodSeq();
                    TepGenServiceMethodInfoEO tepGenServiceMethodInfoEO = coreGenerateMapper.retrieveTepGenServiceMethodInfo(
                        TepGenServiceMethodInfoEO.builder()
                            .masterSeq(rowEO.getMasterSeq())
                            .methodAnnotationName("@Transactional")
                            .build()).stream().findFirst().orElseGet(null);
                    if (tepGenServiceMethodInfoEO == null) {
                        return;
                    }
                    String serviceName = lowerCaseFirst(
                        tepGenServiceMethodInfoEO.getServiceClassName());
                    String serviceMethodName = tepGenServiceMethodInfoEO.getMethodName();

                    controllerJavaSaveComplexAtom = controllerJavaSaveComplexAtom.replace("@voName",
                            voName)
                        .replace("@voInstanceName", voInstanceName)
                        .replace("@simpleDatasetName", simpleDatasetName)
                        .replace("@datasetIndex", datasetIndex.toString())
                        .replace("@serviceName", serviceName)
                        .replace("@serviceMethodName", serviceMethodName);
                    lineString.append(getNewLineString()).append(getTabString(1))
                        .append(controllerJavaSaveComplexAtom);
                })
            );
            String urlPath = lastIndexString(tepGenMasterInfoEO.getPackageName(), ".");
            controllerJavaSaveComplexMethod = controllerJavaSaveComplexMethod.replace("@urlPath",
                    urlPath)
                .replace("@methodName", controllerSaveMethodName)
                .replace("//@complexSaveContents", lineString);
            ;

            String packageName = tepGenMasterInfoEO.getPackageName();
            String controllerPackageName = packageName + ".controller";
            String methodAccessor = "public";
            String controllerClassName =
                upperCaseFirst(lastIndexString(packageName, ".")) + "Controller";
            ;
            String methodAnnotationName = "@Transactional";
            String methodReturnClassName = "ResponseModel";
            String methodParamClassName = "@RequestBody RequestModel";
            String methodParamInstantName = "requestModel";
            String methodName = tepGenMasterInfoEO.getControllerSaveMethodName();
            String methodContents = controllerJavaSaveComplexMethod;

            coreGenerateMapper.insertTepGenControllerMethodInfoList(List.of(
                TepGenControllerMethodInfoEO.builder().controllerPackageName(controllerPackageName)
                    .packageNo(packageNo).controllerClassName(controllerClassName)
                    .methodAnnotationName(methodAnnotationName).methodAccessor(methodAccessor)
                    .methodReturnClassName(methodReturnClassName).methodName(methodName)
                    .methodParamClassName(methodParamClassName)
                    .methodParamInstantName(methodParamInstantName).methodContents(methodContents)
                    .build()));


        });
    }

    private String getReactTsString(Long packageNo) {
        StringBuilder returnString = new StringBuilder("");
        TepGenModelInfoEO tepGenModelInfoEO = new TepGenModelInfoEO();
        tepGenModelInfoEO.setPackageNo(packageNo);
        tepGenModelInfoEO.setLookupYn("N");
        tepGenModelInfoEO.setForReactYN("Y");
        Map<String, String> interfaceNameMap = new HashMap<>();
        coreGenerateMapper.retrieveTepGenModelInfoListAll(tepGenModelInfoEO).stream().filter(
                tepGenModelInfoEO1 -> isNotNullAndEmpty(tepGenModelInfoEO1.getControllerMethodName()))
            .forEach(tepGenModelInfoEO1 -> {
                if (isNotNullAndEmpty(tepGenModelInfoEO1.getInterfaceName())) {
                    interfaceNameMap.put(tepGenModelInfoEO1.getInterfaceName(),
                        tepGenModelInfoEO1.getDatasetName());
                }
            });
        String tsMainTemplateString = getTemplateSqlStmtString("reactType");
        StringBuilder contentString = new StringBuilder("");
        interfaceNameMap.forEach((interfaceName, datasetName) -> {
            String reactTypeInterfaceContent = getTemplateSqlStmtString(
                "reactTypeInterfaceContent");

            TepGenModelInfoEO rowConditionEO = new TepGenModelInfoEO();
            rowConditionEO.setPackageNo(packageNo);
            rowConditionEO.setLookupYn("N");
            rowConditionEO.setInterfaceName(interfaceName);
            rowConditionEO.setForReactYN("Y");
            StringBuilder columnString = new StringBuilder("");
            coreGenerateMapper.retrieveTepGenModelInfoListAll(rowConditionEO).stream()
                .forEach(rowEO -> {
                    if (rowEO.getInterfaceName().equals("IOmResourceCondition")) {
                        String breakPoint = "";
                    }
                    String reactTypeInterfaceContentAtom = getTemplateSqlStmtString(
                        "reactTypeInterfaceContentAtom");
                    reactTypeInterfaceContentAtom = reactTypeInterfaceContentAtom.replace(
                        "@memberName", rowEO.getMemberName());
                    reactTypeInterfaceContentAtom = reactTypeInterfaceContentAtom.replace("@tsType",
                        isEmpty(rowEO.getTsType()) ? "string" : rowEO.getTsType());
                    columnString.append(getNewLineString()).append(reactTypeInterfaceContentAtom);
                });
            reactTypeInterfaceContent = reactTypeInterfaceContent.replace("@interfaceName",
                interfaceName);
            reactTypeInterfaceContent = reactTypeInterfaceContent.replace("@interfaceMemberContent",
                columnString);

            contentString.append(getNewLineString()).append(reactTypeInterfaceContent);


        });
        //api Interface 추가
        StringBuilder apiString = new StringBuilder("");
        TepGenModelInfoEO apiEO = new TepGenModelInfoEO();
        apiEO.setPackageNo(packageNo);
        apiEO.setForReactYN("Y");
        Map<String, String> apiMap = new HashMap<>();
        coreGenerateMapper.retrieveTepGenModelInfoListAll(apiEO).stream()
            .forEach(tepGenModelInfoEO1 -> {
                apiMap.put(tepGenModelInfoEO1.getApiInterfaceParam(),
                    tepGenModelInfoEO1.getApiInterfaceRespData());
            });
        apiMap.forEach((apiInterfaceParam, apiInterfaceRespData) -> {
            TepGenModelInfoEO condtionFindEO = new TepGenModelInfoEO();
            condtionFindEO.setPackageNo(packageNo);
            condtionFindEO.setApiInterfaceParam(apiInterfaceParam);
            condtionFindEO.setForReactYN("Y");
            String conditionType = coreGenerateMapper.retrieveTepGenModelInfoListAll(condtionFindEO)
                .stream().filter(
                    tepGenModelInfoEO1 -> isNotNullAndEmpty(tepGenModelInfoEO1.getVoName())
                        && tepGenModelInfoEO1.getVoName().indexOf("ConditionVO") >= 0).findFirst()
                .orElseGet(() -> {
                    return coreGenerateMapper.retrieveTepGenModelInfoListAll(condtionFindEO)
                        .stream().findFirst().get();
                }).getInterfaceName();
            String reactTypeInterfaceContentApi = getTemplateSqlStmtString(
                "reactTypeInterfaceContentApi");
            reactTypeInterfaceContentApi = reactTypeInterfaceContentApi.replace(
                "@apiInterfaceParam", apiInterfaceParam);
            reactTypeInterfaceContentApi = reactTypeInterfaceContentApi.replace(
                "@apiInterfaceRespData", apiInterfaceRespData);

            reactTypeInterfaceContentApi = reactTypeInterfaceContentApi.replace("@conditionType",
                conditionType);
            if (apiInterfaceParam.startsWith("ISave")) {
                reactTypeInterfaceContentApi = reactTypeInterfaceContentApi.replace("/*", "")
                    .replace("*/", "");
            }
            StringBuilder datasetString = new StringBuilder("");
            StringBuilder reqDatasetString = new StringBuilder("");
            LinkedHashMap<String, String> datasetMap = new LinkedHashMap();
            coreGenerateMapper.retrieveTepGenModelInfoListAll(condtionFindEO).stream().filter(
                    tepGenModelInfoEO1 -> isEmpty(tepGenModelInfoEO1.getVoName())
                        || tepGenModelInfoEO1.getVoName().indexOf("ConditionVO") < 0)
                .forEach(tepGenModelInfoEO1 -> {
                    datasetMap.put(tepGenModelInfoEO1.getDatasetName(),
                        tepGenModelInfoEO1.getInterfaceName());
                });
            datasetMap.forEach((dataSetName, interfaceName) -> {
                datasetString.append(getNewLineString()).append(getTabString(1)).append(
                    getTemplateSqlStmtString("reactTypeInterfaceApiRespDatasetContents").replace(
                        "@datasetName", dataSetName).replace("@interfaceName", interfaceName));
                reqDatasetString.append(getNewLineString()).append(getTabString(2)).append(
                    getTemplateSqlStmtString("reactTypeInterfaceApiReqDatasetContents").replace(
                        "@datasetName", dataSetName).replace("@interfaceName", interfaceName));


            });
            apiString.append(getNewLineString()).append(
                reactTypeInterfaceContentApi.replace("@apiRespDatasetContents", datasetString)
                    .replace("@apiReqDatasetContents", reqDatasetString));
        });

        //saveApi 추가
        StringBuilder saveApiString = new StringBuilder("");
        TepGenModelInfoEO saveApiEO = new TepGenModelInfoEO();
        saveApiEO.setPackageNo(packageNo);
        saveApiEO.setForReactYN("Y");
        Map<String, TepGenModelInfoEO> saveApiMap = new HashMap<>();
        coreGenerateMapper.retrieveTepGenModelInfoListAll(saveApiEO).stream()
            .forEach(tepGenModelInfoEO1 -> {
                if(isNotNullAndEmpty(tepGenModelInfoEO1.getSaveApiInterfaceParam())){
                    saveApiMap.put(tepGenModelInfoEO1.getSaveApiInterfaceParam(),
                        tepGenModelInfoEO1);
                }

            });
        saveApiMap.forEach((saveApiInterfaceParam, rowEO) -> {
            TepGenModelInfoEO condtionFindEO = new TepGenModelInfoEO();
            condtionFindEO.setPackageNo(packageNo);
            condtionFindEO.setSaveApiInterfaceParam(saveApiInterfaceParam);

            String reactTypeInterfaceContentApi = getTemplateSqlStmtString(
                "reactTypeSaveInterfaceContentApi");
            if(isEmpty(saveApiInterfaceParam) ){
                String breakpoint ="";
            }
            reactTypeInterfaceContentApi = reactTypeInterfaceContentApi.replace(
                "@apiInterfaceParam", saveApiInterfaceParam);

//            StringBuilder datasetString = new StringBuilder("");
            StringBuilder reqDatasetString = new StringBuilder("");
            LinkedHashMap<String, String> datasetMap = new LinkedHashMap();
            coreGenerateMapper.retrieveTepGenModelInfoListAll(condtionFindEO).stream().filter(
                    tepGenModelInfoEO1 -> isEmpty(tepGenModelInfoEO1.getVoName())
                        || tepGenModelInfoEO1.getVoName().indexOf("ConditionVO") < 0)
                .forEach(tepGenModelInfoEO1 -> {
                    datasetMap.put(tepGenModelInfoEO1.getDatasetName(),
                        tepGenModelInfoEO1.getInterfaceName());
                });
            datasetMap.forEach((dataSetName, interfaceName) -> {
//                datasetString.append(getNewLineString()).append(getTabString(1)).append(
//                    getTemplateSqlStmtString("reactTypeInterfaceApiRespDatasetContents").replace(
//                        "@datasetName", dataSetName).replace("@interfaceName", interfaceName));
                reqDatasetString.append(getNewLineString()).append(getTabString(2)).append(
                    getTemplateSqlStmtString("reactTypeInterfaceApiReqDatasetContents").replace(
                        "@datasetName", dataSetName).replace("@interfaceName", interfaceName));


            });
            saveApiString.append(getNewLineString()).append(
                reactTypeInterfaceContentApi
//                    .replace("@apiRespDatasetContents", datasetString)
                    .replace("@apiReqDatasetContents", reqDatasetString));
        });
        // save Api end
        contentString.append(apiString.toString()).append(saveApiString.toString());

        returnString = new StringBuilder(
            tsMainTemplateString.replace("//@interfaceContent", contentString));
        return returnString.toString();
    }

    private String getReactApiString(Long packageNo, String controllerMethod, String fullUrl) {
        StringBuilder returnString = new StringBuilder("");
        TepGenModelInfoEO tepGenModelInfoEO = new TepGenModelInfoEO();
        tepGenModelInfoEO.setPackageNo(packageNo);
        tepGenModelInfoEO.setControllerMethodName(controllerMethod);
        tepGenModelInfoEO.setForReactYN("Y");
        Map<String, TepGenModelInfoEO> controllerMap = new LinkedHashMap<>();
        coreGenerateMapper.retrieveTepGenModelInfoListAll(tepGenModelInfoEO)
            .forEach(tepGenModelInfoEO1 -> {
                controllerMap.put(tepGenModelInfoEO1.getDatasetName(), tepGenModelInfoEO1);
            });

        AtomicReference<String> tsMainTemplateString = new AtomicReference<>(
            getTemplateSqlStmtString("reactApi"));
        tsMainTemplateString.set(
            tsMainTemplateString.get().replace("//@methodName", controllerMethod)
                .replace("//@fullUrlString", fullUrl)
                .replace("@errorApiName", fullUrl.replace("/", "Api.")));

        AtomicReference<String> finalTsMainTemplateString = tsMainTemplateString;
        StringBuilder hasDatasetString = new StringBuilder("true");
        StringBuilder dasetConstString = new StringBuilder("");
        StringBuilder reactApiDatasetReturnString = new StringBuilder("");
        StringBuilder importModelString = new StringBuilder("");

        controllerMap.forEach((datasetName, rowEO) -> {
            if (!datasetName.endsWith("ConditionDataset")) {
                finalTsMainTemplateString.set(finalTsMainTemplateString.get()
                    .replace("//@requestParamInterfaeName", rowEO.getApiInterfaceParam())
                    .replace("//@ApiInterfaceRespData", rowEO.getApiInterfaceRespData()));

                String hasDatasetTemplateString = getTemplateSqlStmtString("reactApiHasDataset");
                hasDatasetTemplateString = hasDatasetTemplateString.replace("@datasetName",
                    datasetName);
                hasDatasetString.append(hasDatasetTemplateString);
                reactApiDatasetReturnString.append(
                    getTemplateSqlStmtString("reactApiDatasetReturnString").replace("@datasetName",
                        datasetName));
                dasetConstString.append(
                    getTemplateSqlStmtString("reactApiDasetConstString").replace("@datasetName",
                        datasetName).replace("@interfaceRowName", rowEO.getInterfaceName()));
                if (!rowEO.getLookupYn().equals("Y")) {
                    importModelString.append(",").append(rowEO.getInterfaceName());
                }

            }

        });

        tsMainTemplateString.set(tsMainTemplateString.get()
            .replace("//@hasDataset", hasDatasetString.toString().replace("true&&", ""))
            .replace("@dasetConstString", dasetConstString)
            .replace("//@datasetListString", reactApiDatasetReturnString)
            .replace("//@importModelString", importModelString));

        returnString.append(tsMainTemplateString);
        return returnString.toString();
    }


    private String getReactUtilString(Long packageNo) {
        StringBuilder returnString = new StringBuilder("");
        TepGenModelInfoEO tepGenModelInfoEO = new TepGenModelInfoEO();
        tepGenModelInfoEO.setPackageNo(packageNo);
        tepGenModelInfoEO.setLookupYn("N");
        tepGenModelInfoEO.setForReactYN("Y");
        Map<String, TepGenModelInfoEO> interfaceNameMap = new HashMap<>();
        Map<String, String> utilMethodMap = new HashMap<>();
        Map<String, String> apiInterfaceParamMap = new HashMap<>();
        String a = "1";
        coreGenerateMapper.retrieveTepGenModelInfoListAll(tepGenModelInfoEO).stream().filter(
                tepGenModelInfoEO1 -> isNotNullAndEmpty(tepGenModelInfoEO1.getControllerMethodName()))
            .forEach(tepGenModelInfoEO1 -> {
                if (isNotNullAndEmpty(tepGenModelInfoEO1.getInterfaceName())) {
                    interfaceNameMap.put(tepGenModelInfoEO1.getInterfaceName(),
                        tepGenModelInfoEO1);
                }
                if (isNotNullAndEmpty(tepGenModelInfoEO1.getUtilApiGetMethodName())) {
                    utilMethodMap.put(tepGenModelInfoEO1.getUtilApiGetMethodName(),
                        tepGenModelInfoEO1.getApiInterfaceParam());
                }
                if (isNotNullAndEmpty(tepGenModelInfoEO1.getApiInterfaceParam())) {
                    apiInterfaceParamMap.put(tepGenModelInfoEO1.getApiInterfaceParam(), "S");
                }

            });

        String tsMainTemplateString = getTemplateSqlStmtString("reactUtil");
        StringBuilder apiString = new StringBuilder("");
        StringBuilder getInstanceString = new StringBuilder("");
        AtomicReference<String> importString = new AtomicReference<>("");
        interfaceNameMap.forEach((key, rowEO) -> {
            importString.set(importString + key + ",");
            importString.set(importString + key + "Factor,");

            String reactUtilGetInstanceMethod = getTemplateSqlStmtString(
                "reactUtilGetInstanceMethod");
            StringBuilder factorNullString = new StringBuilder("");
            TepGenModelInfoEO innerConditionEO = new TepGenModelInfoEO();
            innerConditionEO.setPackageNo(packageNo);
            innerConditionEO.setInterfaceName(key);
            innerConditionEO.setForReactYN("Y");
            coreGenerateMapper.retrieveTepGenModelInfoListAll(innerConditionEO)
                .forEach((innerRowEO) -> {
                    factorNullString.append(getNewLineString()).append("returnVal.")
                        .append(innerRowEO.getMemberName()).append("=null;");
                });

            reactUtilGetInstanceMethod = reactUtilGetInstanceMethod.replace("@getFactorMethodName",
                    rowEO.getUtilGetFactorMethodName())
                .replace("@factorInterfaceName", key + "Factor")
                .replace("@getObjectMethodName", rowEO.getUtilGetObjectMethodName())
                .replace("@obejctInterfaceName", key)
                .replace("//@factorNullString", factorNullString.toString());
            getInstanceString.append(getNewLineString()).append(reactUtilGetInstanceMethod);
        });
        tsMainTemplateString = tsMainTemplateString.replace("//@genGetMethod",
            getInstanceString.toString());

        apiInterfaceParamMap.forEach((key, dummy) -> {
            importString.set(importString + key + ",");
        });
        tsMainTemplateString = tsMainTemplateString.replace("@interfaceName",
            importString.toString());

        StringBuilder contentString = new StringBuilder("");
        utilMethodMap.forEach((utilApiGetMethodName, apiInterfaceParam) -> {
            String reactUtilContentApiInstance = getTemplateSqlStmtString(
                "reactUtilContentApiInstance");
            reactUtilContentApiInstance = reactUtilContentApiInstance.replace(
                "@utilApiGetMethodName", utilApiGetMethodName);
            reactUtilContentApiInstance = reactUtilContentApiInstance.replace("@apiInterfaceParam",
                apiInterfaceParam);

            TepGenModelInfoEO condtionFindEO = new TepGenModelInfoEO();
            condtionFindEO.setPackageNo(packageNo);
            condtionFindEO.setUtilApiGetMethodName(utilApiGetMethodName);
            condtionFindEO.setForReactYN("Y");
            String conditionType = coreGenerateMapper.retrieveTepGenModelInfoListAll(condtionFindEO)
                .stream().filter(
                    tepGenModelInfoEO1 -> isNotNullAndEmpty(tepGenModelInfoEO1.getVoName())
                        && tepGenModelInfoEO1.getVoName().contains("ConditionVO")).findFirst()
                .orElseGet(() -> {
                    return coreGenerateMapper.retrieveTepGenModelInfoListAll(condtionFindEO)
                        .stream().findFirst().get();
                }).getInterfaceName();
            reactUtilContentApiInstance = reactUtilContentApiInstance.replace("@conditionType",
                conditionType);

            StringBuilder datasetString = new StringBuilder("");
            StringBuilder reqDatasetString = new StringBuilder("");
            LinkedHashMap<String, String> datasetMap = new LinkedHashMap<>();
            coreGenerateMapper.retrieveTepGenModelInfoListAll(condtionFindEO).stream().filter(
                    tepGenModelInfoEO1 -> isEmpty(tepGenModelInfoEO1.getVoName())
                        || !tepGenModelInfoEO1.getVoName().contains("ConditionVO"))
                .forEach(tepGenModelInfoEO1 -> {
                    datasetMap.put(tepGenModelInfoEO1.getDatasetName(),
                        tepGenModelInfoEO1.getInterfaceName());
                });
            datasetMap.forEach((dataSetName, interfaceName) -> {
                datasetString.append(getNewLineString()).append(getTabString(2)).append(
                    getTemplateSqlStmtString("reactUtilApiRespDemoContents").replace("@datasetName",
                        dataSetName).replace("@interfaceName", interfaceName));
                reqDatasetString.append(getNewLineString()).append(getTabString(2)).append(
                    getTemplateSqlStmtString("reactUtilApiReqDemoContents").replace("@datasetName",
                        dataSetName).replace("@interfaceName", interfaceName));


            });
            if (apiInterfaceParam.startsWith("ISave")) {
                reactUtilContentApiInstance = reactUtilContentApiInstance.replace("/*", "")
                    .replace("*/", "");
            }
            apiString.append(getNewLineString()).append(
                reactUtilContentApiInstance.replace("@apiRespDemoContents", datasetString)
                    .replace("@apiReqDemoContents", reqDatasetString));

        });

        contentString.append(apiString.toString());

        returnString = new StringBuilder(
            tsMainTemplateString.replace("//@genContents", contentString));
        return returnString.toString();
    }

    public void doLookupJob(Long packageNo, String packageName, String methodType,
        String methodName, String sqlStmt, String voName, String controllerYn, String serviceYn,
        String initYn, Long initOrderSeq, String lookupType, String controllerMethodName,
        Long datasetSeq, Long masterSeq) {
        if (isEmpty(methodName)) {
            methodName =
                "retrieve" + CaseUtils.toCamelCase(lookupType.toLowerCase(Locale.ROOT), true, '_');
        }
//        insertSqlServiceMethod(packageNo, packageName, voName, voConditionName, methodName);
        insertLookupServiceMethod(packageNo, packageName, methodName, lookupType, masterSeq);
        insertControllerUnitForLookup(packageNo, packageName, controllerMethodName, methodName,
            lookupType, datasetSeq);
//        insertControllerUnitForSql(packageNo, packageName,
//            isNotEmpty(controllerMethodName) ? controllerMethodName : methodName, methodName,
//            voConditionName, voName, datasetSeq == null ? 0 : datasetSeq);
        if (isNotNullAndEmpty(controllerMethodName)) {
            TepGenModelInfoEO tepGenModelInfoEO = new TepGenModelInfoEO();
            tepGenModelInfoEO.setPackageNo(packageNo);
            tepGenModelInfoEO.setPackageName(packageName);
            tepGenModelInfoEO.setDatasetName(
                CaseUtils.toCamelCase(lookupType.toLowerCase(Locale.ROOT), false, '_') + "Dataset");
            tepGenModelInfoEO.setLookupYn("Y");
            tepGenModelInfoEO.setNullYn("Y");
            tepGenModelInfoEO.setInterfaceName("ILookupData");
            tepGenModelInfoEO.setLookupType(lookupType);
            tepGenModelInfoEO.setControllerMethodName(controllerMethodName);
            tepGenModelInfoEO.setControllerDatasetMethodSeq(datasetSeq == null ? 0 : datasetSeq);
            tepGenModelInfoEO.setApiInterfaceParam(
                "I" + upperCaseFirst(controllerMethodName) + "ApiReqParam");
            tepGenModelInfoEO.setUtilApiGetMethodName(
                "getNew" + upperCaseFirst(controllerMethodName) + "ApiReqInstance");
            tepGenModelInfoEO.setApiInterfaceRespData(
                "I" + upperCaseFirst(controllerMethodName) + "ApiRespData");
            tepGenModelInfoEO.setMasterSeq(masterSeq);
//            tepGenModelInfoEO.setUtilGetFactorMethodName("getNew"+ replaceLast(eoClassName, "VO", "")+"FactorObject");
//            tepGenModelInfoEO.setUtilGetFactorMethodName("getNew"+ replaceLast(eoClassName, "VO", "")+"Object");
            coreGenerateMapper.insertTepGenModelInfoList(List.of(tepGenModelInfoEO));
        }
    }


    public void doSqlJob(Long packageNo, String packageName, String methodType, String methodName,
        String sqlStmt, String voName, String controllerYn, String serviceYn, String initYn,
        Long initOrderSeq, String lookupType, String controllerMethodName, Long datasetSeq,
        Long masterSeq) {
        //1. make VO File
        List<LinkedHashMap<String, Object>> result = b2bGenerateMapper.selectSqlStmt(sqlStmt);
        LinkedHashMap<String, Object> protoTypeMap = getMapProtoType(result);
        LinkedHashMap<String, String> resultMap = getTypeMap(protoTypeMap);
        voName = upperCaseFirst(voName);
        if (voName.length() < 3 || !voName.substring(voName.length() - 2, voName.length())
            .equals("VO")) {
            voName = voName + "VO";
        }
        makeVOFile(packageNo, packageName, voName, resultMap,
            isEmpty(controllerMethodName) ? methodName : controllerMethodName, datasetSeq,
            masterSeq);
        //2. makeConditionVO
        String voConditionName = replaceLast(voName, "VO", "ConditionVO");
        makeVOFile(packageNo, packageName, voConditionName, resultMap,
            isEmpty(controllerMethodName) ? methodName : controllerMethodName, datasetSeq,
            masterSeq);

        //3. insert mapper info
//        insertMapperMethodForSql(packageNo, packageName, tableName, eoName);
//        insertSqlMapperMethod(packageNo,packageName,voName,voConditionName,)
        insertMapperMethodForSql(packageNo, packageName, voName, voConditionName, sqlStmt,
            methodName, protoTypeMap);
        //3. insert service info
        insertSqlServiceMethod(packageNo, packageName, voName, voConditionName, methodName,
            masterSeq);
        //4. insert controller info
//        insertSqlServiceMethod(packageNo,packageName,voName,voConditionName,methodName);
//        insertControllerMethodForSql(packageNo, packageName, methodName, voConditionName, voName);
        insertControllerUnitForSql(packageNo, packageName,
            isNotEmpty(controllerMethodName) ? controllerMethodName : methodName, methodName,
            voConditionName, voName, datasetSeq == null ? 0 : datasetSeq);
    }

    public void doTableJob(Long packageNo, String packageName, String tableName, Long masterSeq) {
        //1 make EO File
        String methodName =
            "save" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_') + "List";
        String eoName = makeEOFile(packageNo, packageName, tableName, methodName, masterSeq);
        //2 insert mapper info
        insertMapperMethodForTable(packageNo, packageName, tableName, eoName);
        //3. insert service info
        insertServiceMethodForTable(packageNo, packageName, tableName, eoName, masterSeq);
        //4. insert controller info
        insertControllerMethodForTable(packageNo, packageName, tableName, eoName);
//        makeMapperXml(packageNo, packageName);
//        makeMapperJava(packageNo, packageName);
//        makeServiceJava(packageNo, packageName);
//        makeServiceImplJava(packageNo, packageName);
//        makeControllerJava(packageNo, packageName);
//        getSaveMethodString(packageNo, tableName, eoName, "save");
    }


    public void doComplexJob(TepGenMasterInfoEO tepGenMasterInfoEO) {
        String eoName = makeEOFile(tepGenMasterInfoEO.getPackageNo(),
            tepGenMasterInfoEO.getPackageName(), tepGenMasterInfoEO.getTableName(),
            null, tepGenMasterInfoEO.getMasterSeq());
        insertMapperMethodForComplex(tepGenMasterInfoEO.getPackageNo(),
            tepGenMasterInfoEO.getPackageName(), tepGenMasterInfoEO.getTableName(),
            tepGenMasterInfoEO.getVoName(), eoName);
        insertServiceMethodForComplex(tepGenMasterInfoEO.getPackageNo(),
            tepGenMasterInfoEO.getPackageName(), tepGenMasterInfoEO.getTableName(),
            tepGenMasterInfoEO.getVoName(), eoName, tepGenMasterInfoEO.getMasterSeq());
//        insertControllerMethodForComplex(packageNo, packageName, tableName, voName);
        updateModelInfoForComplex(tepGenMasterInfoEO);

    }

    private void updateModelInfoForComplex(TepGenMasterInfoEO tepGenMasterInfoEO) {
        coreGenerateMapper.retrieveTepGenModelInfoListAll(TepGenModelInfoEO.builder()
            .packageNo(tepGenMasterInfoEO.getPackageNo())
            .forReactYN("Y")
            .masterSeq(tepGenMasterInfoEO.getMasterSeq())
            .build()).forEach((tepGenModelInfoEO ->
            {
                tepGenModelInfoEO.setControllerSaveMethodSeq(
                    tepGenMasterInfoEO.getControllerSaveMethodSeq());
                tepGenModelInfoEO.setControllerSaveMethodName(
                    tepGenMasterInfoEO.getControllerSaveMethodName());
                tepGenModelInfoEO.setSaveApiInterfaceParam(
                    "I" + upperCaseFirst(tepGenMasterInfoEO.getControllerSaveMethodName())
                        + "ApiReqParam");
                tepGenModelInfoEO.setSaveUtilApiGetMethodName(
                    "getNew" + upperCaseFirst(tepGenMasterInfoEO.getControllerSaveMethodName())
                        + "ApiReqInstance");
                coreGenerateMapper.updateTepGenModelInfoList(List.of(tepGenModelInfoEO));

            }
            )

        );

    }

//    private void insertControllerUnitForComplex(Long packageNo, String packageName,
//        String controllerMethodName, String methodName, String conditionVOName, String voName,
//        Long datasetSeq) {
//        //insert method 만들기
//        String controllerPackageName = packageName + ".controller";
//        String methodAccessor = "public";
//        String controllerClassName =
//            upperCaseFirst(lastIndexString(packageName, ".")) + "Controller";
//
//        String methodAnnotationName = null;
//        String methodReturnClassName = "ResponseModel";
//        String methodParamClassName = "@RequestBody RequestModel";
//        String methodParamInstantName = "requestModel";
//        String methodContents = getControllerUnitSqlStringForComplex(packageName, methodName, conditionVOName,
//            voName, datasetSeq);
//
//        coreGenerateMapper.insertTepGenControllerUnitMethodList(List.of(
//            TepGenControllerUnitMethodEO.builder().controllerPackageName(controllerPackageName)
//                .packageNo(packageNo).controllerClassName(controllerClassName)
//                .methodAnnotationName(methodAnnotationName).methodAccessor(methodAccessor)
//                .controllerMethodName(controllerMethodName)
//                .methodReturnClassName(methodReturnClassName).methodName(methodName)
//                .methodParamClassName(methodParamClassName)
//                .methodParamInstantName(methodParamInstantName).methodContents(methodContents)
//                .datasetSeq(datasetSeq).build()));
//    }


    private String insertMapperMethodForComplex(Long packageNo, String packageName,
        String tableName,
        String voName, String eoName) {
        String mapperXmlFileName = "";
        String mapperPackageName = packageName + ".mapper";
        String mapperXmlName = upperCaseFirst(lastIndexString(packageName, ".")) + "Mapper_SQL.xml";
        String mapperClassName = upperCaseFirst(lastIndexString(packageName, ".")) + "Mapper";
        String methodAnnotationName = "@Transactional";
        String methodParamClassName = "List<" + voName + ">";
        String methodParamInstantName = lowerCaseFirst(voName) + "List";
        //insert method 만들기
        insertInsertMapperMethodForComplex(packageNo, packageName, tableName, voName,
            mapperPackageName,
            mapperXmlName, mapperClassName, methodAnnotationName, methodParamClassName,
            methodParamInstantName);
        //update method 만들기
        insertUpdateMapperMethodForComplex(packageNo, packageName, tableName, voName,
            mapperPackageName,
            mapperXmlName, mapperClassName, methodAnnotationName, methodParamClassName,
            methodParamInstantName);
//        //delete method 만들기
        insertDeleteMapperMethodForComplex(packageNo, packageName, tableName, voName,
            mapperPackageName,
            mapperXmlName, mapperClassName, methodAnnotationName, methodParamClassName,
            methodParamInstantName);
//        //select by pk 만들기
//        insertSelectByPkMapperMethod(packageNo, packageName, tableName, eoName, mapperPackageName,
//            mapperXmlName, mapperClassName, methodAnnotationName, methodParamClassName,
//            methodParamInstantName);
//        //select all 만들기
        insertSelectListMapperMethodForComplex(packageNo, packageName, tableName, eoName,
            mapperPackageName,
            mapperXmlName, mapperClassName, methodAnnotationName, eoName, lowerCaseFirst(eoName),
            "List<" + eoName + ">");
        //validation

        return mapperXmlFileName;
    }

    private void insertSelectListMapperMethodForComplex(Long packageNo, String packageName, String
        tableName,
        String eoName, String mapperPackageName, String mapperXmlName, String mapperClassName,
        String methodAnnotationName, String methodParamClassName, String methodParamInstantName,
        String methodReturnClassName) {
        //insert method 만들기
        String methodName =
            "retrieve" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_')
                + "ListAll";
        String xmlMethodType = "select";
        String sqlStmt = getSelectListString(packageName, tableName, eoName, methodName);

        if (getListSize(coreGenerateMapper.retrieveTepGenMapperMethodInfo(
            TepGenMapperMethodInfoConditionVO.builder()
                .packageNo(packageNo)
                .xmlMethodType(xmlMethodType)
                .methodName(methodName)
                .build())) == 0) {
            coreGenerateMapper.insertMultiTepGenMapperMethodInfo(List.of(
                TepGenMapperMethodInfoEO.builder().mapperPackageName(mapperPackageName)
                    .packageNo(packageNo).mapperXmlName(mapperXmlName)
                    .mapperClassName(mapperClassName)
                    .methodAnnotationName(methodAnnotationName).methodName(methodName)
                    .methodParamClassName(methodParamClassName)
                    .methodParamInstantName(methodParamInstantName).xmlMethodType(xmlMethodType)
                    .tableName(tableName).methodReturnClassName(methodReturnClassName)
                    .sqlStmt(sqlStmt)
                    .build()));
        }
    }

    private void insertDeleteMapperMethodForComplex(Long packageNo, String packageName,
        String tableName,
        String voName, String mapperPackageName, String mapperXmlName, String mapperClassName,
        String methodAnnotationName, String methodParamClassName, String methodParamInstantName) {
        //insert method 만들기
        String methodName =
            "delete" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_')
                + "List";
        String xmlMethodType = "delete";
        String sqlStmt = getDeleteStringForComplex(packageNo, packageName, tableName, voName,
            methodName);

        coreGenerateMapper.insertMultiTepGenMapperMethodInfo(List.of(
            TepGenMapperMethodInfoEO.builder().mapperPackageName(mapperPackageName)
                .packageNo(packageNo).mapperXmlName(mapperXmlName).mapperClassName(mapperClassName)
                .methodAnnotationName(methodAnnotationName).methodName(methodName)
                .methodParamClassName(methodParamClassName)
                .methodParamInstantName(methodParamInstantName).xmlMethodType(xmlMethodType)
                .tableName(tableName).sqlStmt(sqlStmt).build()));
    }

    private String getDeleteStringForComplex(Long packageNo, String packageName, String tableName,
        String voName,
        String methodName) {
        String returnString = "";
        String templateString = getTemplateSqlStmtString("MapperXmlDelete");
//        String methodName =
//            "insertMulti" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_');
        //LG CNS Co., Ltd.~  5000 User License
        //GIJWD-MQIJY-OLQWY-KKEMR-PCQMK-KAIKU-NQONU-TIJMS
        String eoFullPathName = packageName.toLowerCase(Locale.ROOT) + "." + "model." + voName;

        SchemaColumnConditionVO schemaColumnConditionVO = new SchemaColumnConditionVO();
        schemaColumnConditionVO.setTableName(tableName);

        List<SchemaColumnVO> schemaColumnVOList = retrieveColumnSchema(schemaColumnConditionVO);
        schemaColumnVOList = convertValidColumnVO(packageNo, voName, schemaColumnVOList);
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

    private void insertUpdateMapperMethodForComplex(Long packageNo, String packageName,
        String tableName,
        String voName, String mapperPackageName, String mapperXmlName, String mapperClassName,
        String methodAnnotationName, String methodParamClassName, String methodParamInstantName) {
        //insert method 만들기
        String methodName =
            "update" + voName
                + "List";
        String xmlMethodType = "update";
        String sqlStmt = getUpdateStringForComplex(packageNo, packageName, tableName, voName,
            methodName);

        coreGenerateMapper.insertMultiTepGenMapperMethodInfo(List.of(
            TepGenMapperMethodInfoEO.builder().mapperPackageName(mapperPackageName)
                .packageNo(packageNo).mapperXmlName(mapperXmlName).mapperClassName(mapperClassName)
                .methodAnnotationName(methodAnnotationName).methodName(methodName)
                .methodParamClassName(methodParamClassName)
                .methodParamInstantName(methodParamInstantName).xmlMethodType(xmlMethodType)
                .tableName(tableName).sqlStmt(sqlStmt).build()));
    }

    private String getUpdateStringForComplex(Long packageNo, String packageName, String tableName,
        String voName,
        String methodName) {
        String returnString = "";
        String templateString = getTemplateSqlStmtString("MapperXmlUpdate");
//        String methodName =
//            "insertMulti" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_');
        String eoFullPathName = packageName.toLowerCase(Locale.ROOT) + "." + "model." + voName;

        SchemaColumnConditionVO schemaColumnConditionVO = new SchemaColumnConditionVO();
        schemaColumnConditionVO.setTableName(tableName);

        List<SchemaColumnVO> schemaColumnVOList = retrieveColumnSchema(schemaColumnConditionVO);
        schemaColumnVOList = convertValidColumnVO(packageNo, voName, schemaColumnVOList);

        StringBuilder matchString = new StringBuilder("");
        StringBuilder whereString = new StringBuilder("");
        if (isNotNullAndEmpty(schemaColumnVOList)) {
            final int[] inx = {0};
            final int[] whereInx = {0};
            schemaColumnVOList.stream().filter(schemaColumnVO ->
                !schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT).equals("created_by")
                    && !schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT)
                    .equals("creation_date")).forEach(schemaColumnVO -> {

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
                    .equals("last_update_date")) {
                    matchString.append("now()");
                } else {
                    matchString.append("#{item.");
                    matchString.append(CaseUtils.toCamelCase(
                        schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT), false, '_'));
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

    private void insertInsertMapperMethodForComplex(Long packageNo, String packageName,
        String tableName,
        String voName, String mapperPackageName, String mapperXmlName, String mapperClassName,
        String methodAnnotationName, String methodParamClassName, String methodParamInstantName) {
        //insert method 만들기
        String methodName =
            "insert" + voName
                + "List";
        String xmlMethodType = "insert";
        String sqlStmt = getInsertStringForComplex(packageNo, packageName, tableName, voName,
            methodName);

        coreGenerateMapper.insertMultiTepGenMapperMethodInfo(List.of(
            TepGenMapperMethodInfoEO.builder().mapperPackageName(mapperPackageName)
                .packageNo(packageNo).mapperXmlName(mapperXmlName).mapperClassName(mapperClassName)
                .methodAnnotationName(methodAnnotationName).methodName(methodName)
                .methodParamClassName(methodParamClassName)
                .methodParamInstantName(methodParamInstantName).xmlMethodType(xmlMethodType)
                .tableName(tableName).sqlStmt(sqlStmt).build()));
    }

    private List<SchemaColumnVO> convertValidColumnVO(Long packageNo, String voName,
        List<SchemaColumnVO> schemaColumnVOList) {
        List<SchemaColumnVO> returnList = new ArrayList<>();
        if (isNotNullAndEmpty(schemaColumnVOList) && isNotNullAndEmpty(voName)) {
            //model 정보 조회
            Map<String, TepGenModelInfoEO> voMap = coreGenerateMapper.retrieveTepGenModelInfoListAll(
                    TepGenModelInfoEO.builder()
                        .packageNo(packageNo)
                        .voName(voName)
                        .build()).stream()
                .collect(Collectors.toMap(TepGenModelInfoEO::getMemberName, Function.identity()));

            returnList = schemaColumnVOList.stream().filter(rowEO -> {
                return isNotNullAndEmpty(
                    voMap.get(CaseUtils.toCamelCase(rowEO.getColumnName(), false, '_')));
            }).collect(Collectors.toList());
        }
        return returnList;
    }

    private String getInsertStringForComplex(Long packageNo, String packageName, String tableName,
        String voName,
        String methodName) {
        String returnString = "";
        String templateString = getTemplateSqlStmtString("MapperXmlInsert");
//        String methodName =
//            "insertMulti" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_');
        String eoFullPathName = packageName.toLowerCase(Locale.ROOT) + "." + "model." + voName;
        SchemaColumnConditionVO schemaColumnConditionVO = new SchemaColumnConditionVO();
        schemaColumnConditionVO.setTableName(tableName);
        List<SchemaColumnVO> schemaColumnVOList = retrieveColumnSchema(schemaColumnConditionVO);
        schemaColumnVOList = convertValidColumnVO(packageNo, voName, schemaColumnVOList);
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
                    || schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT)
                    .equals("last_update_date")) {
                    variableName.append("now()");
                } else {
                    variableName.append("#{item.");
                    variableName.append(CaseUtils.toCamelCase(
                        schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT), false, '_'));
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

    private void makeReactTSFile(Long packageNo, String packageName) {
        String tsString = getReactTsString(packageNo);
        String[] g = packageName.split("[.]");
        String lastPackName = g[g.length - 1];
        createFrontFile(packageName, upperCaseFirst(lastPackName) + ".types.ts", tsString);
        //file 생성정보
        coreGenerateMapper.insertMulti(List.of(
            TepGenFileInfoEO.builder().fileName(upperCaseFirst(lastPackName) + ".types.ts")
                .packageName(packageName).packageNo(packageNo).fileType("controller")
                .fileContents(tsString).build()));

    }

    private void makeReactApiFile(Long packageNo, String packageName) {

        String[] g = packageName.split("[.]");
        String lastPackName = g[g.length - 1];
        Map<String, String> controllerMethodMap = new HashMap<>();

        TepGenModelInfoEO conditionEO = new TepGenModelInfoEO();
        conditionEO.setPackageNo(packageNo);
        conditionEO.setForReactYN("Y");
        coreGenerateMapper.retrieveTepGenModelInfoListAll(conditionEO).stream().filter(
                tepGenModelInfoEO1 -> isNotNullAndEmpty(tepGenModelInfoEO1.getControllerMethodName()))
            .forEach((tepGenModelInfoEO -> {
                if (isNotNullAndEmpty(tepGenModelInfoEO.getControllerMethodName())) {
                    controllerMethodMap.put(tepGenModelInfoEO.getControllerMethodName(),
                        lastIndexString(packageName, ".") + "/"
                            + tepGenModelInfoEO.getControllerMethodName());
                }
            }));

        controllerMethodMap.forEach((controllerMethod, fullUrl) -> {
            String tsString = getReactApiString(packageNo, controllerMethod, fullUrl);
            createFrontApiFile(packageName, controllerMethod + ".apiform.ts", tsString);
            //file 생성정보
            coreGenerateMapper.insertMulti(List.of(
                TepGenFileInfoEO.builder().fileName(controllerMethod + ".apiform.ts")
                    .packageName(packageName).packageNo(packageNo).fileType(".apiform.ts")
                    .fileContents(tsString).build()));
        });


    }

    private void makeReactUtilFile(Long packageNo, String packageName) {
        String tsString = getReactUtilString(packageNo);
        String[] g = packageName.split("[.]");
        String lastPackName = g[g.length - 1];
        createFrontFile(packageName, upperCaseFirst(lastPackName) + ".util.ts", tsString);
        //file 생성정보
        coreGenerateMapper.insertMulti(List.of(
            TepGenFileInfoEO.builder().fileName(upperCaseFirst(lastPackName) + ".util.ts")
                .packageName(packageName).packageNo(packageNo).fileType(".util.ts")
                .fileContents(tsString).build()));

    }

    private void makeReactStoreFile(Long packageNo, String packageName) {
        String[] g = packageName.split("[.]");
        String lastPackName = upperCaseFirst(g[g.length - 1]);
        String tsString = getReactStoreString(packageNo, lastPackName);

        createFrontFile(packageName, upperCaseFirst(lastPackName) + ".store.ts", tsString);
        //file 생성정보
        coreGenerateMapper.insertMulti(List.of(
            TepGenFileInfoEO.builder().fileName(upperCaseFirst(lastPackName) + ".store.ts")
                .packageName(packageName).packageNo(packageNo).fileType(".store.ts")
                .fileContents(tsString).build()));

    }

    private String getReactStoreString(Long packageNo, String storeName) {
        String returnString = "";
        TepGenModelInfoEO tepGenModelInfoEO = new TepGenModelInfoEO();
        tepGenModelInfoEO.setPackageNo(packageNo);
        tepGenModelInfoEO.setForReactYN("Y");
//        tepGenModelInfoEO.setLookupYn("N");
        Map<String, TepGenModelInfoEO> interfaceNameMap = new LinkedHashMap<>();
        Map<String, TepGenModelInfoEO> datasetNameMap = new LinkedHashMap<>();
        Map<String, TepGenModelInfoEO> apiMethodMap = new LinkedHashMap<>();
        String a = "1";
        Map<String, String> fetchedDatasetMap = new HashMap<>();
        Map<String, String> interfaceFactorMap = new HashMap<>();
        Map<String, TepGenModelInfoEO> utilMethodMap = new HashMap<>();

        coreGenerateMapper.retrieveTepGenModelInfoListAll(tepGenModelInfoEO).stream().filter(
                tepGenModelInfoEO1 -> isNotNullAndEmpty(tepGenModelInfoEO1.getControllerMethodName()))
            .forEach(tepGenModelInfoEO1 -> {
                if (isNotNullAndEmpty(tepGenModelInfoEO1.getInterfaceName())) {
                    interfaceNameMap.put(tepGenModelInfoEO1.getInterfaceName(), tepGenModelInfoEO1);
                }
                if (isNotNullAndEmpty(tepGenModelInfoEO1.getDatasetName())) {
                    datasetNameMap.put(tepGenModelInfoEO1.getDatasetName(), tepGenModelInfoEO1);
                }
                if (isNotNullAndEmpty(tepGenModelInfoEO1.getControllerMethodName())) {
                    apiMethodMap.put(tepGenModelInfoEO1.getControllerMethodName(),
                        tepGenModelInfoEO1);
                }
                if (isNotNullAndEmpty(tepGenModelInfoEO1.getUtilApiGetMethodName())) {
                    utilMethodMap.put(tepGenModelInfoEO1.getUtilApiGetMethodName(),
                        tepGenModelInfoEO1);
                }
            });

        String tsMainTemplateString = getTemplateSqlStmtString("reactStore");
        StringBuilder importModelString = new StringBuilder("");
        StringBuilder observable = new StringBuilder("");
        StringBuilder computed = new StringBuilder("");
        StringBuilder action = new StringBuilder("");
        StringBuilder importApiString = new StringBuilder();
        apiMethodMap.forEach((controllerMethodName, rowEO) -> {
            importApiString.append(controllerMethodName).append(",");
        });

        tsMainTemplateString = tsMainTemplateString.replace("@storeName", storeName + "Store");
        final Integer[] inx = {0};
        interfaceNameMap.forEach((interfaceName, rowEO) -> {
            if (rowEO.getLookupYn().equals("N")) {
                importModelString.append(interfaceName).append(",");
                importModelString.append(interfaceName).append("Factor,");
                interfaceFactorMap.put(interfaceName, interfaceName + "Factor");
            }
            if (inx[0] == 0) {
//                    if (isNotNullAndEmpty(rowEO.getUtilApiGetMethodName())) {
//                        importModelString.append(rowEO.getUtilApiGetMethodName()).append(",");
//                    }
                if (isNotNullAndEmpty(rowEO.getApiInterfaceParam())) {
                    importModelString.append(rowEO.getApiInterfaceParam()).append(",");
                }
                if (isNotNullAndEmpty(rowEO.getApiInterfaceRespData())) {
                    importModelString.append(rowEO.getApiInterfaceRespData()).append(",");
                }

            }
            if (isNotNullAndEmpty(rowEO.getUtilGetFactorMethodName())) {
                importModelString.append(rowEO.getUtilGetFactorMethodName()).append(",");
            }
            if (isNotNullAndEmpty(rowEO.getUtilGetObjectMethodName())) {
                importModelString.append(rowEO.getUtilGetObjectMethodName()).append(",");
            }
            //observable
            observable.append(getNewLineString()).append(getTabString(1))
                .append(rowEO.getDatasetName()).append(" : ").append(rowEO.getInterfaceName())
                .append(rowEO.getDatasetName().endsWith("ConditionDataset") ? "=" : "[]=").append(
                    rowEO.getDatasetName().endsWith("ConditionDataset") ?
                        rowEO.getUtilGetObjectMethodName() + "();"
                        : "[] as ").append(rowEO.getDatasetName().endsWith("ConditionDataset") ? ""
                    : rowEO.getInterfaceName() + "[];");

            if (!rowEO.getDatasetName().endsWith("ConditionDataset")) {
                String fetchedDatasetName = replaceLast(rowEO.getDatasetName(), "Dataset",
                    "FetchedList");
                fetchedDatasetMap.put(rowEO.getDatasetName(), fetchedDatasetName);
                observable.append(getNewLineString()).append(getTabString(1))
                    .append(fetchedDatasetName).append(" : ").append(rowEO.getInterfaceName())
                    .append("[]=").append("[] as ").append(rowEO.getInterfaceName()).append("[];");
            }

            inx[0]++;
        });
        utilMethodMap.forEach((methodName, rowEO) -> {
            importModelString.append(methodName).append(",");
        });
        //computed
        datasetNameMap.forEach((datasetName, rowEO) -> {
            if (!datasetName.endsWith("ConditionDataset")) {
                String reactStoreJsonMethod = getTemplateSqlStmtString("reactStoreJsonMethod");
                String interfaceName = rowEO.getInterfaceName();
                String factorInterfaceName = interfaceFactorMap.get(interfaceName);
                if (isNotNullAndEmpty(factorInterfaceName) && isNotNullAndEmpty(
                    factorInterfaceName)) {
                    String datasetMethod = reactStoreJsonMethod.replace("@datasetName", datasetName)
                        .replace("@factorName", factorInterfaceName)
                        .replace("@getUtilGetObjectMethodName", rowEO.getUtilGetFactorMethodName());
                    computed.append(getNewLineString()).append(datasetMethod);
                }
                String fetchedListName = fetchedDatasetMap.get(datasetName);

                if (isNotNullAndEmpty(fetchedListName) && isNotNullAndEmpty(factorInterfaceName)) {
                    String fetchedListMethod = reactStoreJsonMethod.replace("@datasetName",
                            fetchedListName).replace("@factorName", factorInterfaceName)
                        .replace("@getUtilGetObjectMethodName", rowEO.getUtilGetFactorMethodName());
                    computed.append(getNewLineString()).append(fetchedListMethod);

                    String reactStoreUpdateCudsMethod = getTemplateSqlStmtString(
                        "reactStoreUpdateCudsMethod");
                    reactStoreUpdateCudsMethod = reactStoreUpdateCudsMethod.replace(
                            "@upperDasetName", upperCaseFirst(datasetName))
                        .replace("@datasetName", datasetName)
                        .replace("@factorName", factorInterfaceName)
                        .replace("@fetchListName", fetchedListName)
                        .replace("@interfaceName", rowEO.getInterfaceName()
                        )
                        .replace("@getUtilGetObjectMethodName", rowEO.getUtilGetObjectMethodName());

                    action.append(getNewLineString()).append(reactStoreUpdateCudsMethod);
                }
            }
        });
        //api
        StringBuilder apiString = new StringBuilder("");
        apiMethodMap.forEach((controllerMethodName, rowEO) -> {
            String reactStoreApiMethod = getTemplateSqlStmtString("reactStoreApiMethod");
            reactStoreApiMethod = reactStoreApiMethod.replace("@apiMethodName",
                    controllerMethodName)
                .replace("@utilApiGetMethodName", rowEO.getUtilApiGetMethodName());
            TepGenModelInfoEO conditionEO = new TepGenModelInfoEO();
            conditionEO.setPackageNo(rowEO.getPackageNo());
            conditionEO.setControllerMethodName(rowEO.getControllerMethodName());
            conditionEO.setForReactYN("Y");
            String conditionDatasetName = coreGenerateMapper.retrieveTepGenModelInfoListAll(
                    conditionEO).stream().filter(
                    (conditionRowEO) -> isNotNullAndEmpty(conditionRowEO.getVoName())
                        && conditionRowEO.getVoName().endsWith("ConditionVO")).findFirst()
                .orElseGet(TepGenModelInfoEO::new).getDatasetName();
            if (isNotNullAndEmpty(conditionDatasetName)) {
                reactStoreApiMethod = reactStoreApiMethod.replace("//requestData.conditions",
                        "requestData.conditions")
                    .replace("@conditionDatasetName", conditionDatasetName);
            } else {
                reactStoreApiMethod = reactStoreApiMethod.replace(
                    "//requestData.conditions = this.@conditionDatasetName;", "");
            }
            Map<String, TepGenModelInfoEO> apiDatasetMap = new LinkedHashMap<>();
            coreGenerateMapper.retrieveTepGenModelInfoListAll(conditionEO).forEach((innerEO) -> {
                apiDatasetMap.put(innerEO.getDatasetName(), innerEO);
            });
            StringBuilder dataSetContentsString = new StringBuilder("");
            StringBuilder storeApiSuccessContentsString = new StringBuilder("");

            apiDatasetMap.forEach((innerDatasetName, innerEO) -> {
                String reactStoreDataSetContents = getTemplateSqlStmtString(
                    "reactStoreDataSetContents");
                reactStoreDataSetContents = reactStoreDataSetContents.replace(
                        "@controllerDatasetMethodSeq",
                        innerEO.getControllerDatasetMethodSeq() == null ? "0"
                            : innerEO.getControllerDatasetMethodSeq().toString())
                    .replace("@datasetName", innerDatasetName);
                if (!innerEO.getControllerMethodName().startsWith("save")) {
                    reactStoreDataSetContents = "//" + reactStoreDataSetContents;
                }
                if (!nullToEmpty(innerEO.getVoName()).endsWith("ConditionVO")) {
                    dataSetContentsString.append(getNewLineString())
                        .append(reactStoreDataSetContents);
                }

                if (!nullToEmpty(innerEO.getVoName()).endsWith("ConditionVO")) {
                    String reactStoreApiSuccessContents = getTemplateSqlStmtString(
                        "reactStoreApiSuccessContents");
                    reactStoreApiSuccessContents = reactStoreApiSuccessContents.replace(
                        "@datasetName", innerDatasetName);
                    String fetchedListName = fetchedDatasetMap.get(innerDatasetName);
                    if (isEmpty(fetchedListName)) {
                        reactStoreApiSuccessContents = reactStoreApiSuccessContents.replace(
                            "//this.@datasetFetchList = _.cloneDeep(data.processResult.@datasetName);",
                            "");
                    } else {
                        reactStoreApiSuccessContents = reactStoreApiSuccessContents.replace(
                            "@datasetFetchList", fetchedListName).replace("//", "");
                    }
                    storeApiSuccessContentsString.append(getNewLineString())
                        .append(reactStoreApiSuccessContents);
                }

            });
            reactStoreApiMethod = reactStoreApiMethod.replace("@dataSetContents",
                    dataSetContentsString)
                .replace("@storeApiSuccessContents", storeApiSuccessContentsString);
            apiString.append(getNewLineString()).append(reactStoreApiMethod);
        });

        action.append(apiString);

        returnString = tsMainTemplateString.replace("@importModelString",
            importModelString.toString());
        returnString = returnString.replace("@importApiString", importApiString.toString());
        returnString = returnString.replace("//@observable", observable.toString());
        returnString = returnString.replace("//@computed", computed.toString());
        returnString = returnString.replace("//@action", action.toString());

        return returnString;
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
//            String controllerFullPath = controllerPackage.get();
            String controllerFullPath = packageName.toLowerCase(Locale.ROOT) + ".controller";
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

            createFile(packageName, controllerClassName.get() + ".java", controllerJavaString,
                "controller");
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
        String controllerMethodName, String methodName, String lookupType, Long datasetSeq) {
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
                .datasetSeq(datasetSeq).build()));
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
        String voInstantName =
            CaseUtils.toCamelCase(lookupType.toLowerCase(Locale.ROOT), false, '_') + "LookupVO";

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
        String controllerMethodName, String methodName, String conditionVOName, String voName,
        Long datasetSeq) {
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
                .datasetSeq(datasetSeq).build()));
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
                TepGenControllerUnitMethodEO.builder().packageNo(packageNo)
                    .controllerMethodName(controllerMethodName).build()).stream()
            .forEach(tepGenControllerUnitMethodEO -> {
                conditionUnitString.append(getNewLineString()).append(getTabString(2))
                    .append(tepGenControllerUnitMethodEO.getMethodContents());
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
        return "import " + tepGenServiceMethodInfoEO.getServicePackageName()
            .toLowerCase(Locale.ROOT) + "." + tepGenServiceMethodInfoEO.getServiceClassName() + ";";
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
        String eoName, Long masterSeq) {
        String mapperXmlFileName = "";
        String servicePackageName = packageName + ".service";
        String serviceClassName = upperCaseFirst(lastIndexString(packageName, ".")) + "Service";
        String methodAnnotationName = "@Transactional";
        String methodParamInstantName = lowerCaseFirst(eoName) + "List";
        //save method 만들기
        insertSaveServiceMethod(packageNo, servicePackageName, tableName, eoName,
            serviceClassName,
            methodAnnotationName, "public", methodParamInstantName, masterSeq);
        //validation
        insertValidationServiceMethod(packageNo, servicePackageName, tableName, eoName,
            serviceClassName, null, "private", methodParamInstantName, masterSeq);
//        getValidationString(packageNo,tableName,eoName,"AA",eoName,methodParamInstantName);

        return mapperXmlFileName;
    }

    private void insertSaveServiceMethodForComplex(Long packageNo, String servicePackageName,
        String tableName, String voName, String serviceClassName, String methodAnnotationName,
        String method_accessor, String methodParamInstantName, Long masterSeq) {
        String methodName =
            "save" + voName + "List";
        String methodContents = getSaveMethodStringForComplex(packageNo, tableName, voName,
            methodName,
            voName, methodParamInstantName);
        String methodParamClassName = "List<" + voName + ">";
        coreGenerateMapper.insertMultiTepGenServiceMethodInfo(List.of(
            TepGenServiceMethodInfoEO.builder().packageNo(packageNo)
                .servicePackageName(servicePackageName).serviceClassName(serviceClassName)
                .methodAccessor(method_accessor).methodAnnotationName(methodAnnotationName)
                .methodReturnClassName("void").methodName(methodName)
                .methodParamClassName(methodParamClassName)
                .methodParamInstantName(methodParamInstantName).methodContents(methodContents)
                .tableName(tableName).addDatasetParam("Y").masterSeq(masterSeq).build()));
    }

    private String getSaveMethodStringForComplex(Long packageNo, String tableName, String voName,
        String methodName, String methodParamClassName, String methodParamInstantName) {
        String returnString = "";
        //@methodName
        //@methodParamClassName
        //@methodParamInstantName
        //@loopEOInstance
        String loopEOInstance = lowerCaseFirst(voName);
        //@validationMethodName
        String validationMethodName = "validation" + voName;
        //@mapperInstanceName
        String mapperInstanceName = lowerCaseFirst(getMapperClassName(packageNo));
        //@mapperDeleteMethodName
        String mapperDeleteMethodName = getMapperMethodName(packageNo, tableName, "delete", voName);
        //@mapperUpdateMethodName
        String mapperUpdateMethodName = getMapperMethodName(packageNo, tableName, "update", voName);
        //@mapperInsertMethodName
        String mapperInsertMethodName = getMapperMethodName(packageNo, tableName, "insert", voName);
//        String templateString = getTemplateSqlStmtString("ServiceSaveMethod");
        String templateString = getTemplateSqlStmtString("ServiceSaveMethodNew");

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

    private String insertServiceMethodForComplex(Long packageNo, String packageName,
        String tableName,
        String voName, String eoName, Long masterSeq) {
        String mapperXmlFileName = "";
        String servicePackageName = packageName + ".service";
        String serviceClassName = upperCaseFirst(lastIndexString(packageName, ".")) + "Service";
        String methodAnnotationName = "@Transactional";
        String methodParamInstantName = lowerCaseFirst(voName) + "List";
        //save method 만들기
        insertSaveServiceMethodForComplex(packageNo, servicePackageName, tableName, voName,
            serviceClassName,
            methodAnnotationName, "public", methodParamInstantName, masterSeq);
        //validation
        insertValidationServiceMethodForComplex(packageNo, servicePackageName, tableName, voName,
            serviceClassName, null, "private", methodParamInstantName, eoName, masterSeq);
//        getValidationString(packageNo,tableName,eoName,"AA",eoName,methodParamInstantName);

        return mapperXmlFileName;
    }

    private void insertValidationServiceMethodForComplex(Long packageNo, String servicePackageName,
        String tableName, String voName, String serviceClassName, String methodAnnotationName,
        String methodAccessor, String methodParamInstantName, String eoName, Long masterSeq) {
        String methodName = "validation" + voName;
        ;
        String methodContents = getValidationStringForComplex(packageNo, tableName, voName,
            methodName,
            voName, methodParamInstantName, eoName);
        coreGenerateMapper.insertMultiTepGenServiceMethodInfo(List.of(
            TepGenServiceMethodInfoEO.builder().packageNo(packageNo)
                .servicePackageName(servicePackageName).serviceClassName(serviceClassName)
                .methodAccessor(methodAccessor).methodAnnotationName(methodAnnotationName)
                .methodReturnClassName("Boolean").methodName(methodName)
                .methodParamClassName(voName).methodParamInstantName(methodParamInstantName)
                .methodContents(methodContents).tableName(tableName).masterSeq(masterSeq).build()));
    }

    private String getValidationStringForComplex(Long packageNo, String tableName, String voName,
        String methodName, String methodParamClassName, String methodParamInstantName,
        String eoName) {
        String returnString = "";
        String loopEOInstance = lowerCaseFirst(voName);
        String mapperInstanceName = lowerCaseFirst(getMapperClassName(packageNo));

        String templateString = getTemplateSqlStmtString("ServiceValidationMethod");

        templateString = templateString.replace("@methodName", methodName);
        templateString = templateString.replace("@methodParamClassName", methodParamClassName);

        templateString = templateString.replace("@eoName", voName);
        templateString = templateString.replace("@loopEOInstance", loopEOInstance);
        templateString = templateString.replace("//@nullCheck",
            getNullValidationStringForComplex(packageNo, loopEOInstance, tableName, voName));
//        String a = getDupValidationStringForComplex(packageNo, voName, loopEOInstance, tableName);
        templateString = templateString.replace("//@dupCheck",
            getDupValidationStringForComplex(packageNo, voName, loopEOInstance, tableName, eoName));
        templateString = templateString.replace("@methodParamInstantName", methodParamInstantName);
        templateString = templateString.replace("@mapperInstanceName", mapperInstanceName);

        return templateString;
    }

    private List<TableConstraintsVO> convertTableConstraintsVOList(Long packageNo, String voName,
        List<TableConstraintsVO> tableConstraintsVOList) {
        List<TableConstraintsVO> resultList = new ArrayList<>();
        //모두 존재하는 키만
        Map<String, TepGenModelInfoEO> voMap = coreGenerateMapper.retrieveTepGenModelInfoListAll(
                TepGenModelInfoEO.builder()
                    .packageNo(packageNo)
                    .voName(voName)
                    .build()).stream()
            .collect(Collectors.toMap(TepGenModelInfoEO::getMemberName, Function.identity()));
        if (isNotNullAndEmpty(voMap) && isNotNullAndEmpty(tableConstraintsVOList)) {
            Map<String, TableConstraintsVO> constMap = new LinkedHashMap<>();

            tableConstraintsVOList.stream().forEach(tableConstraintsVO -> {
                constMap.put(tableConstraintsVO.getConstraintName(), tableConstraintsVO);
            });

            constMap.forEach((key, dummy) -> {
                tableConstraintsVOList.stream().filter(
                        tableConstraintsVO -> tableConstraintsVO.getConstraintName().equals(key))
                    .forEach((tableConstraintsVO) -> {
                        if (voMap.get(
                            CaseUtils.toCamelCase(tableConstraintsVO.getColumnName(), false, '_'))
                            == null) {
                            constMap.put(key, null);
                        }
                    });
            });
            constMap.forEach((key, dummy) -> {
                if (dummy != null) {
                    tableConstraintsVOList.stream().filter(
                            tableConstraintsVO -> tableConstraintsVO.getConstraintName().equals(key))
                        .forEach(resultList::add);
                }
            });

        }
        return resultList;
    }

    private String getDupValidationStringForComplex(Long packageNo, String voName,
        String loopEOInstance, String tableName, String eoName) {
        String returnString = "";

        TableConstraintsVO conditionVO = new TableConstraintsVO();
        conditionVO.setTableName(tableName);
        //1 pk uk 존재여부 확인
        List<TableConstraintsVO> tableConstraintsVOList = b2bGenerateMapper.retrieveTableConstraints(
            conditionVO);
        if (isNotNullAndEmpty(tableConstraintsVOList)) {
            returnString = getTemplateSqlStmtString("serviceValidationDupCheck");
            returnString = returnString.replace("@eoName", eoName);
            StringBuilder setParamString = new StringBuilder("");
            StringBuilder addValidationString = new StringBuilder("");
            StringBuilder serviceValidationDupInListB = new StringBuilder("");
//            tableConstraintsVOList = convertTableConstraintsVOList(packageNo, voName,
//                tableConstraintsVOList);
            tableConstraintsVOList.stream().forEach(tableConstraintsVO -> {

                List<TableConstraintsVO> columnUsageVOList = b2bGenerateMapper.retrieveKeyColumnUsage(
                    tableConstraintsVO);
                columnUsageVOList = convertTableConstraintsVOList(packageNo, voName,
                    columnUsageVOList);
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

                        String setParam = templateSetParamString.replace("@memberName",
                            memberName);

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
                "retrieve" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true,
                    '_')
                    + "ListAll";

            returnString = returnString.replace("@retrieveMethod", retrieveMethod);

            returnString = returnString.replace("//@addValidationSet", addValidationString);
            returnString = returnString.replace("@loopEOInstance", loopEOInstance);
            returnString = returnString.replace("@eoName", voName);
            if (getListSize(tableConstraintsVOList) == 0) {
                returnString = "/*" + getNewLineString() + returnString + getNewLineString() + "*/";
            }
        }
        //@loopEOInstance
        //@memberName
        //@camelMemberName

        return returnString;
    }

    private String getNullValidationStringForComplex(Long packageNo, String loopEOInstance,
        String tableName, String voName) {
        String returnString = "";

        SchemaColumnConditionVO schemaColumnConditionVO = new SchemaColumnConditionVO();
        schemaColumnConditionVO.setTableName(tableName);
        List<SchemaColumnVO> schemaColumnVOList = b2bGenerateMapper.retrieveColumnSchema(
            schemaColumnConditionVO);
        schemaColumnVOList = convertValidColumnVO(packageNo, voName, schemaColumnVOList);
        if (isNotNullAndEmpty(schemaColumnVOList)) {
            StringBuilder tmpString = new StringBuilder("");

            schemaColumnVOList.stream().filter(
                    schemaColumnVO -> !nullToEmpty(schemaColumnVO.getIsNullable()).equals("YES"))
                .forEach(schemaColumnVO -> {
                    String templateString = getTemplateSqlStmtString(
                        "serviceValidationNullCheck");
                    String memberName = CaseUtils.toCamelCase(schemaColumnVO.getColumnName(),
                        true,
                        '_');
                    String camelMemberName = CaseUtils.toCamelCase(
                        schemaColumnVO.getColumnName(),
                        false, '_');
                    templateString = templateString.replace("@loopEOInstance", loopEOInstance);
                    templateString = templateString.replace("@memberName", memberName);
                    templateString = templateString.replace("@camelMemberName",
                        camelMemberName);
                    tmpString.append(getNewLineString()).append(templateString);
                });
            returnString = tmpString.toString();
        }
        //@loopEOInstance
        //@memberName
        //@camelMemberName

        return returnString;
    }

    private void insertValidationServiceMethod(Long packageNo, String servicePackageName,
        String tableName, String eoName, String serviceClassName, String methodAnnotationName,
        String method_accessor, String methodParamInstantName, Long masterSeq) {
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
                .methodContents(methodContents).tableName(tableName).masterSeq(masterSeq).build()));
    }

    private void insertSaveServiceMethod(Long packageNo, String servicePackageName,
        String tableName, String eoName, String serviceClassName, String methodAnnotationName,
        String method_accessor, String methodParamInstantName, Long masterSeq) {
        String methodName =
            "save" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true, '_')
                + "List";
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
                .tableName(tableName).addDatasetParam("Y").masterSeq(masterSeq).build()));
    }

    private void insertLookupServiceMethod(Long packageNo, String packageName, String
        methodName,
        String lookupType, Long masterSeq) {
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
                .masterSeq(masterSeq)
                .addDatasetParam("Y").build()));
    }

    private String getLookupMethodString(Long packageNo, String methodName, String lookupType) {
        String returnString = "";
        returnString = getTemplateSqlStmtString("serviceJavaLookup");
        returnString = returnString.replace("@methodName", methodName);
        returnString = returnString.replace("@lookupType", lookupType);
        return returnString;
    }

    private void insertSqlServiceMethod(Long packageNo, String packageName, String voName,
        String conditionVOName, String methodName, Long masterSeq) {
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
                .masterSeq(masterSeq)
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

    private void insertSelectListMapperMethod(Long packageNo, String packageName, String
        tableName,
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
                .packageNo(packageNo).mapperXmlName(mapperXmlName)
                .mapperClassName(mapperClassName)
                .methodAnnotationName(methodAnnotationName).methodName(methodName)
                .methodParamClassName(methodParamClassName)
                .methodParamInstantName(methodParamInstantName).xmlMethodType(xmlMethodType)
                .tableName(tableName).methodReturnClassName(methodReturnClassName)
                .sqlStmt(sqlStmt)
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
                .packageNo(packageNo).mapperXmlName(mapperXmlName)
                .mapperClassName(mapperClassName)
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
        String conditionVOFullPathName =
            packageName.toLowerCase(Locale.ROOT) + "." + "model." + conditionVOName;
        String resultVOFullPathName =
            packageName.toLowerCase(Locale.ROOT) + "." + "model." + voName;

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
        returnString = returnString.replace("@conditionVOFullPathName",
            conditionVOFullPathName);
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
        String conditionVOFullPathName =
            packageName.toLowerCase(Locale.ROOT) + "." + "model." + eoName;
        String resultVOFullPathName =
            packageName.toLowerCase(Locale.ROOT) + "." + "model." + eoName;

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
                    CaseUtils.toCamelCase(
                        schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT),
                        false, '_'));
                whereString.append(whereTemplate);

            });

        }
        returnString = templateString;
        returnString = returnString.replace("@methodName", methodName);
        returnString = returnString.replace("@conditionVOFullPathName",
            conditionVOFullPathName);
        returnString = returnString.replace("@resultVOFullPathName", resultVOFullPathName);
        returnString = returnString.replace("@columnName", columnName.toString());
        returnString = returnString.replace("@tableName", tableName);
        returnString = returnString.replace("@whereString", whereString.toString());

        return returnString;
    }

    private void insertSelectByPkMapperMethod(Long packageNo, String packageName, String
        tableName,
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
                .packageNo(packageNo).mapperXmlName(mapperXmlName)
                .mapperClassName(mapperClassName)
                .methodAnnotationName(methodAnnotationName).methodName(methodName)
                .methodParamClassName(methodParamClassName)
                .methodParamInstantName(methodParamInstantName).xmlMethodType(xmlMethodType)
                .tableName(tableName).methodReturnClassName(methodParamClassName)
                .sqlStmt(sqlStmt)
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
        String eoFullPathName = packageName.toLowerCase(Locale.ROOT) + "." + "model." + eoName;

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
                .packageNo(packageNo).mapperXmlName(mapperXmlName)
                .mapperClassName(mapperClassName)
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
        String eoFullPathName = packageName.toLowerCase(Locale.ROOT) + "." + "model." + eoName;

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
        templateString = templateString.replace("@methodParamInstantName",
            methodParamInstantName);
        templateString = templateString.replace("@mapperInstanceName", mapperInstanceName);

        return templateString;
    }

    private String getDupValidationString(String eoName, String loopEOInstance, String
        tableName) {
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

                        String setParam = templateSetParamString.replace("@memberName",
                            memberName);

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
                "retrieve" + CaseUtils.toCamelCase(tableName.toLowerCase(Locale.ROOT), true,
                    '_')
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
                    String templateString = getTemplateSqlStmtString(
                        "serviceValidationNullCheck");
                    String memberName = CaseUtils.toCamelCase(schemaColumnVO.getColumnName(),
                        true,
                        '_');
                    String camelMemberName = CaseUtils.toCamelCase(
                        schemaColumnVO.getColumnName(),
                        false, '_');
                    templateString = templateString.replace("@loopEOInstance", loopEOInstance);
                    templateString = templateString.replace("@memberName", memberName);
                    templateString = templateString.replace("@camelMemberName",
                        camelMemberName);
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
        String mapperDeleteMethodName = getMapperMethodName(packageNo, tableName, "delete",
            eoName);
        //@mapperUpdateMethodName
        String mapperUpdateMethodName = getMapperMethodName(packageNo, tableName, "update",
            eoName);
        //@mapperInsertMethodName
        String mapperInsertMethodName = getMapperMethodName(packageNo, tableName, "insert",
            eoName);
//        String templateString = getTemplateSqlStmtString("ServiceSaveMethod");
        String templateString = getTemplateSqlStmtString("ServiceSaveMethodNew");
        templateString = templateString.replace("@methodName", methodName);
        templateString = templateString.replace("@methodParamClassName", methodParamClassName);
        templateString = templateString.replace("@methodParamInstantName",
            methodParamInstantName);
        templateString = templateString.replace("@loopEOInstance", loopEOInstance);
        templateString = templateString.replace("@validationMethodName", validationMethodName);
        templateString = templateString.replace("@mapperInstanceName", mapperInstanceName);
        templateString = templateString.replace("@mapperDeleteMethodName",
            mapperDeleteMethodName);
        templateString = templateString.replace("@mapperUpdateMethodName",
            mapperUpdateMethodName);
        templateString = templateString.replace("@mapperInsertMethodName",
            mapperInsertMethodName);

        return templateString;
    }

    private String getMapperClassName(Long packageNo) {
        List<TepGenMapperMethodInfoVO> tepGenMapperMethodInfoVOList = coreGenerateMapper.retrieveTepGenMapperMethodInfo(
            TepGenMapperMethodInfoConditionVO.builder().packageNo(packageNo).build());
        return isNotNullAndEmpty(tepGenMapperMethodInfoVOList)
            ? tepGenMapperMethodInfoVOList.get(0)
            .getMapperClassName() : "";
    }

    private String getMapperMethodName(Long packageNo, String tableName, String xmlMethodType,
        String voName) {
        List<TepGenMapperMethodInfoVO> tepGenMapperMethodInfoVOList = coreGenerateMapper.retrieveTepGenMapperMethodInfo(
            TepGenMapperMethodInfoConditionVO.builder().packageNo(packageNo)
                .tableName(tableName)
                .xmlMethodType(xmlMethodType).methodParamClassName("List<" + voName + ">")
                .build());
        return isNotNullAndEmpty(tepGenMapperMethodInfoVOList)
            ? tepGenMapperMethodInfoVOList.get(0)
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
                .packageNo(packageNo).mapperXmlName(mapperXmlName)
                .mapperClassName(mapperClassName)
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
                .packageNo(packageNo).mapperXmlName(mapperXmlName)
                .mapperClassName(mapperClassName)
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
        String eoFullPathName = packageName.toLowerCase(Locale.ROOT) + "." + "model." + eoName;

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
                    .equals("creation_date")).forEach(schemaColumnVO -> {

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
                    .equals("last_update_date")) {
                    matchString.append("now()");
                } else {
                    matchString.append("#{item.");
                    matchString.append(CaseUtils.toCamelCase(
                        schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT), false, '_'));
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
        String eoFullPathName = packageName.toLowerCase(Locale.ROOT) + "." + "model." + eoName;
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
                if (schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT)
                    .equals("creation_date")
                    || schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT)
                    .equals("last_update_date")) {
                    variableName.append("now()");
                } else {
                    variableName.append("#{item.");
                    variableName.append(CaseUtils.toCamelCase(
                        schemaColumnVO.getColumnName().toLowerCase(Locale.ROOT), false, '_'));
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

    public String makeEOFile(Long packageNo, String packageName, String tableName,
        String controllerMethodName, Long masterSeq) {
        String eoClassName = CaseUtils.toCamelCase(tableName, true, '_') + "EO";
        if (getListSize(coreGenerateMapper.retrieveTepGenFileInfo(
            TepGenFileInfoConditionVO.builder().fileName(eoClassName + ".java")
                .packageName(packageName)
                .packageNo(packageNo).fileType("model").build())) == 0) {
            String eoString = getEOString(packageNo, packageName, tableName, eoClassName,
                controllerMethodName, 0L, masterSeq);
            String fileName = eoClassName + ".java";

            createFile(packageName, fileName, eoString, "model");
            //file 생성정보

            coreGenerateMapper.insertMulti(List.of(
                TepGenFileInfoEO.builder().fileName(fileName).packageName(packageName)
                    .packageNo(packageNo).fileType("model").fileContents(eoString).build()));

        }
        return eoClassName;

    }

    public String makeVOFile(Long packageNo, String packageName, String voClassName,
        LinkedHashMap<String, String> resultMap, String controllerMethodName, Long datasetSeq
        , Long masterSeq) {

        String voString = getVOString(packageName, resultMap, voClassName);
        String fileName = voClassName + ".java";

        createFile(packageName, fileName, voString, "model");
        //model 정보 생성

        List<ConvertDataTypeVO> dataTypeVOList = coreGenerateMapper.getConvertDataType();
        Map<String, String> tsTypeMap = new HashMap<>();
        if (getListSize(dataTypeVOList) > 0) {
            dataTypeVOList.forEach(convertDataTypeVO -> {
                tsTypeMap.put(convertDataTypeVO.getJavaDataType(),
                    convertDataTypeVO.getTsDataType());
            });
        }

        resultMap.forEach((memberName, javaType) -> {
            TepGenModelInfoEO tepGenModelInfoEO = new TepGenModelInfoEO();
            tepGenModelInfoEO.setPackageNo(packageNo);
            tepGenModelInfoEO.setPackageName(packageName);
            tepGenModelInfoEO.setVoName(voClassName);
            tepGenModelInfoEO.setJavaType(javaType);
            tepGenModelInfoEO.setTsType(tsTypeMap.get(javaType));
            tepGenModelInfoEO.setInterfaceName("I" + replaceLast(voClassName, "VO", ""));
            tepGenModelInfoEO.setMemberName(memberName);
            tepGenModelInfoEO.setNullYn("Y");
            tepGenModelInfoEO.setLookupYn("N");
            tepGenModelInfoEO.setDatasetName(
                lowerCaseFirst(replaceLast(voClassName, "VO", "")) + "Dataset");
            tepGenModelInfoEO.setControllerMethodName(controllerMethodName);
            tepGenModelInfoEO.setControllerDatasetMethodSeq(
                datasetSeq == null ? 0 : datasetSeq);
            tepGenModelInfoEO.setApiInterfaceParam(
                "I" + upperCaseFirst(controllerMethodName) + "ApiReqParam");
            tepGenModelInfoEO.setUtilApiGetMethodName(
                "getNew" + upperCaseFirst(controllerMethodName) + "ApiReqInstance");
            tepGenModelInfoEO.setApiInterfaceRespData(
                "I" + upperCaseFirst(controllerMethodName) + "ApiRespData");
            tepGenModelInfoEO.setUtilGetFactorMethodName(
                "getNew" + replaceLast(voClassName, "VO", "") + "FactorInstance");
            tepGenModelInfoEO.setUtilGetObjectMethodName(
                "getNew" + replaceLast(voClassName, "VO", "") + "Instance");
            tepGenModelInfoEO.setMasterSeq(masterSeq);
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
            String mapperFullPath =
                mapperPackage.get().toLowerCase(Locale.ROOT) + "." + mapperClassName.get();

            xmlMapperTemplateString = xmlMapperTemplateString.replace("@mapperFullPath",
                mapperFullPath);
            xmlMapperTemplateString = xmlMapperTemplateString.replace("@mapperContents",
                contentsStringBuilder.toString());
            createFile(packageName, mapperXmlName.get(), xmlMapperTemplateString, "xml");
            //file 생성정보
            coreGenerateMapper.insertMulti(List.of(
                TepGenFileInfoEO.builder().fileName(mapperXmlName.get())
                    .packageName(packageName)
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
                distinctMap.put((new StringBuilder().append("import ")
                        .append(tepGenFileInfoVO.getPackageName().toLowerCase(Locale.ROOT))
                        .append(".model.")
                        .append(tepGenFileInfoVO.getFileName().replace(".java", ";"))).toString(),
                    "");

            });
            distinctMap.forEach(
                (key, val) -> returnString.append(getNewLineString()).append(key));
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
//            String serviceFullPath = servicePackage.get();
            String serviceFullPath = packageName.toLowerCase(Locale.ROOT) + ".service";
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
                    TepGenMapperMethodInfoConditionVO.builder().packageNo(packageNo).build())
                .stream()
                .findFirst().get();
            String mapperPackage = TepGenMapperMethodInfoVO.getMapperPackageName().toLowerCase(
                Locale.ROOT) + "."
                + TepGenMapperMethodInfoVO.getMapperClassName();
            String mapperClassName = TepGenMapperMethodInfoVO.getMapperClassName();
            String mapperInstantName = lowerCaseFirst(mapperClassName);
            serviceImplJavaString = serviceImplJavaString.replace("@mapperPackage",
                mapperPackage);
            serviceImplJavaString = serviceImplJavaString.replace("@mapperClassName",
                mapperClassName);
            serviceImplJavaString = serviceImplJavaString.replace("@mapperInstantName",
                mapperInstantName);
            createFile(packageName, serviceClassName.get() + "Impl.java",
                serviceImplJavaString, "service");
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
//            String serviceFullPath = servicePackage.get();
            String serviceFullPath = packageName.toLowerCase(Locale.ROOT) + ".service";
            serviceJavaString = serviceJavaString.replace("@serviceFullPath", serviceFullPath);
            serviceJavaString = serviceJavaString.replace("@importModelString",
                getImportModelString(packageNo));
            serviceJavaString = serviceJavaString.replace("@serviceClassName",
                serviceClassName.get());
            serviceJavaString = serviceJavaString.replace("@serviceContents",
                contentsStringBuilder.toString());

            createFile(packageName, serviceClassName.get() + ".java", serviceJavaString, "service");
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
            String mapperFullPath = mapperPackage.get().toLowerCase(Locale.ROOT);

            mapperJavaString = mapperJavaString.replace("@mapperFullPath", mapperFullPath);
            mapperJavaString = mapperJavaString.replace("@importModelString",
                getImportModelString(packageNo));
            mapperJavaString = mapperJavaString.replace("@mapperClassName",
                mapperClassName.get());
            mapperJavaString = mapperJavaString.replace("@mapperContents",
                contentsStringBuilder.toString());

            createFile(packageName, mapperClassName.get() + ".java", mapperJavaString, "mapper");
            //file 생성정보
            coreGenerateMapper.insertMulti(List.of(
                TepGenFileInfoEO.builder().fileName(mapperClassName.get() + ".java")
                    .packageName(packageName).packageNo(packageNo).fileType("mapper")
                    .fileContents(mapperJavaString).build()));
        }
        return mapperJavaFileName;
    }

    private void createFrontApiFile(String packageName, String fileName, String fileContents) {
        File mainDir = new File("c:" + File.separatorChar + "chsWorkNew");
        //한폴더에 몰자
        String[] g = packageName.split("[.]");
        String strDir = g[g.length - 1];
        String dirName = "c:" + File.separatorChar + "chsWorkNew" + File.separatorChar + strDir
            + File.separatorChar + "front" + File.separatorChar + "api";
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

    private void createFrontFile(String packageName, String fileName, String fileContents) {
        File mainDir = new File("c:" + File.separatorChar + "chsWorkNew");
        //한폴더에 몰자
        String[] g = packageName.split("[.]");
        String strDir = g[g.length - 1];
        String dirName = "c:" + File.separatorChar + "chsWorkNew" + File.separatorChar + strDir
            + File.separatorChar + "front";
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

    public void createFile(String packageName, String fileName, String fileContents,
        String folderName) {
        File mainDir = new File("c:" + File.separatorChar + "chsWorkNew");
        //한폴더에 몰자
        String[] g = packageName.split("[.]");
        String strDir = g[g.length - 1];
        if (!mainDir.isDirectory()) {
            mainDir.mkdir();
        }
        String prjName = "c:" + File.separatorChar + "chsWorkNew" + File.separatorChar + strDir;
        File prjDir = new File(prjName);
        if (!prjDir.isDirectory()) {
            prjDir.mkdir();
        }
        String backDirName = "c:" + File.separatorChar + "chsWorkNew" + File.separatorChar + strDir
            + File.separatorChar + "back";
        File backDir = new File(backDirName);
        if (!backDir.isDirectory()) {
            backDir.mkdir();
        }

        String dirName = "c:" + File.separatorChar + "chsWorkNew" + File.separatorChar + strDir
            + File.separatorChar + "back" + File.separatorChar + folderName;
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
                replaceString.append(getNewLineString()).append(getTabString(1))
                    .append("private")
                    .append(" ").append(val).append(" ").append(key).append(";");

            });
        }

        //3 eo name replace
        returnString = templateString.replace("//@EONameHere", voClassName);
        returnString = returnString.replace("//@GenHere", replaceString.toString());
        returnString = returnString.replace("//@PackageNameHere",
            packageName.toLowerCase(Locale.ROOT));
        return returnString;
    }

    public String getEOString(Long packageNo, String packageName, String tableName,
        String eoClassName, String controllerMethodName, Long datasetMethodSeq, Long masterSeq) {
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

                coreColumnVOList.add(
                    CoreColumnVO.builder().dataType(schemaColumnVO.getDataType())
                        .columnName(schemaColumnVO.getColumnName()).build()

                );
            });
        }

        //2 get Replace String
        List<TepGenModelInfoEO> tepGenModelInfoEOList = new ArrayList<>();
        String replaceString = getVOColumnString(coreColumnVOList, 1, tepGenModelInfoEOList);
        tepGenModelInfoEOList.stream().forEach(tepGenModelInfoEO -> {
            tepGenModelInfoEO.setPackageNo(packageNo);
            tepGenModelInfoEO.setPackageName(packageName);
            tepGenModelInfoEO.setVoName(eoClassName);
            tepGenModelInfoEO.setInterfaceName("I" + replaceLast(eoClassName, "EO", ""));
            tepGenModelInfoEO.setNullYn("Y");
            tepGenModelInfoEO.setDatasetName(
                lowerCaseFirst(replaceLast(eoClassName, "EO", "")) + "Dataset");
            tepGenModelInfoEO.setControllerMethodName(controllerMethodName);
            tepGenModelInfoEO.setControllerDatasetMethodSeq(
                datasetMethodSeq == null ? 0 : datasetMethodSeq);
            if (isNotNullAndEmpty(controllerMethodName)) {
                tepGenModelInfoEO.setApiInterfaceParam(
                    "I" + upperCaseFirst(controllerMethodName) + "ApiReqParam");
                tepGenModelInfoEO.setUtilApiGetMethodName(
                    "getNew" + upperCaseFirst(controllerMethodName) + "ApiReqInstance");
                tepGenModelInfoEO.setApiInterfaceRespData(
                    "I" + upperCaseFirst(controllerMethodName) + "ApiRespData");

                tepGenModelInfoEO.setUtilGetFactorMethodName(
                    "getNew" + replaceLast(eoClassName, "VO", "") + "FactorInstance");
                tepGenModelInfoEO.setUtilGetObjectMethodName(
                    "getNew" + replaceLast(eoClassName, "VO", "") + "Instance");
            }
            tepGenModelInfoEO.setMasterSeq(masterSeq);

            coreGenerateMapper.insertTepGenModelInfoList(List.of(tepGenModelInfoEO));

        });
        //3 eo name replace
        returnString = templateString.replace("//@EONameHere", eoClassName);
        returnString = returnString.replace("//@GenHere", replaceString);
        returnString = returnString.replace("//@PackageNameHere",
            packageName.toLowerCase(Locale.ROOT));
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

    public String getVOColumnString(List<CoreColumnVO> coreColumnVOList, int tabInx,
        List<TepGenModelInfoEO> tepGenModelInfo) {
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
                returnString.append(
                    isNotNullAndEmpty(dataTypeMap.get(coreColumnVO.getDataType()))
                        ? dataTypeMap.get(coreColumnVO.getDataType()) : "String");
                returnString.append(" ");
                returnString.append(
                    CaseUtils.toCamelCase(coreColumnVO.getColumnName(), false, '_'));
                returnString.append(";");
                returnString.append(getNewLineString());

                TepGenModelInfoEO tepGenModelInfoEO = new TepGenModelInfoEO();
                tepGenModelInfoEO.setColumnType(coreColumnVO.getDataType());
                tepGenModelInfoEO.setJavaType(
                    isNotNullAndEmpty(dataTypeMap.get(coreColumnVO.getDataType()))
                        ? dataTypeMap.get(coreColumnVO.getDataType()) : "String");
                tepGenModelInfoEO.setColumnName(coreColumnVO.getColumnName());
                tepGenModelInfoEO.setMemberName(
                    CaseUtils.toCamelCase(coreColumnVO.getColumnName(), false, '_'));
                tepGenModelInfoEO.setTsType(
                    isNotNullAndEmpty(tsTypeMap.get(coreColumnVO.getDataType()))
                        ? tsTypeMap.get(
                        coreColumnVO.getDataType()) : "string");
                tepGenModelInfoEO.setLookupYn("N");
                finalTepGenModelInfo.add(tepGenModelInfoEO);
            });


        }
        return returnString.toString();
    }

}
