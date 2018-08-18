package com.adpanshi.cashloan.data.variable.extractor;

import com.adpanshi.cashloan.data.appdata.bo.AppEmergencyDataBo;
import com.adpanshi.cashloan.data.appdata.domain.AppDataDomain;
import com.adpanshi.cashloan.data.common.enums.DataTypeEnum;
import com.adpanshi.cashloan.data.variable.exception.VariableException;
import com.adpanshi.cashloan.data.variable.model.VariableData;
import com.adpanshi.cashloan.data.variable.model.VariableDataValue;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fish_coder
 * @Title: Channel_App_Emergency_VariableExtractor_1_0
 * @ProjectName fenqidai-dubbo
 * @Description: TODO
 * @date 2018/8/1017:58
 */
@Service
public class Channel_App_Emergency_VariableExtractor_1_0 implements VariableExtractor{

    private static Logger log = LoggerFactory.getLogger(Channel_App_Emergency_VariableExtractor_1_0.class);
    @Resource
    private AppDataDomain appDataDomain;

    @Override
    public List<VariableDataValue> doExtract(Integer channelDataId) {
        AppEmergencyDataBo appEmergencyDataBo = appDataDomain.getAppEmergencyData(channelDataId);
        //调用紧急联系人原始数据接口
        JSONObject jsonObject = JSONObject.parseObject(appEmergencyDataBo.getOriginalData());
        VariableData variableData = new VariableData();
        addAppEmergencyVariable(jsonObject, variableData);
        return variableData.getValueList();
    }

    public void addAppEmergencyVariable(JSONObject jsonObj,VariableData variableData){
        try {
            //保存两个紧急联系人列表
            List<JSONArray> variableDataValueList = new ArrayList<>();
            for(int i=0;i<2;i++){
                JSONArray array = new JSONArray();
                if (jsonObj.getString("name") != null) {
                    array.add(new VariableDataValue("name", "姓名", jsonObj.getString("name").split(",")[i], DataTypeEnum.STRING.getValue()));
                }
                if (jsonObj.getString("relation") != null) {
                    array.add(new VariableDataValue("relation", "关系", jsonObj.getString("relation").split(",")[i], DataTypeEnum.STRING.getValue()));
                }
                if (jsonObj.getString("type") != null) {
                    array.add(new VariableDataValue("type", "关系类型", jsonObj.getString("type").split(",")[i], DataTypeEnum.STRING.getValue()));
                }
                if (jsonObj.getString("phone") != null) {
                    array.add(new VariableDataValue("phone", "手机号", jsonObj.getString("phone").split(",")[i], DataTypeEnum.STRING.getValue()));
                }
                variableDataValueList.add(array);
            }
            variableData.addValueList("emergencyList", "紧急通信人列表", variableDataValueList, DataTypeEnum.ARRAY.getValue());
            //其他字段
            if (jsonObj.getString("mobileType") != null) {
                variableData.addValueList("firstName", "手机类型", jsonObj.getString("mobileType"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("versionNumber") != null) {
                variableData.addValueList("versionNumber", "APP版本号", jsonObj.getString("versionNumber"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("phoneBrand") != null) {
                variableData.addValueList("phoneBrand", "手机品牌", jsonObj.getString("phoneBrand"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("operatingSystem") != null) {
                variableData.addValueList("operatingSystem", "手机系统", jsonObj.getString("operatingSystem"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("systemVersions") != null) {
                variableData.addValueList("systemVersions", "手机系统版本", jsonObj.getString("systemVersions"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("phoneType") != null) {
                variableData.addValueList("phoneType", "手机类型", jsonObj.getString("phoneType"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("phoneMark") != null) {
                variableData.addValueList("phoneMark", "手机型号", jsonObj.getString("phoneMark"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("versionName") != null) {
                variableData.addValueList("versionName", "版本名", jsonObj.getString("versionName"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("versionCode") != null) {
                variableData.addValueList("versionCode", "版本码", jsonObj.getString("versionCode"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("mac") != null) {
                variableData.addValueList("mac", "mac地址", jsonObj.getString("mac"), DataTypeEnum.STRING.getValue());
            }

        }  catch (Exception e) {
            log.error("app紧急联系人变量抽取异常： " + e);
            throw new VariableException("解析异常：" + e);
        }

    }
}
