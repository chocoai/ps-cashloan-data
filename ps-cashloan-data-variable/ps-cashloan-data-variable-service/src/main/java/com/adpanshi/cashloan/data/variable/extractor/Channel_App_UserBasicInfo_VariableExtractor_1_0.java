package com.adpanshi.cashloan.data.variable.extractor;

import com.adpanshi.cashloan.data.appdata.bo.AppDataBo;
import com.adpanshi.cashloan.data.appdata.domain.AppDataDomain;
import com.adpanshi.cashloan.data.common.enums.DataTypeEnum;
import com.adpanshi.cashloan.data.variable.exception.VariableException;
import com.adpanshi.cashloan.data.variable.model.VariableData;
import com.adpanshi.cashloan.data.variable.model.VariableDataValue;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zsw on 2018/6/29 0029.
 */
@Service
public class Channel_App_UserBasicInfo_VariableExtractor_1_0 implements VariableExtractor {
    private static Logger log = LoggerFactory.getLogger(Channel_App_UserBasicInfo_VariableExtractor_1_0.class);

    @Resource
    private AppDataDomain appDataDomain;

    @Override
    public List<VariableDataValue> doExtract(Integer channelDataId) {
        AppDataBo appDataBo = appDataDomain.getAppData(channelDataId);
        JSONObject jsonObj = JSON.parseObject(appDataBo.getOriginalData());
        VariableData variableData = new VariableData();
        addWhiteKnightVariable(jsonObj,variableData);
        return variableData.getValueList();
    }

    /**
     * 添加变量集
     */
    private void addWhiteKnightVariable(JSONObject jsonObj,VariableData variableData) {
        try {
            if (jsonObj.getString("marriageState") != null) {
                    variableData.addValueList("marriageState", "是否已婚", jsonObj.getString("marriageState"), DataTypeEnum.STRING.getValue());
                }
            if (jsonObj.getString("residentProvince") != null) {
                variableData.addValueList("residentProvince", "居住省", jsonObj.getString("residentProvince"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("residentCity") != null) {
                variableData.addValueList("residentCity", "居住城市", jsonObj.getString("residentCity"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("residentAddress") != null) {
                variableData.addValueList("residentAddress", "居住地址", jsonObj.getString("residentAddress"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("employer") != null) {
                variableData.addValueList("employer", "企业名称", jsonObj.getString("employer"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("emergencyContactName") != null) {
                variableData.addValueList("emergencyContactName", "紧急联系人", jsonObj.getString("emergencyContactName"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("emergencyContactRel") != null) {
                variableData.addValueList("emergencyContactRel", "与紧急联系人关系", jsonObj.getString("emergencyContactRel"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("lastCity") != null) {
                variableData.addValueList("lastCity", "最近所在城市", jsonObj.getString("lastCity"), DataTypeEnum.STRING.getValue());
            }
        } catch (Exception e) {
            log.error("app用户基本属性变量抽取异常： " + e);
            throw new VariableException("解析异常：" + e);
        }
    }
}