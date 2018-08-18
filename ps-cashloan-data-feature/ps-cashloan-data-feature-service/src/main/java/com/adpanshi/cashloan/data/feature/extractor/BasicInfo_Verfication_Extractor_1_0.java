package com.adpanshi.cashloan.data.feature.extractor;

import com.adpanshi.cashloan.data.common.enums.DataTypeEnum;
import com.adpanshi.cashloan.data.feature.bo.DataFromBo;
import com.adpanshi.cashloan.data.feature.model.FeatureData;
import com.adpanshi.cashloan.data.feature.pojo.FeatureExtractSimpleResultBo;
import com.adpanshi.cashloan.data.feature.rule.DataTypeExchangeRule;
import com.adpanshi.cashloan.data.variable.bo.VariableDataBo;
import com.adpanshi.cashloan.data.variable.bo.VariableDataValueBo;
import com.adpanshi.cashloan.data.variable.domain.VariableDomain;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息特征抽取
 * Created by zsw on 2018/6/29 0029.
 */
@Service
public class BasicInfo_Verfication_Extractor_1_0 implements FeatureExtractor {

    @Resource
    private VariableDomain varivableDomain;


    @Override
    public FeatureExtractSimpleResultBo doExtract(List<DataFromBo> variableDataIdList) {

        FeatureData featureData =new FeatureData();
        //1.通过variableDataIdList 获取变量数据
        List<Integer> dataFromVariableIdList = dataFromBoList2DataIdList(variableDataIdList);
        List<VariableDataBo> variableDataList = varivableDomain.findVariableList(dataFromVariableIdList);
        //2.按照需求抽取特征

        for (VariableDataBo variableData : variableDataList){
            for (VariableDataValueBo variableValue:variableData.getValueList()) {
                if("firstName".equals(variableValue.getKey())){
                    featureData.addValueList("用户信息第一个名字","userFirstName",variableValue.getValue(), DataTypeEnum.INTEGER.getValue());
                }else if("lastName".equals(variableValue.getKey())){
                    featureData.addValueList("用户信息最后名字","userLastName",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("dateBirthday".equals(variableValue.getKey())){
                    //todo 可以计算的，先给李金磊做，嘻嘻
                    featureData.addValueList("用户信息年龄","userAge",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("registerAddr".equals(variableValue.getKey())){
                    featureData.addValueList("用户信息注册地址","userRegisterAddress",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("liveCoordinate".equals(variableValue.getKey())){
                    featureData.addValueList("用户信息所在经纬度","userCoordinate",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("livingImg".equals(variableValue.getKey())){
                    featureData.addValueList("用户信息手持身份证照片","userLivingImg",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }

            }
        }

        FeatureExtractSimpleResultBo result = new FeatureExtractSimpleResultBo();
        result.setDataFromVariableIdList(dataFromVariableIdList);
        result.setDataValueList(featureData.getValueList());
        return result;
    }

    private List<Integer> dataFromBoList2DataIdList(List<DataFromBo> variableDataIdList){
        List<Integer> list = new ArrayList<Integer>();
        for (DataFromBo dataFromBo:variableDataIdList){
            list.add(dataFromBo.getVariableDataId());
        }
        return list;
    }
}