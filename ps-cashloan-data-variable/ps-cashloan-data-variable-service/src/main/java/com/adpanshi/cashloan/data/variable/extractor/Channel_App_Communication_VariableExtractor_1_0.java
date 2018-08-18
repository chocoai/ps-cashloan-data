package com.adpanshi.cashloan.data.variable.extractor;

import com.adpanshi.cashloan.data.appdata.bo.AppCommunicationDataBo;
import com.adpanshi.cashloan.data.appdata.bo.AppDataBo;
import com.adpanshi.cashloan.data.appdata.domain.AppDataDomain;
import com.adpanshi.cashloan.data.common.enums.DataTypeEnum;
import com.adpanshi.cashloan.data.variable.exception.VariableException;
import com.adpanshi.cashloan.data.variable.model.VariableData;
import com.adpanshi.cashloan.data.variable.model.VariableDataValue;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * APP通讯录变量抽取
 * Created by zsw on 2018/8/1 0001.
 */
@Service
public class Channel_App_Communication_VariableExtractor_1_0 implements VariableExtractor {
    private static Logger log = LoggerFactory.getLogger(Channel_App_Communication_VariableExtractor_1_0.class);

    @Resource
    private AppDataDomain appDataDomain;

    @Override
    public List<VariableDataValue> doExtract(Integer channelDataId) {
        AppCommunicationDataBo appCommunicationDataBo = appDataDomain.getAppCommunicationData(channelDataId);
        JSONArray jsonArray = JSONObject.parseArray(appCommunicationDataBo.getOriginalData());
        VariableData variableData = new VariableData();
        addAppCommunicationVariable(jsonArray, variableData);
        return variableData.getValueList();
    }

    /**
     * 添加变量集
     */
    private void addAppCommunicationVariable(JSONArray jsonArray,VariableData variableData) {
        try{
            List<JSONArray> variableDataValueList = new ArrayList<>();
            for (Object o : jsonArray) {
                if (o instanceof JSONObject) {
                    JSONObject jsonObject = (JSONObject)o;
                    JSONArray array = new JSONArray();
                    array.add(new VariableDataValue("phone", "通讯录手机号", jsonObject.getString("phone"), DataTypeEnum.STRING.getValue()));
                    array.add(new VariableDataValue("name", "通讯录姓名", jsonObject.getString("name"), DataTypeEnum.STRING.getValue()));
                    variableDataValueList.add(array);
                }
            }
            variableData.addValueList("appCommunicationList","APP通讯录列表",variableDataValueList, DataTypeEnum.ARRAY.getValue());

        }catch (Exception e) {
            log.error("app通讯录变量抽取异常： " + e);
            throw new VariableException("解析异常：" + e);
        }
    }
}
