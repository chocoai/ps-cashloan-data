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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsw on 2018/8/7 0007.
 */
@Service
public class DeviceApplication_Verfication_Extractor_1_0  implements FeatureExtractor {

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
                if("appApplicationList".equals(variableValue.getKey()) && variableValue.getValue() instanceof JSONArray){
                    JSONArray jsonArray = (JSONArray)variableValue.getValue();
                    List<JSONArray> applicationList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONArray applicationArray = (JSONArray) jsonArray.get(i);
                        JSONArray simpleApplication = new JSONArray();
                        for (int j = 0; j < applicationArray.size(); j++) {
                            JSONObject applicationValueJson = (JSONObject)applicationArray.get(j);
                            if ("appName".equals(applicationValueJson.getString("key"))) {
                                simpleApplication.add(new FeatureDataValue("APP应用名称", "appApplicationName", applicationValueJson.getString("value"), DataTypeEnum.STRING.getValue()));
                            }
                            if ("packageName".equals(applicationValueJson.getString("key"))) {
                                simpleApplication.add(new FeatureDataValue("APP应用包名", "appApplicationPackageName", applicationValueJson.getString("value"), DataTypeEnum.STRING.getValue()));
                            }
                        }
                        applicationList.add(simpleApplication);
                    }
//                    featureData.addValueList("APP应用列表","appApplicationList",applicationList, DataTypeEnum.ARRAY.getValue());
                    featureData.addValueList("APP应用数量","appApplicationCount",applicationList.size(), DataTypeEnum.INTEGER.getValue());
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