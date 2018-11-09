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
public class Channel_MoxieSNS_UserEducationInfo_VariableExtractor_1_0 implements VariableExtractor {
    private static Logger log = LoggerFactory.getLogger(Channel_MoxieSNS_UserEducationInfo_VariableExtractor_1_0.class);


    @Resource
    private MoxieSNSDomain moxieSNSDomain;

    @Override
    public List<VariableDataValue> doExtract(Integer channelDataId) {
        // 调用磨盒社交信息原始数据接口
        MoxieSNSBo moxieSNSBo = moxieSNSDomain.getMoxieSNSMetaData(channelDataId);
        JSONObject jsonObj = JSON.parseObject(moxieSNSBo.getMetaData());
        VariableData variableData = new VariableData();
        addMxSNSUserEducationInfoVariable(jsonObj, variableData);
        return variableData.getValueList();
    }

    private void addMxSNSUserEducationInfoVariable(JSONObject jsonObj, VariableData variableData) {
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
        //从task_data对象里取出education_info
        if (!taskData.containsKey("education_info")) {
            return;
        }
        JSONObject educationInfo = taskData.getJSONObject("education_info");
        try {
            if (educationInfo.getJSONArray("college_info") != null) {
                //大学info array
                ArrayList<VariableDataValue> collegeList = new ArrayList<>();
                JSONArray collegeJsonArray = educationInfo.getJSONArray("college_info");
                for (int j = 0; j < collegeJsonArray.size(); j++) {
                    JSONObject collegejob = collegeJsonArray.getJSONObject(j);
                    //college_name
                    VariableDataValue collegeNameValue = new VariableDataValue();
                    collegeNameValue.setKey("college_name");
                    collegeNameValue.setContent("大学校名");
                    collegeNameValue.setValue(collegejob.getString("college_name"));
                    collegeList.add(collegeNameValue);
                    //college_address
                    VariableDataValue collegeAddressValue = new VariableDataValue();
                    collegeAddressValue.setKey("college_address");
                    collegeAddressValue.setContent("大学地址");
                    collegeAddressValue.setValue(collegejob.getString("college_address"));
                    collegeList.add(collegeAddressValue);
                    //college_graduation
                    VariableDataValue collegeGraduationValue = new VariableDataValue();
                    collegeGraduationValue.setKey("college_graduation");
                    collegeGraduationValue.setContent("大学毕业时间");
                    collegeGraduationValue.setValue(collegejob.getString("college_graduation"));
                    collegeList.add(collegeGraduationValue);
                }

                variableData.addValueList("college_info", "大学信息", collegeList, DataTypeEnum.ARRAY.getValue());
            }
            if (educationInfo.getJSONArray("highschool_info") != null) {
                //高中info array
                ArrayList<VariableDataValue> highschoolList = new ArrayList<>();
                JSONArray highschoolJsonArray = educationInfo.getJSONArray("highschool_info");
                for (int j = 0; j < highschoolJsonArray.size(); j++) {
                    JSONObject highschoolJob = highschoolJsonArray.getJSONObject(j);
                    //college_name
                    VariableDataValue collegeNameValue = new VariableDataValue();
                    collegeNameValue.setKey("highschool_name");
                    collegeNameValue.setContent("大学校名");
                    collegeNameValue.setValue(highschoolJob.getString("highschool_name"));
                    highschoolList.add(collegeNameValue);
                    //college_address
                    VariableDataValue collegeAddressValue = new VariableDataValue();
                    collegeAddressValue.setKey("highschool_address");
                    collegeAddressValue.setContent("大学地址");
                    collegeAddressValue.setValue(highschoolJob.getString("highschool_address"));
                    highschoolList.add(collegeAddressValue);
                    //college_graduation
                    VariableDataValue collegeGraduationValue = new VariableDataValue();
                    collegeGraduationValue.setKey("college_graduation");
                    collegeGraduationValue.setContent("高中毕业时间");
                    collegeGraduationValue.setValue(highschoolJob.getString("highschool_graduation"));
                    highschoolList.add(collegeGraduationValue);
                }
                variableData.addValueList("highschool_info", "高中信息", highschoolList, DataTypeEnum.ARRAY.getValue());
            }
        } catch (Exception e) {
            log.error("磨盒用户SNS工作信息变量抽取异常： " + e);
            throw new VariableException("解析异常：" + e);
        }

    }
}
