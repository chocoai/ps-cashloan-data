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
 * 信用报告特征抽取
 * Created by zsw on 2018/8/3 0003.
 */
@Service
public class Fraud_EquifaxFraud_Extractor_1_0 implements FeatureExtractor {
    private static Logger logger = LoggerFactory.getLogger(Fraud_EquifaxFraud_Extractor_1_0.class);

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
                if("mobilePhone".equals(variableValue.getKey())){
                    featureData.addValueList("信用报告电话","envelopePhone",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if("firstName".equals(variableValue.getKey())){
                    featureData.addValueList("用户信息第一个名字","envelopeFirstName",variableValue.getValue(), DataTypeEnum.STRING.getValue());
                }else if ("lastName".equals(variableValue.getKey())){
                    featureData.addValueList("信用报告最后一个名字","envelopeLastName",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("middleName".equals(variableValue.getKey())){
                    featureData.addValueList("中间名","middleName",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                } else if ("ages".equals(variableValue.getKey())){
                    featureData.addValueList("年龄","ages",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("dateOfBirth".equals(variableValue.getKey())){
                    featureData.addValueList("出生日期","dateOfBirth",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("gender".equals(variableValue.getKey())){
                    featureData.addValueList("性别","gender",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("totalIncome".equals(variableValue.getKey())){
                    featureData.addValueList("总收入","totalIncome",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("address".equals(variableValue.getKey())){
                    featureData.addValueList("地址","address",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("postal".equals(variableValue.getKey())){
                    featureData.addValueList("邮政编码","postal",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("state".equals(variableValue.getKey())){
                    featureData.addValueList("归属州","state",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("type".equals(variableValue.getKey()) && !"N/A".equals(variableValue.getValue())){
                    featureData.addValueList("住址类型","type",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("emailAddress".equals(variableValue.getKey())){
                    featureData.addValueList("电子邮件地址","emailAddress",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("number".equals(variableValue.getKey())){
                    featureData.addValueList("电话号码","number",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("idNumber".equals(variableValue.getKey())){
                    featureData.addValueList("PAN卡号","idNumber",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("driverLicences".equals(variableValue.getKey())){
                    featureData.addValueList("驾驶证","driverLicences",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("idCards".equals(variableValue.getKey())){
                    featureData.addValueList("身份证","idCards",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("idOthers".equals(variableValue.getKey())){
                    featureData.addValueList("其他ID","idOthers",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("nationalIDCards".equals(variableValue.getKey())){
                    featureData.addValueList("国民卡信息","nationalIDCards",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("passportIDs".equals(variableValue.getKey())){
                    featureData.addValueList("护照ID","passportIDs",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("photoCreditCards".equals(variableValue.getKey())){
                    featureData.addValueList("图片版权","photoCreditCards",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("rationCards".equals(variableValue.getKey())){
                    featureData.addValueList("配给卡","rationCards",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("voterIDs".equals(variableValue.getKey())){
                    featureData.addValueList("选民证","voterIDs",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("name".equals(variableValue.getKey())){
                    featureData.addValueList("用户名","name",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("value".equals(variableValue.getKey())){
                    featureData.addValueList("信用报告分值","envelopeScore",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("accountNumber".equals(variableValue.getKey())){
                    featureData.addValueList("账号","accountNumber",variableValue.getValue(),DataTypeEnum.STRING.getValue());
                }else if ("addrLine1".equals(variableValue.getKey())){
                    featureData.addValueList("信用报告地址","envelopeAddress",variableValue.getValue(),DataTypeEnum.STRING.getValue());
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
