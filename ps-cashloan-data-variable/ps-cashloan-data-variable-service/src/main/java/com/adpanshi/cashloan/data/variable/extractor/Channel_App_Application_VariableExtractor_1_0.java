package com.adpanshi.cashloan.data.variable.extractor;

import com.adpanshi.cashloan.data.appdata.bo.AppApplicationDataBo;
import com.adpanshi.cashloan.data.appdata.domain.AppDataDomain;
import com.adpanshi.cashloan.data.common.enums.DataTypeEnum;
import com.adpanshi.cashloan.data.thirdparty.moxie.bo.MoxieSIMBo;
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
 * APP应用变量抽取
 * Created by zsw on 2018/8/7 0007.
 */
@Service
public class Channel_App_Application_VariableExtractor_1_0  implements VariableExtractor {
    private static Logger log = LoggerFactory.getLogger(Channel_App_Application_VariableExtractor_1_0.class);

    @Resource
    private AppDataDomain appDataDomain;

    @Override
    public List<VariableDataValue> doExtract(Integer channelDataId) {
        //调用应用信息原始数据接口
        AppApplicationDataBo applicationDataBo = appDataDomain.getAppApplicationData(channelDataId);
        JSONArray jsonArray = JSONObject.parseArray(applicationDataBo.getOriginalData());
        VariableData variableData = new VariableData();
        addAppApplicationVariable(jsonArray, variableData);
        return variableData.getValueList();
    }

    /**
     * 添加变量集
     */
    private void addAppApplicationVariable(JSONArray jsonArray, VariableData variableData) {
        try{
            List<JSONArray> variableDataValueList = new ArrayList<>();
            for (Object o : jsonArray) {
                if (o instanceof JSONObject) {
                    JSONObject jsonObject = (JSONObject)o;
                    JSONArray array = new JSONArray();
                    array.add(new VariableDataValue("userId", "用户数据ID", jsonObject.getString("userId"), DataTypeEnum.STRING.getValue()));
                    array.add(new VariableDataValue("appName", "APP名称", jsonObject.getString("appName"), DataTypeEnum.STRING.getValue()));
                    array.add(new VariableDataValue("packageName", "APP包名", jsonObject.getString("packageName"), DataTypeEnum.STRING.getValue()));
                    array.add(new VariableDataValue("iexpress", "是否失效", jsonObject.getString("iexpress"), DataTypeEnum.STRING.getValue()));
                    array.add(new VariableDataValue("systemType", "系统类型", jsonObject.getString("systemType"), DataTypeEnum.STRING.getValue()));
                    variableDataValueList.add(array);
                }
            }
            variableData.addValueList("appApplicationList","APP应用列表",variableDataValueList, DataTypeEnum.ARRAY.getValue());

        }catch (Exception e) {
            log.error("app通话记录变量抽取异常： " + e);
            throw new VariableException("解析异常：" + e);
        }
    }
}
