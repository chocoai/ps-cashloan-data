package com.adpanshi.cashloan.data.variable.extractor;

import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.appdata.bo.AppCallRecordDataBo;
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
 * Created by zsw on 2018/8/1 0001.
 * 通话记录变量提取
 */
@Service
public class Channel_App_CallRecord_VariableExtractor_1_0 implements VariableExtractor {
    private static Logger log = LoggerFactory.getLogger(Channel_App_CallRecord_VariableExtractor_1_0.class);

    @Resource
    private AppDataDomain appDataDomain;
    @Override
    public List<VariableDataValue> doExtract(Integer channelDataId) {
        AppCallRecordDataBo callRecordDataBo = appDataDomain.getAppCallRecordData(channelDataId);
        JSONArray jsonArray = JSONObject.parseArray(callRecordDataBo.getOriginalData());
        VariableData variableData = new VariableData();
        addAppCallRecordVariable(jsonArray,variableData);
        return variableData.getValueList();
    }

    /**
     * 添加变量集
     */
    private void addAppCallRecordVariable(JSONArray jsonArray, VariableData variableData) {
        if (jsonArray == null) {
            return;
        }
        try {
            List<JSONArray> variableDataValueList = new ArrayList<>();
            for (Object obj : jsonArray) {
                if (obj instanceof JSONObject) {
                    JSONObject jsonObj = (JSONObject) obj;
                    JSONArray array = new JSONArray();
                    if (jsonObj.getString("name") != null) {
                        array.add(new VariableDataValue("name", "目标对象名称", jsonObj.getString("name"), DataTypeEnum.STRING.getValue()));
                    }
                    if (jsonObj.getString("matchedNumber") != null) {
                        array.add(new VariableDataValue("matchedNumber", "目标对象号码", jsonObj.getString("matchedNumber"), DataTypeEnum.STRING.getValue()));
                    }
                    if (jsonObj.getString("date") != null) {
                        String dateStr=DateUtil.dateToString(DateUtil.getDate(Long.parseLong(jsonObj.getString("date"))),DateUtil.ymdhmsSSSFormat);
                        array.add(new VariableDataValue("date", "通话日期", dateStr, DataTypeEnum.DATE.getValue()));
                    }
                    if (jsonObj.getString("duration") != null) {
                        array.add(new VariableDataValue("duration", "通话时长", jsonObj.getString("duration"), DataTypeEnum.STRING.getValue()));
                    }
                    if (jsonObj.getString("type") != null) {
                        array.add(new VariableDataValue("type", "通话类型（呼入 呼出）", jsonObj.getString("type"), DataTypeEnum.STRING.getValue()));
                    }
                    if (jsonObj.getString("location") != null) {
                        array.add(new VariableDataValue("location", "目标对象地址", jsonObj.getString("location"), DataTypeEnum.STRING.getValue()));
                    }
                    variableDataValueList.add(array);
                }
            }
            variableData.addValueList("callRecordDetailList", "通话记录详细列表", variableDataValueList, DataTypeEnum.ARRAY.getValue());
        } catch (Exception e) {
            log.error("app通话记录变量抽取异常： " + e);
            throw new VariableException("解析异常：" + e);
        }
    }


}
