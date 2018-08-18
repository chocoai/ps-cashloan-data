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
 * APP用户基本信息变量抽取
 * Created by zsw on 2018/6/29 0029.
 */
@Service
public class Channel_App_UserBasicInfo_VariableExtractor_1_0 implements VariableExtractor {
    private static Logger log = LoggerFactory.getLogger(Channel_App_UserBasicInfo_VariableExtractor_1_0.class);

    @Resource
    private AppDataDomain appDataDomain;

    @Override
    public List<VariableDataValue> doExtract(Integer channelDataId) {
        AppDataBo appDataBo = appDataDomain.getAppUserBaseInfoData(channelDataId);
        JSONObject jsonObj = JSON.parseObject(appDataBo.getOriginalData());
        //变量对象携带对应个体的基本信息
        VariableData variableData = new VariableData();
        addUserBaseInfoVariable(jsonObj,variableData);
        return variableData.getValueList();
    }

    /**
     * 添加变量集
     */
    private void addUserBaseInfoVariable(JSONObject jsonObj, VariableData variableData) {
        try {
            if (jsonObj.getString("firstName") != null) {
                variableData.addValueList("firstName", "姓名", jsonObj.getString("firstName"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("lastName") != null) {
                variableData.addValueList("lastName", "姓氏", jsonObj.getString("lastName"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("registerAddr") != null) {
                variableData.addValueList("registerAddr", "注册地址", jsonObj.getString("registerAddr"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("livingImg") != null) {
                variableData.addValueList("livingImg", "手持aadhaar照片", jsonObj.getString("livingImg"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("dateBirthday") != null) {
                    variableData.addValueList("dateBirthday", "出生日期", jsonObj.getString("dateBirthday"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("education") != null) {
                variableData.addValueList("education", "学历", jsonObj.getString("education"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("sex") != null) {
                variableData.addValueList("sex", "性别", jsonObj.getString("sex"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("companyName") != null) {
                variableData.addValueList("companyName", "公司名称", jsonObj.getString("companyName"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("companyAddr") != null) {
                variableData.addValueList("companyAddr", "公司地址", jsonObj.getString("companyAddr"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("workingYears") != null) {
                variableData.addValueList("workingYears", "工作年限", jsonObj.getString("workingYears"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("mobileType") != null) {
                variableData.addValueList("mobileType", "手机类型", jsonObj.getString("mobileType"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("liveAddr") != null) {
                variableData.addValueList("liveAddr", "常住地址", jsonObj.getString("liveAddr"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("panNumber") != null) {
                variableData.addValueList("panNumber", "盘卡号", jsonObj.getString("panNumber"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("salary") != null) {
                variableData.addValueList("salary", "薪资", jsonObj.getString("salary"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("userId") != null) {
                variableData.addValueList("userId", "用户ID", jsonObj.getString("userId"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("idNo") != null) {
                variableData.addValueList("idNo", "aadhaarNo", jsonObj.getString("idNo"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("versionNumber") != null) {
                variableData.addValueList("versionNumber", "APP版本号", jsonObj.getString("versionNumber"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("detailAddr") != null) {
                variableData.addValueList("detailAddr", "详细地址", jsonObj.getString("detailAddr"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("companyPhone") != null) {
                variableData.addValueList("companyPhone", "公司电话", jsonObj.getString("companyPhone"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("pinCode") != null) {
                variableData.addValueList("pinCode", "地区code码", jsonObj.getString("pinCode"), DataTypeEnum.STRING.getValue());
            }
            if (jsonObj.getString("liveCoordinate") != null) {
                variableData.addValueList("liveCoordinate", "经纬度", jsonObj.getString("liveCoordinate"), DataTypeEnum.STRING.getValue());
            }

        } catch (Exception e) {
            log.error("app用户基本属性变量抽取异常： " + e);
            throw new VariableException("解析异常：" + e);
        }
    }
}