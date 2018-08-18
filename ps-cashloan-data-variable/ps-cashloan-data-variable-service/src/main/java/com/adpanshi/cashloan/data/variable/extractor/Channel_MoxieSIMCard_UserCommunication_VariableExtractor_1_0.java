package com.adpanshi.cashloan.data.variable.extractor;


import com.adpanshi.cashloan.data.common.enums.DataTypeEnum;
import com.adpanshi.cashloan.data.thirdparty.moxie.bo.MoxieSIMBo;
import com.adpanshi.cashloan.data.thirdparty.moxie.domain.MoxieSIMDomain;
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
 * 用户通讯录变量抽取
 * Created by zsw on 2018/8/1 0001.
 */
@Service
public class Channel_MoxieSIMCard_UserCommunication_VariableExtractor_1_0 implements VariableExtractor {
    private static Logger log = LoggerFactory.getLogger(Channel_MoxieSIMCard_UserCommunication_VariableExtractor_1_0.class);

    @Resource
    private MoxieSIMDomain moxieSIMDomain;

    @Override
    public List<VariableDataValue> doExtract(Integer channelDataId) {
        //调用磨盒SIM卡信息原始数据接口
        MoxieSIMBo moxieSIMBo = moxieSIMDomain.getMoxieSIMData(channelDataId);
        JSONObject jsonObj = JSON.parseObject(moxieSIMBo.getMetaData());
        VariableData variableData = new VariableData();
        addMxSIMCardUserCommunicationVariable(jsonObj, variableData);
        return variableData.getValueList();
    }

    /**
     * 添加用户通讯录变量集,phone_contacts
     *
     * @param jsonObj
     * @param variableData
     */
    private void addMxSIMCardUserCommunicationVariable(JSONObject jsonObj, VariableData variableData) {
        //从最大json包获取data数据
        if (!jsonObj.containsKey("data")) {
            return;
        }
        JSONObject data = jsonObj.getJSONObject("data");
        //从data对象里取出task_data对象
        if (!data.containsKey("taskData")) {
            return;
        }
        JSONObject taskData = data.getJSONObject("taskData");
        //从task_data对象里取出phone_contacts数组
        if (!taskData.containsKey("phone_contacts")) {
            return;
        }
        String ss = taskData.getString("phone_contacts");
        JSONArray phoneContactsArray = JSON.parseArray(ss);
        try {
            for (int i = 0; i < phoneContactsArray.size(); i++) {
                JSONObject job = phoneContactsArray.getJSONObject(i);
                if (job.getString("cloud_address") != null) {
                    variableData.addValueList("cloud_address", "云端存储地址", job.getString("cloud_address"), DataTypeEnum.STRING.getValue());
                }
                if (job.getString("name") != null) {
                    variableData.addValueList("name", "名字", job.getString("name"), DataTypeEnum.STRING.getValue());
                }
                if (job.getString("company") != null) {
                    variableData.addValueList("company", "公司", job.getString("company"), DataTypeEnum.STRING.getValue());
                }
                if (job.getString("stop_call") != null) {
                    variableData.addValueList("stop_call", "阻止客户", job.getString("stop_call"), DataTypeEnum.STRING.getValue());
                }
                if (job.getJSONArray("phone_info") != null) {
                    /*
                    联系人存在多组手机号
                     */
                    ArrayList<VariableDataValue> phonelist = new ArrayList<>();
                    JSONArray phoneInfoArray = job.getJSONArray("phone_info");
                    for (int j = 0; j < phoneInfoArray.size(); j++) {
                        JSONObject phonejob = phoneInfoArray.getJSONObject(j);
                        VariableDataValue phoneType = new VariableDataValue();
                        phoneType.setKey("phone_type");
                        phoneType.setContent("电话号码类型");
                        phoneType.setValue(phonejob.getString("phone_type"));
                        phonelist.add(phoneType);
                        VariableDataValue phone = new VariableDataValue();
                        phone.setKey("phone");
                        phone.setContent("电话号码");
                        phone.setValue(phonejob.getString("phone"));
                        phonelist.add(phone);
                    }
                    variableData.addValueList("phone_info", "电话信息", phonelist, DataTypeEnum.ARRAY.getValue());
                }
            }
        } catch (Exception e) {
            log.error("磨盒用户通讯录变量抽取异常： " + e);
            throw new VariableException("解析异常：" + e);
        }
    }
}
