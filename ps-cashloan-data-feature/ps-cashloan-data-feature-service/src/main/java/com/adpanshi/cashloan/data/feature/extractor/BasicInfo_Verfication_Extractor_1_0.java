package com.adpanshi.cashloan.data.feature.extractor;

import com.adpanshi.cashloan.data.common.enums.DataTypeEnum;
import com.adpanshi.cashloan.data.feature.bo.DataFromBo;
import com.adpanshi.cashloan.data.feature.model.FeatureData;
import com.adpanshi.cashloan.data.feature.pojo.FeatureExtractSimpleResultBo;
import com.adpanshi.cashloan.data.feature.rule.DataTypeExchangeRule;
import com.adpanshi.cashloan.data.feature.service.FeatureDataService;
import com.adpanshi.cashloan.data.variable.bo.VariableDataBo;
import com.adpanshi.cashloan.data.variable.bo.VariableDataValueBo;
import com.adpanshi.cashloan.data.variable.domain.VariableDomain;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsw on 2018/6/29 0029.
 */
@Service
public class BasicInfo_Verfication_Extractor_1_0 implements FeatureExtractor {

    @Resource
    private VariableDomain varivableDomain;

    private DataTypeExchangeRule dataTypeExchangeRule=new DataTypeExchangeRule();

    public FeatureExtractSimpleResultBo doExtract(List<DataFromBo> variableDataIdList) {

        FeatureData featureData =new FeatureData();
        //1.通过variableDataIdList 获取变量数据
        List<Integer> dataFromVariableIdList = dataFromBoList2DataIdList(variableDataIdList);
        List<VariableDataBo> variableDataList = varivableDomain.getVariableList(dataFromVariableIdList);
        //2.按照需求抽取特征
        for (VariableDataBo variableData : variableDataList){
            for (VariableDataValueBo variableValue:variableData.getValueList()) {
                if(variableValue.getKey().equals("marriageState")){
                    featureData.addValueList("是否已婚","marriageState",dataTypeExchangeRule.getStrToInt(variableValue), DataTypeEnum.INTEGER.getValue());
                }else if(variableValue.getKey().equals("residentCity")){
                    featureData.addValueList("居住城市","residentCity",variableValue, DataTypeEnum.STRING.getValue());
                }else if(variableValue.getKey().equals("lastCity")){
                    featureData.addValueList("最近所在城市","lastCity",variableValue, DataTypeEnum.STRING.getValue());
                }
            }
        }
        FeatureExtractSimpleResultBo result = new FeatureExtractSimpleResultBo();
        result.setDataFromVariableIdList(dataFromVariableIdList);
        result.setDataValueList(featureData.getValueList());
        return result;
    }

    public List<Integer> dataFromBoList2DataIdList(List<DataFromBo> variableDataIdList){
        List<Integer> list = new ArrayList<Integer>();
        for (DataFromBo dataFromBo:variableDataIdList){
            list.add(dataFromBo.getVariableDataId());
        }
        return list;
    }
}