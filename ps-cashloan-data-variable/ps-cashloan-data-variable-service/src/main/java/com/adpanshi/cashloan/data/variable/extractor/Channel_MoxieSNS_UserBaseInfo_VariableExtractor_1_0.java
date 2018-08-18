package com.adpanshi.cashloan.data.variable.extractor;

import com.adpanshi.cashloan.data.common.enums.DataTypeEnum;
import com.adpanshi.cashloan.data.thirdparty.moxie.bo.MoxieSNSBo;
import com.adpanshi.cashloan.data.thirdparty.moxie.domain.MoxieSNSDomain;
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
 * Created by zsw on 2018/8/1 0001.
 */
@Service
public class Channel_MoxieSNS_UserBaseInfo_VariableExtractor_1_0 implements VariableExtractor {
    private static Logger log = LoggerFactory.getLogger(Channel_MoxieSNS_UserBaseInfo_VariableExtractor_1_0.class);

    @Resource
    private MoxieSNSDomain moxieSNSDomain;

    @Override
    public List<VariableDataValue> doExtract(Integer channelDataId) {
        // 调用磨盒社交信息原始数据接口
        MoxieSNSBo moxieSNSBo = moxieSNSDomain.getMetaData(channelDataId);
        JSONObject jsonObj = JSON.parseObject(moxieSNSBo.getMetaData());
        VariableData variableData = new VariableData();
        addMxSNSUserBaseInfoVariable(jsonObj, variableData);
        return variableData.getValueList();
    }

    private void addMxSNSUserBaseInfoVariable(JSONObject jsonObj, VariableData variableData) {
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
        //task_data得到base_info
        if (!taskData.containsKey("base_info")) {
            return;
        }
        JSONObject baseInfo = taskData.getJSONObject("base_info");
        try {
            if (baseInfo.getString("nickname") != null) {
                variableData.addValueList("nickname", "昵称", baseInfo.getString("nickname"), DataTypeEnum.STRING.getValue());
            }
            if (baseInfo.getString("currentcity") != null) {
                variableData.addValueList("currentcity", "现居地", baseInfo.getString("currentcity"), DataTypeEnum.STRING.getValue());
            }
            if (baseInfo.getString("hometown") != null) {
                variableData.addValueList("hometown", "家乡", baseInfo.getString("hometown"), DataTypeEnum.STRING.getValue());
            }
            if (baseInfo.getString("birthdate") != null) {
                variableData.addValueList("birthdate", "出生月日", baseInfo.getString("birthdate"), DataTypeEnum.STRING.getValue());
            }
            if (baseInfo.getString("birthyear") != null) {
                variableData.addValueList("birthyear", "出生年", baseInfo.getString("birthyear"), DataTypeEnum.STRING.getValue());
            }
            if (baseInfo.getString("gender") != null) {
                variableData.addValueList("gender", "性别", baseInfo.getString("gender"), DataTypeEnum.STRING.getValue());
            }
            if (baseInfo.getString("emails") != null) {
                //emails 循环添加至list,list 添加至valueList
                JSONArray emailsArray = JSON.parseArray(baseInfo.getString("emails"));
                List<String> emailList = new ArrayList<String>();
                for (int i = 0; i < emailsArray.size(); i++) {
                    emailList.add(emailsArray.get(i).toString());
                }
                variableData.addValueList("emails", "邮箱", emailList, DataTypeEnum.ARRAY.getValue());
            }
            if (baseInfo.getString("blood_type") != null) {
                variableData.addValueList("blood_type", "血型", baseInfo.getString("blood_type"), DataTypeEnum.STRING.getValue());
            }
            if (baseInfo.getString("language") != null) {
                variableData.addValueList("language", "语言", baseInfo.getString("language"), DataTypeEnum.STRING.getValue());
            }
            if (baseInfo.getString("religion_trend") != null) {
                variableData.addValueList("religion_trend", "宗教趋向", baseInfo.getString("religion_trend"), DataTypeEnum.STRING.getValue());
            }
            if (baseInfo.getString("political_trend") != null) {
                variableData.addValueList("political_trend", "政治趋向", baseInfo.getString("political_trend"), DataTypeEnum.STRING.getValue());
            }
            if (baseInfo.getString("relationshipstatus") != null) {
                variableData.addValueList("relationshipstatus", "关系状态", baseInfo.getString("relationshipstatus"), DataTypeEnum.STRING.getValue());
            }
            if (baseInfo.getString("friendsnum") != null) {
                variableData.addValueList("friendsnum", "好友数量", baseInfo.getString("friendsnum"), DataTypeEnum.STRING.getValue());
            }
            //数组，多个手机信息
            if (baseInfo.getString("mobilephones") != null) {
                //phones 数组内为String
                JSONArray phonesArray = JSON.parseArray(baseInfo.getString("mobilephones"));
                List<String> phonesList = new ArrayList<String>();
                for (int i = 0; i < phonesArray.size(); i++) {
                    phonesList.add(phonesArray.get(i).toString());
                }
                variableData.addValueList("mobilephones", "手机号码", phonesList, DataTypeEnum.ARRAY.getValue());
            }
        } catch (Exception e) {
            log.error("磨盒用户SNS基本属性变量抽取异常： " + e);
            throw new VariableException("解析异常：" + e);
        }

    }
}
