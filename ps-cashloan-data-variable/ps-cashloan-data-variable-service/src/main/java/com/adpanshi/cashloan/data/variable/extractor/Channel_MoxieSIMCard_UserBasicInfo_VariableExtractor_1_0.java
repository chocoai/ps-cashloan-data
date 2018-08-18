package com.adpanshi.cashloan.data.variable.extractor;

import com.adpanshi.cashloan.data.common.enums.DataTypeEnum;
import com.adpanshi.cashloan.data.thirdparty.moxie.bo.MoxieSIMBo;
import com.adpanshi.cashloan.data.thirdparty.moxie.domain.MoxieSIMDomain;
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
 * 磨盒用户基本信息变量抽取
 * Created by zsw on 2018/8/1 0001.
 */
@Service
public class Channel_MoxieSIMCard_UserBasicInfo_VariableExtractor_1_0 implements VariableExtractor {

    private static Logger log = LoggerFactory.getLogger(Channel_MoxieSIMCard_UserBasicInfo_VariableExtractor_1_0.class);


    @Resource
    private MoxieSIMDomain moxieSIMDomain;

    @Override
    public List<VariableDataValue> doExtract(Integer channelDataId) {
        //调用磨盒SIM卡信息原始数据接口
        MoxieSIMBo moxieSIMBo = moxieSIMDomain.getMoxieSIMData(channelDataId);
        JSONObject jsonObj = JSON.parseObject(moxieSIMBo.getMetaData());
        VariableData variableData = new VariableData();
        addMxSIMCardUserBasicInfoVariable(jsonObj, variableData);
        return variableData.getValueList();
    }

    /**
     * 添加磨盒变量集,profile
     *
     * @param jsonObj
     * @param variableData
     */
    private void addMxSIMCardUserBasicInfoVariable(JSONObject jsonObj, VariableData variableData) {
        //从最大json包获取data数据
        if (!jsonObj.containsKey("data")) {
            return;
        }
        JSONObject data = jsonObj.getJSONObject("data");
        //从data对象里取出task_data对象
        if (!data.containsKey("task_data")) {
            return;
        }
        JSONObject taskData = data.getJSONObject("task_data");
        //从task_data对象里取出profile对象
        if (!taskData.containsKey("profile")) {
            return;
        }
        JSONObject profile = taskData.getJSONObject("profile");

        try {
            if (profile.getString("phone") != null) {
                variableData.addValueList("phone", "手机号", profile.getString("phone"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("mobile_brand") != null) {
                variableData.addValueList("mobile_brand", "手机品牌", profile.getString("mobile_brand"), DataTypeEnum.STRING.getValue());
            }
            if (profile.getString("os_type") != null) {
                variableData.addValueList("os_type", "系统类型", profile.getString("os_type"), DataTypeEnum.STRING.getValue());
            }
        } catch (Exception e) {
            log.error("磨盒用户基本属性变量抽取异常： " + e);
            throw new VariableException("解析异常：" + e);
        }

    }
}
