package com.adpanshi.cashloan.data.variable.extractor;

import com.adpanshi.cashloan.data.common.enums.DataTypeEnum;
import com.adpanshi.cashloan.data.thirdparty.equifax.bo.EquifaxReportDataBo;
import com.adpanshi.cashloan.data.thirdparty.equifax.domain.EquifaxCreditReportDomain;
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
 * equifax用户财务状况变量抽取
 * Created by zsw on 2018/8/1 0001.
 */
@Service
public class Channel_Equifax_UserFinance_VariableExtractor_1_0 implements VariableExtractor {

    private static Logger log = LoggerFactory.getLogger(Channel_Equifax_UserFinance_VariableExtractor_1_0.class);

    @Resource
    private EquifaxCreditReportDomain equifaxCreditReportDomain;

    @Override
    public List<VariableDataValue> doExtract(Integer channelDataId) {
        //信用报告原始数据接口
        EquifaxReportDataBo equifaxReportDataBo = equifaxCreditReportDomain.getMetaData(channelDataId);
        JSONObject jsonObj = JSON.parseObject(equifaxReportDataBo.getOriginalData());
        VariableData variableData = new VariableData();
        if (jsonObj == null) {
            return variableData.getValueList();
        }
        addEquifaxUserFinanceVariable(jsonObj, variableData);
        return variableData.getValueList();
    }

    /**
     * 添加变量集
     */
    private void addEquifaxUserFinanceVariable(JSONObject jsonObject, VariableData variableData) {
        try {
            //从最大json包获取body数据
            if (!jsonObject.containsKey("body")) {
                return;
            }
            JSONObject body = jsonObject.getJSONObject("body");
            //从body对象里取出inquiryResponse对象
            if (!body.containsKey("inquiryResponse")) {
                return;
            }
            JSONObject inquiryResponse = body.getJSONObject("inquiryResponse");
            //从inquiryResponse对象里取出reportData对象
            if (!inquiryResponse.containsKey("reportData")) {
                return;
            }
            JSONObject reportData = inquiryResponse.getJSONObject("reportData");
            //从reportData对象里取出accountDetails对象
            if (!reportData.containsKey("accountDetails")) {
                return;
            }
            JSONObject accountDetails = reportData.getJSONObject("accountDetails");
            //从accountDetails对象里得到accountList
            JSONArray accountList = JSON.parseArray(accountDetails.getString("accountList"));
            List<VariableDataValue> variableDataValueList = new ArrayList<>();
            //从accountList对象取得财务状况信息
            for (int i = 0; i < accountList.size(); i++) {
                JSONObject jsonObject1 = accountList.getJSONObject(i);
                for (String s : jsonObject1.keySet()) {
                    if ("accountNumber".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("accountNumber", "账号", jsonObject1.getString("accountNumber"), DataTypeEnum.STRING.getValue()));
                    } else if ("accountStatus".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("accountStatus", "账户状态", jsonObject1.getString("accountStatus"), DataTypeEnum.STRING.getValue()));
                    } else if ("accountType".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("accountType", "账户类型", jsonObject1.getString("accountType"), DataTypeEnum.STRING.getValue()));
                    } else if ("assetClassification".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("assetClassification", "资产类型", jsonObject1.getString("assetClassification"), DataTypeEnum.STRING.getValue()));
                    } else if ("balance".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("balance", "账户余额", jsonObject1.getString("balance"), DataTypeEnum.STRING.getValue()));
                    } else if ("collateralType".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("collateralType", "抵押品类型", jsonObject1.getString("collateralType"), DataTypeEnum.STRING.getValue()));
                    } else if ("collateralValue".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("collateralValue", "抵押品价值", jsonObject1.getString("collateralValue"), DataTypeEnum.STRING.getValue()));
                    } else if ("dateClosed".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("dateClosed", "账户关闭日期", jsonObject1.getString("dateClosed"), DataTypeEnum.STRING.getValue()));
                    } else if ("dateOpened".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("dateOpened", "账户开立日期", jsonObject1.getString("dateOpened"), DataTypeEnum.STRING.getValue()));
                    } else if ("dateReported".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("dateReported", "报告日期", jsonObject1.getString("dateReported"), DataTypeEnum.STRING.getValue()));
                    } else if ("disputeCode".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("disputeCode", "有效争议", jsonObject1.getString("disputeCode"), DataTypeEnum.STRING.getValue()));
                    } else if ("history48Months".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("history48Months", "历史48个月", jsonObject1.getString("history48Months"), DataTypeEnum.STRING.getValue()));
                    } else if ("institution".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("institution", "金融机构", jsonObject1.getString("institution"), DataTypeEnum.STRING.getValue()));
                    } else if ("interestRate".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("interestRate", "利率", jsonObject1.getString("interestRate"), DataTypeEnum.STRING.getValue()));
                    } else if ("lastPaymentDate".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("lastPaymentDate", "截至报告日期的最后一笔付款金额 ", jsonObject1.getString("lastPaymentDate"), DataTypeEnum.STRING.getValue()));
                    } else if ("open".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("open", "开放状态", jsonObject1.getString("open"), DataTypeEnum.STRING.getValue()));
                    } else if ("ownershipType".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("ownershipType", "账户的所有权类型", jsonObject1.getString("ownershipType"), DataTypeEnum.STRING.getValue()));
                    } else if ("pastDueAmount".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("pastDueAmount", "逾期金额", jsonObject1.getString("pastDueAmount"), DataTypeEnum.STRING.getValue()));
                    } else if ("reason".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("reason", "账户关闭原因", jsonObject1.getString("reason"), DataTypeEnum.STRING.getValue()));
                    } else if ("repaymentTenure".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("repaymentTenure", "还款期限", jsonObject1.getString("repaymentTenure"), DataTypeEnum.STRING.getValue()));
                    } else if ("sanctionAmount".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("sanctionAmount", "制裁金额", jsonObject1.getString("sanctionAmount"), DataTypeEnum.STRING.getValue()));
                    } else if ("suitFiledStatus".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("suitFiledStatus", "符合信贷授予机构所报告的账户的提交状态", jsonObject1.getString("suitFiledStatus"), DataTypeEnum.STRING.getValue()));
                    } else if ("termFrequency".equals(s)) {
                        variableDataValueList.add(new VariableDataValue("ermFrequency", "付款频率", jsonObject1.getString("ermFrequency"), DataTypeEnum.STRING.getValue()));
                    }
                }
            }
            variableData.addValueList("accountList", "账号列表", variableDataValueList, DataTypeEnum.ARRAY.getValue());

        }catch (Exception e) {
            log.error("用户财务状况变量抽取异常： " + e);
            throw new VariableException("解析异常：" + e);
        }
    }
}
