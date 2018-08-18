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
import java.util.List;

/**
 * Created by zsw on 2018/8/1 0001.
 */
@Service
public class Channel_MoxieSNS_UserWorkInfo_VariableExtractor_1_0 implements VariableExtractor {

    private static Logger log = LoggerFactory.getLogger(Channel_MoxieSNS_UserWorkInfo_VariableExtractor_1_0.class);

    @Resource
    private MoxieSNSDomain moxieSNSDomain;

    @Override
    public List<VariableDataValue> doExtract(Integer channelDataId) {
        // 调用磨盒社交信息原始数据接口
        MoxieSNSBo moxieSNSBo = moxieSNSDomain.getMetaData(channelDataId);
        JSONObject jsonObj = JSON.parseObject(moxieSNSBo.getMetaData());
        VariableData variableData = new VariableData();
        addMxSNSUserWorkInfoVariable(jsonObj, variableData);
        return variableData.getValueList();
    }

    private void addMxSNSUserWorkInfoVariable(JSONObject jsonObj, VariableData variableData){

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
        //task_data得到work_info array
        if (!taskData.containsKey("work_info")){
            return;
        }
        JSONArray workInfoArray = JSON.parseArray(taskData.getString("work_info"));
        try {
            for (int i = 0; i < workInfoArray.size(); i++) {
                JSONObject workjob = workInfoArray.getJSONObject(i);
                if (workjob.getString("company") != null) {
                    variableData.addValueList("company", "工作公司", workjob.getString("company"), DataTypeEnum.STRING.getValue());
                }
                if (workjob.getString("position") != null) {
                    variableData.addValueList("position", "工作职位", workjob.getString("position"), DataTypeEnum.STRING.getValue());
                }
                if (workjob.getString("starttime") != null) {
                    variableData.addValueList("starttime", "该公司起始时间", workjob.getString("starttime"), DataTypeEnum.STRING.getValue());
                }
                if (workjob.getString("finishtime") != null) {
                    variableData.addValueList("finishtime", "该公司离职时间", workjob.getString("finishtime"), DataTypeEnum.STRING.getValue());
                }
                if (workjob.getString("city") != null) {
                    variableData.addValueList("city", "工作城市", workjob.getString("city"), DataTypeEnum.STRING.getValue());
                }
            }
        } catch (Exception e) {
            log.error("磨盒用户SNS工作信息变量抽取异常： " + e);
            throw new VariableException("解析异常：" + e);
        }
    }
}

