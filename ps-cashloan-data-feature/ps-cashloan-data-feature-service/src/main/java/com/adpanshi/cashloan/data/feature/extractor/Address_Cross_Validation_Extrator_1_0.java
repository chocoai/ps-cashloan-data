package com.adpanshi.cashloan.data.feature.extractor;


import com.adpanshi.cashloan.data.common.enums.DataTypeEnum;
import com.adpanshi.cashloan.data.feature.bo.DataFromBo;
import com.adpanshi.cashloan.data.feature.enums.FeatureType;
import com.adpanshi.cashloan.data.feature.exception.FeatureException;
import com.adpanshi.cashloan.data.feature.model.FeatureData;
import com.adpanshi.cashloan.data.feature.pojo.FeatureExtractSimpleResultBo;
import com.adpanshi.cashloan.data.feature.utils.CommonUtil;
import com.adpanshi.cashloan.data.feature.utils.ProvinceCityTownVillageUtil;
import com.adpanshi.cashloan.data.variable.bo.VariableDataBo;
import com.adpanshi.cashloan.data.variable.bo.VariableDataValueBo;
import com.adpanshi.cashloan.data.variable.domain.VariableDomain;
import com.adpanshi.cashloan.data.variable.enums.VariableType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 地址交叉验证特征抽取
 */
@Service
public class Address_Cross_Validation_Extrator_1_0 implements FeatureExtractor{

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Resource
    private VariableDomain varivableDomain;



    @Override
    public FeatureExtractSimpleResultBo doExtract(List<DataFromBo> variableDataIdList) {

        try {
            List<Integer> dataFromVariableIdList = CommonUtil.dataFromBoList2DataIdList(variableDataIdList);
            FeatureData featureData = achieveFeatureDataFromMultiVariables(variableDataIdList);

            //返回特征主键
            FeatureExtractSimpleResultBo result = new FeatureExtractSimpleResultBo();
            result.setDataFromVariableIdList(dataFromVariableIdList);
            result.setDataValueList(featureData.getValueList());
            return result;
        }catch (Exception e){
            logger.info("计算特征组{}异常！数据源={}, 姓名={}, 身份证={}, 版本={}",
                    FeatureType.USER_BASICINFO_ADDRESS_CROSS_VALIDATION, variableDataIdList);
            logger.error(e.getMessage(), e);
            throw new FeatureException(getClass().getName() + "计算特征组异常！", e);
        }
    }


    private FeatureData achieveFeatureDataFromMultiVariables(List<DataFromBo> variableDataIdList){

        FeatureData featureData = new FeatureData();

        //用户基本属性
        String userBasicInfo = VariableType.USER_BASICINFO.getValue();
        //魔蝎社交属性
        String operatorScocial = VariableType.USER_CONTACTS_INFO.getValue();
        //魔蝎基本属性
        String operatorBasic = VariableType.USER_BASICINFO.getValue();

        Map<String, String> addressMap = new HashMap<>(16);

        List<Integer> dataFromVariableIdList = CommonUtil.dataFromBoList2DataIdList(variableDataIdList);
        List<VariableDataBo> variableDataList = varivableDomain.findVariableList(dataFromVariableIdList);
        for(VariableDataBo vdbo : variableDataList){

            //用户基本属性
            if(vdbo.getVariableType().getValue().equals(userBasicInfo)){
                for(VariableDataValueBo vdvbo : vdbo.getValueList()) {
                    if("appUserLiveInCity".equals(vdvbo.getKey())) {
                        addressMap.put("userHomeAddress", vdvbo.getValue() + "");
                    }
                }
            }

            //魔蝎社交属性
            if(vdbo.getVariableType().getValue().equals(operatorScocial)){
                for(VariableDataValueBo vdvbo : vdbo.getValueList()) {
                    if("nearly6MonthsFriendsCircleCenterCity".equals(vdvbo.getKey())) {
                        addressMap.put("nearly6MonthsFriendsCircleCenterCity", vdvbo.getValue() + "");
                    }

                }
            }

            //魔蝎基本属性
            if(vdbo.getVariableType().getValue().equals(operatorBasic)){
                for(VariableDataValueBo vdvbo : vdbo.getValueList()) {
                    if("mxResidentialAddress".equals(vdvbo.getKey())) {
                        addressMap.put("mxResidentialAddress", vdvbo.getValue() + "");
                    }
                }
            }


        }

        achieveCompareAddress(addressMap, featureData);
        return featureData;

    }


    private void achieveCompareAddress(Map<String, String> addressMap, FeatureData featureData){
        final Integer sameAddresscountThree = 3;
        final Integer sameAddresscountTwo = 2;
        final Integer sameAddresscountOne = 1;

        int cnt = 0;
        if(ProvinceCityTownVillageUtil.compareTwoAddress(
                addressMap.get("userHomeAddress"), addressMap.get("nearly6MonthsFriendsCircleCenterCity"))==1){
            cnt++;
        }
        if(ProvinceCityTownVillageUtil.compareTwoAddress(
                addressMap.get("userHomeAddress"), addressMap.get("mxResidentialAddress"))==1){
            cnt++;
        }
        if(cnt == sameAddresscountThree){
            featureData.addValueList("申贷人目前住址可信度", "currentliveAddressConfidence", 100, DataTypeEnum.INTEGER.getValue());
        }else if(cnt == sameAddresscountTwo){
            featureData.addValueList("申贷人目前住址可信度", "currentliveAddressConfidence", 80, DataTypeEnum.INTEGER.getValue());
        }else if(cnt == sameAddresscountOne){
            featureData.addValueList("申贷人目前住址可信度", "currentliveAddressConfidence", 60, DataTypeEnum.INTEGER.getValue());
        }else if(addressMap.size()>0){
            featureData.addValueList("申贷人目前住址可信度", "currentliveAddressConfidence", 40, DataTypeEnum.INTEGER.getValue());
        }
    }


}
