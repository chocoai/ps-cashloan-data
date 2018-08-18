package com.adpanshi.cashloan.data.variable.extractor;

import com.adpanshi.cashloan.data.common.enums.DataTypeEnum;
import com.adpanshi.cashloan.data.variable.exception.VariableException;
import com.adpanshi.cashloan.data.variable.model.VariableData;
import com.adpanshi.cashloan.data.variable.model.VariableDataValue;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zsw on 2018/8/1 0001.
 * 短信信息变量提取
 */
@Service
public class Channel_App_SMS_VariableExtractor_1_0 implements VariableExtractor {

    private static Logger log = LoggerFactory.getLogger(Channel_App_SMS_VariableExtractor_1_0.class);

    @Override
    public List<VariableDataValue> doExtract(Integer channelDataId) {
        return null;
    }

    /**
     * 添加变量集
     */
    private void addEquifaxUserCreditVariable(JSONObject jsonObj, VariableData variableData) {
        try {
            if (jsonObj.getString("destination") != null) {
                variableData.addValueList("destination", "目标对象", jsonObj.getString("destination"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("time") != null) {
                variableData.addValueList("time", "短信接收时间", jsonObj.getString("time"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("content") != null) {
                variableData.addValueList("content", "短信内容", jsonObj.getString("content"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("read") != null) {
                variableData.addValueList("read", "是否已读", jsonObj.getString("read"), DataTypeEnum.STRING.getValue());
            }
        }catch (Exception e) {
            log.error("app短信内容变量抽取异常： " + e);
            throw new VariableException("解析异常：" + e);
        }
    }
}
