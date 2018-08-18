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
 * 盘卡验证基本信息变量抽取
 * Created by zsw on 2018/8/1 0001.
 */
@Service
public class Channel_PanCard_Verify_VariableExtractor_1_0 implements VariableExtractor {

    private static Logger log = LoggerFactory.getLogger(Channel_PanCard_Verify_VariableExtractor_1_0.class);
    @Resource
    private AppDataDomain appDataDomain;

    @Override
    public List<VariableDataValue> doExtract(Integer channelDataId) {
        // 调用盘卡获取原始数据接口
        AppDataBo appDataBo = appDataDomain.getAppUserBaseInfoData(channelDataId);
        JSONObject jsonObj = JSON.parseObject(appDataBo.getOriginalData());
        VariableData variableData = new VariableData();
        addMxSNSUserEducationInfoVariable(jsonObj, variableData);
        return variableData.getValueList();
    }

    private void addMxSNSUserEducationInfoVariable(JSONObject jsonObj, VariableData variableData){
        try {
            if (jsonObj.getString("first_name")!=null){
                variableData.addValueList("first_name", "名", jsonObj.getString("first_name"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("last_name")!=null){
                variableData.addValueList("last_name", "姓", jsonObj.getString("last_name"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("pan_status")!=null){
                variableData.addValueList("pan_status", "盘状态（是否有效）", jsonObj.getString("pan_status"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("transaction_id")!=null){
                variableData.addValueList("transaction_id", "交易编号", jsonObj.getString("transaction_id"), DataTypeEnum.STRING.getValue());
            }
        } catch (Exception e) {
            log.error("盘卡验证基本信息变量抽取异常： " + e);
            throw new VariableException("解析异常：" + e);
        }

    }
}
