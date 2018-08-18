package com.adpanshi.cashloan.data.variable.extractor;

import com.adpanshi.cashloan.data.common.enums.DataTypeEnum;
import com.adpanshi.cashloan.data.thirdparty.equifax.bo.EquifaxReportDataBo;
import com.adpanshi.cashloan.data.thirdparty.equifax.domain.EquifaxCreditReportDomain;
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
 * equifax用户信用报告变量抽取
 * Created by zsw on 2018/8/1 0001.
 */
@Service
public class Channel_Equifax_UserCredit_VariableExtractor_1_0 implements VariableExtractor {

    private static Logger log = LoggerFactory.getLogger(Channel_Equifax_UserCredit_VariableExtractor_1_0.class);

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
        addEquifaxUserCreditVariable(jsonObj, variableData);
        return variableData.getValueList();
    }

    /**
     * 添加变量集
     */
    private void addEquifaxUserCreditVariable(JSONObject jsonObject, VariableData variableData) {
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
            //从reportData对象里取出accountSummary对象
            if (!reportData.containsKey("accountSummary")) {
                return;
            }
            JSONObject accountSummary = reportData.getJSONObject("accountSummary");

            //从accountSummary对象取出信用报告各项数据
            for (String key : accountSummary.keySet()) {

                if ("noOfAccount".equals(key)) {
                    variableData.addValueList("noOfAccount", "账户总数", accountSummary.getString("noOfAccount"), DataTypeEnum.STRING.getValue());
                } else if ("noOfActiveAccounts".equals(key)) {
                    variableData.addValueList("noOfActiveAccounts", "过去一年开户数", accountSummary.getString("noOfActiveAccounts"), DataTypeEnum.STRING.getValue());
                } else if ("noOfWriteOffs".equals(key)) {
                    variableData.addValueList("noOfWriteOffs", "注销账户数", accountSummary.getString("noOfWriteOffs"), DataTypeEnum.STRING.getValue());
                } else if ("totalPastDue".equals(key)) {
                    variableData.addValueList("totalPastDue", "逾期金额", accountSummary.getString("totalPastDue"), DataTypeEnum.STRING.getValue());
                } else if ("mostSevereStatusWithIn24Months".equals(key)) {
                    variableData.addValueList("mostSevereStatusWithIn24Months", "2年内最严重行为", accountSummary.getString("mostSevereStatusWithIn24Months"), DataTypeEnum.STRING.getValue());
                } else if ("singleHighestCredit".equals(key)) {
                    variableData.addValueList("singleHighestCredit", "单次最高信用额度", accountSummary.getString("singleHighestCredit"), DataTypeEnum.STRING.getValue());
                } else if ("singleHighestSanctionAmount".equals(key)) {
                    variableData.addValueList("singleHighestSanctionAmount", "单次最高贷款金额", accountSummary.getString("singleHighestSanctionAmount"), DataTypeEnum.STRING.getValue());
                } else if ("totalHighCredit".equals(key)) {
                    variableData.addValueList("totalHighCredit", "最高额度总和", accountSummary.getString("totalHighCredit"), DataTypeEnum.STRING.getValue());
                } else if ("averageOpenBalance".equals(key)) {
                    variableData.addValueList("averageOpenBalance", "平均账户余额", accountSummary.getString("averageOpenBalance"), DataTypeEnum.STRING.getValue());
                } else if ("singleHighestBalance".equals(key)) {
                    variableData.addValueList("singleHighestBalance", "最高账户余额", accountSummary.getString("singleHighestBalance"), DataTypeEnum.STRING.getValue());
                } else if ("noOfPastDueAccounts".equals(key)) {
                    variableData.addValueList("noOfPastDueAccounts", "逾期次数", accountSummary.getString("noOfPastDueAccounts"), DataTypeEnum.STRING.getValue());
                } else if ("noOfZeroBalanceAccounts".equals(key)) {
                    variableData.addValueList("noOfZeroBalanceAccounts", "空账户数", accountSummary.getString("noOfZeroBalanceAccounts"), DataTypeEnum.STRING.getValue());
                } else if ("recentAccount".equals(key)) {
                    variableData.addValueList("recentAccount", "最新开户情况", accountSummary.getString("recentAccount"), DataTypeEnum.STRING.getValue());
                } else if ("oldestAccount".equals(key)) {
                    variableData.addValueList("oldestAccount", "最早开户情况", accountSummary.getString("oldestAccount"), DataTypeEnum.STRING.getValue());
                } else if ("totalBalanceAmount".equals(key)) {
                    variableData.addValueList("totalBalanceAmount", "总账户余额", accountSummary.getString("totalBalanceAmount"), DataTypeEnum.STRING.getValue());
                } else if ("totalSanctionAmount".equals(key)) {
                    variableData.addValueList("totalSanctionAmount", "总贷款金额", accountSummary.getString("totalSanctionAmount"), DataTypeEnum.STRING.getValue());
                } else if ("totalCreditLimit".equals(key)) {
                    variableData.addValueList("totalCreditLimit", "信用卡限额总和", accountSummary.getString("totalCreditLimit"), DataTypeEnum.STRING.getValue());
                } else if ("totalMonthlyPaymentAmount".equals(key)) {
                    variableData.addValueList("totalMonthlyPaymentAmount", "每月支付金额", accountSummary.getString("totalMonthlyPaymentAmount"), DataTypeEnum.STRING.getValue());
                }
            }
            //从reportData对象里取出score对象
            if (!reportData.containsKey("score")) {
                return;
            }
            JSONObject score = reportData.getJSONObject("score");
            //从score对象里取出用户名和信用分
            if (score != null){
                for (String s : score.keySet()){
                    if ("name".equals(s)) {
                        variableData.addValueList("name", "用户名", score.getString("name"), DataTypeEnum.STRING.getValue());
                    } else if ("value".equals(s)) {
                        variableData.addValueList("value", "信用分", score.getString("value"), DataTypeEnum.STRING.getValue());
                    }
                }
            }
        }catch (Exception e) {
            log.error("用户信用报告变量抽取异常： " + e);
            throw new VariableException("解析异常：" + e);
        }
    }
}
