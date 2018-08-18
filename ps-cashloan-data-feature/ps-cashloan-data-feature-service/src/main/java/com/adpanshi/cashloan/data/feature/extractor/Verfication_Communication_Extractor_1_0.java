package com.adpanshi.cashloan.data.feature.extractor;

import com.adpanshi.cashloan.data.common.enums.DataTypeEnum;
import com.adpanshi.cashloan.data.feature.bo.DataFromBo;
import com.adpanshi.cashloan.data.feature.model.FeatureData;
import com.adpanshi.cashloan.data.feature.pojo.FeatureDataValue;
import com.adpanshi.cashloan.data.feature.pojo.FeatureExtractSimpleResultBo;
import com.adpanshi.cashloan.data.variable.bo.VariableDataBo;
import com.adpanshi.cashloan.data.variable.bo.VariableDataValueBo;
import com.adpanshi.cashloan.data.variable.domain.VariableDomain;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsw on 2018/8/3 0003.
 */
@Service
public class Verfication_Communication_Extractor_1_0 implements FeatureExtractor {
    private static Logger logger = LoggerFactory.getLogger(Verfication_Communication_Extractor_1_0.class);

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
                if("appCommunicationList".equals(variableValue.getKey()) && variableValue.getValue() instanceof JSONArray){
                    JSONArray jsonArray = (JSONArray)variableValue.getValue();
                    List<JSONArray> communicationList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONArray communicationArray = (JSONArray) jsonArray.get(i);
                        JSONArray simpleCommunication = new JSONArray();
                        for (int j = 0; j < communicationArray.size(); j++) {
                            JSONObject communicationValueJson = (JSONObject)communicationArray.get(j);
                            if ("name".equals(communicationValueJson.getString("key"))) {
                                simpleCommunication.add(new FeatureDataValue("APP通讯录联系人姓名", "appCommunicationContactName", communicationValueJson.getString("value"), DataTypeEnum.STRING.getValue()));
                            }
                            if ("phone".equals(communicationValueJson.getString("key"))) {
                                simpleCommunication.add(new FeatureDataValue("APP通讯录联系人手机号", "appCommunicationContactPhone", communicationValueJson.getString("value"), DataTypeEnum.STRING.getValue()));
                            }
                        }
                        communicationList.add(simpleCommunication);
                    }
//                    featureData.addValueList("APP通讯录列表","appCommunicationList",communicationList, DataTypeEnum.ARRAY.getValue());
                    featureData.addValueList("APP通讯录数量","userContactsMatchSize",communicationList.size(), DataTypeEnum.INTEGER.getValue());
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
