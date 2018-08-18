package com.adpanshi.cashloan.data.variable.extractor;

import com.adpanshi.cashloan.data.common.enums.DataTypeEnum;
import com.adpanshi.cashloan.data.variable.model.VariableData;
import com.adpanshi.cashloan.data.variable.model.VariableDataValue;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zsw on 2018/8/1 0001.
 * 设备信息变量提取
 */
@Service
public class Channel_App_Device_VariableExtractor_1_0 implements VariableExtractor {
    private static Logger log = LoggerFactory.getLogger(Channel_App_Device_VariableExtractor_1_0.class);

    @Override
    public List<VariableDataValue> doExtract(Integer channelDataId) {
        return null;
    }

    /**
     * 添加变量集
     */
    private void addAppDeviceVariable(JSONObject jsonObj, VariableData variableData) {
        if (jsonObj.getString("mobileType") != null){
            variableData.addValueList("mobileType","手机类型",jsonObj.getString("mobileType"), DataTypeEnum.STRING.getValue());
        }
        if (jsonObj.getString("versionNumber") != null){
            variableData.addValueList("versionNumber","版本号",jsonObj.getString("versionNumber"), DataTypeEnum.STRING.getValue());
        }
        if (jsonObj.getString("userId") != null){
            variableData.addValueList("userId","用户ID",jsonObj.getString("userId"), DataTypeEnum.STRING.getValue());
        }
        if (jsonObj.getString("appName") != null){
            variableData.addValueList("appName","app名字",jsonObj.getString("appName"), DataTypeEnum.STRING.getValue());
        }
        if (jsonObj.getString("packageName") != null){
            variableData.addValueList("packageName","app包名",jsonObj.getString("packageName"), DataTypeEnum.STRING.getValue());
        }
        if (jsonObj.getString("iexpress") != null){
            variableData.addValueList("iexpress","",jsonObj.getString("iexpress"), DataTypeEnum.STRING.getValue());
        }
        if (jsonObj.getString("systemType") != null){
            variableData.addValueList("systemType","系统类型",jsonObj.getString("systemType"), DataTypeEnum.STRING.getValue());
        }
    }
}
