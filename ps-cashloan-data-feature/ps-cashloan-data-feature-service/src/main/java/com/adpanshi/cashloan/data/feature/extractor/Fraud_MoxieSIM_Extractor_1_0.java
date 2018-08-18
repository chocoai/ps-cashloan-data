package com.adpanshi.cashloan.data.feature.extractor;

import com.adpanshi.cashloan.data.common.enums.DataTypeEnum;
import com.adpanshi.cashloan.data.feature.bo.DataFromBo;
import com.adpanshi.cashloan.data.feature.model.FeatureData;
import com.adpanshi.cashloan.data.feature.pojo.FeatureExtractSimpleResultBo;
import com.adpanshi.cashloan.data.variable.bo.VariableDataBo;
import com.adpanshi.cashloan.data.variable.bo.VariableDataValueBo;
import com.adpanshi.cashloan.data.variable.domain.VariableDomain;
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
public class Fraud_MoxieSIM_Extractor_1_0 implements FeatureExtractor{
    private static Logger logger = LoggerFactory.getLogger(Fraud_MoxieSIM_Extractor_1_0.class);

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
                if("phone".equals(variableValue.getKey())){
                    featureData.addValueList("电话号码","phone",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("mobile_brand".equals(variableValue.getKey())){
                    featureData.addValueList("手机品牌","mobile_brand",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("os_type".equals(variableValue.getKey())){
                    featureData.addValueList("系统类型","os_type",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("cloud_address".equals(variableValue.getKey())){
                    featureData.addValueList("云端存储地址","cloud_address",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("name".equals(variableValue.getKey())){
                    featureData.addValueList("名字","name",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("company".equals(variableValue.getKey())){
                    featureData.addValueList("公司","company",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("stop_call".equals(variableValue.getKey())){
                    featureData.addValueList("阻止客户","stop_call",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("stop_call".equals(variableValue.getKey())){
                    featureData.addValueList("阻止客户","stop_call",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("phone_info".equals(variableValue.getKey())){
                    featureData.addValueList("电话信息","phone_info",variableValue.getValue(), DataTypeEnum.ARRAY.getValue());
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
