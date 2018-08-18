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
public class Social_MoxieSNS_Extractor_1_0 implements FeatureExtractor {
    private static Logger logger = LoggerFactory.getLogger(Social_MoxieSNS_Extractor_1_0.class);

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
                if("nickname".equals(variableValue.getKey())){
                    featureData.addValueList("用户名","nickname",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("currentcity".equals(variableValue.getKey())){
                    featureData.addValueList("现居地","currentcity",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("hometown".equals(variableValue.getKey())){
                    featureData.addValueList("家乡","hometown",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("birthdate".equals(variableValue.getKey())){
                    featureData.addValueList("出生月日","birthdate",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("birthyear".equals(variableValue.getKey())){
                    featureData.addValueList("出生年","birthyear",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("gender".equals(variableValue.getKey())){
                    featureData.addValueList("性别","gender",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("emails".equals(variableValue.getKey())){
                    featureData.addValueList("邮箱","emails",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("gender".equals(variableValue.getKey())){
                    featureData.addValueList("性别","gender",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("blood_type".equals(variableValue.getKey())){
                    featureData.addValueList("血型","blood_type",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("language".equals(variableValue.getKey())){
                    featureData.addValueList("语言","language",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("religion_trend".equals(variableValue.getKey())){
                    featureData.addValueList("宗教趋向","religion_trend",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("political_trend".equals(variableValue.getKey())){
                    featureData.addValueList("政治趋向","political_trend",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("relationshipstatus".equals(variableValue.getKey())){
                    featureData.addValueList("用户名","relationshipstatus",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("friendsnum".equals(variableValue.getKey())){
                    featureData.addValueList("好友数量","friendsnum",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("mobilephones".equals(variableValue.getKey())){
                    featureData.addValueList("手机号码","mobilephones",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("college_info".equals(variableValue.getKey())){
                    featureData.addValueList("大学信息","college_info",variableValue.getValue(), DataTypeEnum.ARRAY.getValue());
                }else if("highschool_info".equals(variableValue.getKey())){
                    featureData.addValueList("高中信息","highschool_info",variableValue.getValue(), DataTypeEnum.ARRAY.getValue());
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
