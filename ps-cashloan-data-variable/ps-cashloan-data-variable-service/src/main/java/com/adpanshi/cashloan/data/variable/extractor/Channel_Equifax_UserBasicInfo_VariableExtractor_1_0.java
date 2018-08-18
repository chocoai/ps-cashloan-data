package com.adpanshi.cashloan.data.variable.extractor;

import com.adpanshi.cashloan.data.common.enums.DataTypeEnum;
import com.adpanshi.cashloan.data.thirdparty.equifax.bo.EquifaxReportDataBo;
import com.adpanshi.cashloan.data.thirdparty.equifax.domain.EquifaxCreditReportDomain;
import com.adpanshi.cashloan.data.thirdparty.moxie.bo.MoxieSIMBo;
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
 * equifax信用报告基本信息变量抽取
 * Created by zsw on 2018/8/1 0001.
 */
@Service
public class Channel_Equifax_UserBasicInfo_VariableExtractor_1_0 implements VariableExtractor {
    private static Logger log = LoggerFactory.getLogger(Channel_Equifax_UserBasicInfo_VariableExtractor_1_0.class);

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
        addEquifaxUserBasicInfoVariable(jsonObj, variableData);
        return variableData.getValueList();
    }

    /**
     * 添加变量集
     */
    private void addEquifaxUserBasicInfoVariable(JSONObject jsonObject, VariableData variableData) {

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
            if (!inquiryResponse.containsKey("reportData") && !inquiryResponse.containsKey("inquiryRequestInfo")) {
                return;
            }
            JSONObject inquiryRequestInfo = inquiryResponse.getJSONObject("inquiryRequestInfo");
            if (inquiryRequestInfo.getString("firstName") != null) {
                variableData.addValueList("firstName", "名字", inquiryRequestInfo.getString("firstName"), DataTypeEnum.STRING.getValue());
            }
            if (inquiryRequestInfo.getString("lastName") != null) {
                variableData.addValueList("firstName", "姓氏", inquiryRequestInfo.getString("lastName"), DataTypeEnum.STRING.getValue());
            }
            if (inquiryRequestInfo.getString("addrLine1") != null) {
                variableData.addValueList("addrLine1", "信用报告地址", inquiryRequestInfo.getString("addrLine1"), DataTypeEnum.STRING.getValue());
            }
            if (inquiryRequestInfo.getString("mobilePhone") != null) {
                variableData.addValueList("mobilePhone", "手机号", inquiryRequestInfo.getString("mobilePhone"), DataTypeEnum.STRING.getValue());
            }
            JSONObject reportData = inquiryResponse.getJSONObject("reportData");
            //从reportData对象里取出idAndContactInfo对象
            if (!reportData.containsKey("idAndContactInfo") && !reportData.containsKey("score") ) {
                return;
            }
            JSONObject idAndContactInfo = reportData.getJSONObject("idAndContactInfo");
            JSONObject score = reportData.getJSONObject("score");
            if (!score.containsKey("value")) {
                return;
            }
            if (score.getString("value") != null) {
                variableData.addValueList("score", "信用分", score.getString("value"), DataTypeEnum.STRING.getValue());
            }
            //从idAndContactInfo对象里取出personalInfo对象
            if (!idAndContactInfo.containsKey("personalInfo")) {
                return;
            }
            JSONObject personalInfo = idAndContactInfo.getJSONObject("personalInfo");
            //从personalInfo对象里取出name对象，从name对象里取出名字，姓氏，中间名信息
            JSONObject nameInfo = personalInfo.getJSONObject("name");
                if (nameInfo != null) {
                    for (String i : nameInfo.keySet()) {
                        if ("firstName".equals(i)) {
                            variableData.addValueList("firstName", "名字", nameInfo.getString("firstName"), DataTypeEnum.STRING.getValue());
                        } else if ("lastName".equals(i)) {
                            variableData.addValueList("lastName", "姓氏", nameInfo.getString("lastName"), DataTypeEnum.STRING.getValue());
                        } else if ("middleName".equals(i)) {
                            variableData.addValueList("middleName", "中间名", nameInfo.getString("middleName"), DataTypeEnum.STRING.getValue());
                        }
                    }
                }
            //从personalInfo对象中取出年龄，出生日期，性别，总收入信息
                for (String j: personalInfo.keySet()) {
                    if ("ages".equals(j)) {
                        variableData.addValueList("ages", "年龄", personalInfo.getString("ages"), DataTypeEnum.STRING.getValue());
                    } else if ("dateOfBirth".equals(j)) {
                        variableData.addValueList("dateOfBirth", "出生日期", personalInfo.getString("dateOfBirth"), DataTypeEnum.STRING.getValue());
                    } else if ("gender".equals(j)) {
                        variableData.addValueList("gender", "性别", personalInfo.getString("gender"), DataTypeEnum.STRING.getValue());
                    } else if ("totalIncome".equals(j)) {
                        variableData.addValueList("totalIncome", "总收入", personalInfo.getString("totalIncome"), DataTypeEnum.STRING.getValue());
                    }
                }
            //从idAndContactInfo对象中取出addressInfo对象
            JSONArray addressInfoArray = JSON.parseArray(idAndContactInfo.getString("addressInfo"));
                //从addressInfo对象中取出地址，邮编，州，住址类型信息
            if (addressInfoArray != null) {
                for (int i = 0; i < addressInfoArray.size(); i++) {
                    JSONObject jsonObject1 = addressInfoArray.getJSONObject(i);
                    for (String s : jsonObject1.keySet()) {
                        if ("address".equals(s)) {
                            variableData.addValueList("address", "地址", jsonObject1.getString("address"), DataTypeEnum.STRING.getValue());
                        } else if ("postal".equals(s)) {
                            variableData.addValueList("postal", "邮政编码", jsonObject1.getString("postal"), DataTypeEnum.STRING.getValue());
                        } else if ("state".equals(s)) {
                            variableData.addValueList("state", "归属州", jsonObject1.getString("state"), DataTypeEnum.STRING.getValue());
                        } else if ("type".equals(s)) {
                            variableData.addValueList("type", "住址类型", jsonObject1.getString("type"), DataTypeEnum.STRING.getValue());
                        }
                    }
                }
            }
            //从idAndContactInfo对象中取出emailAddressInfo对象
            JSONArray emailAddressInfoArray = JSON.parseArray(idAndContactInfo.getString("emailAddressInfo"));
            //从emailAddressInfo对象中取出电子邮件信息
            this.addemailAddressInfoArray(emailAddressInfoArray, variableData);
            //从idAndContactInfo中取出phoneInfo对象
            JSONArray phoneInfoArray = JSON.parseArray(idAndContactInfo.getString("phoneInfo"));
            //从phoneInfo对象里取出电话号码信息
            this.addphoneInfoArray(phoneInfoArray, variableData);
            //从idAndContactInfo中取出identityInfo对象
            JSONObject identityInfo = idAndContactInfo.getJSONObject("identityInfo");
            this.addIdentityInfo(identityInfo, variableData);
                }catch (Exception e) {
                    log.error("信用报告用户基本信息变量抽取异常： " + e);
                    throw new VariableException("解析异常：" + e);
        }
    }

    /**
     * 从emailAddressInfo对象中取出电子邮件信息
     * @param emailAddressInfoArray 邮箱地址信息
     * @param variableData  变量数据
     */
    private void addemailAddressInfoArray(JSONArray emailAddressInfoArray, VariableData variableData) {
        if (emailAddressInfoArray != null){
            for (int i = 0;i<emailAddressInfoArray.size();i++) {
                JSONObject jsonObject1 = emailAddressInfoArray.getJSONObject(i);
                for (String s : jsonObject1.keySet()) {
                    if ("emailAddress".equals(s)) {
                        variableData.addValueList("emailAddress", "电子邮件地址", jsonObject1.getString("emailAddress"), DataTypeEnum.STRING.getValue());
                    }
                }
            }
        }
    }
    /**
     * 从phoneInfo对象里取出电话号码信息
     * @param phoneInfoArray    手机号集合
     * @param variableData      变量数据
     */
    private void addphoneInfoArray(JSONArray phoneInfoArray, VariableData variableData) {
        if(phoneInfoArray != null){
            List<String> list = new ArrayList<>();
            for(int i = 0;i<phoneInfoArray.size();i++) {
                JSONObject jsonObject1 = phoneInfoArray.getJSONObject(i);
                for (String s : jsonObject1.keySet()) {
                    if ("number".equals(s)) {
                        list.add(jsonObject1.getString("number"));
                    }
                }
            }
            variableData.addValueList("numbers", "电话号码集合", list, DataTypeEnum.STRING.getValue());
        }
    }
    /**
     * 从identityInfo对象中取出panIdInfo对象,从panIdInfo对象中取出盘卡号码
     * @param identityInfo  identityInfo json
     * @param variableData  变量数据
     */
    private void addIdentityInfo(JSONObject identityInfo, VariableData variableData) {
        if (identityInfo.getString("panIdInfo") != null) {
            JSONArray panIdInfoArray = JSON.parseArray(identityInfo.getString("panIdInfo"));
            List<String> idNumbers = new ArrayList<>();
            for (int i = 0; i < panIdInfoArray.size(); i++) {
                JSONObject jsonObject1 = panIdInfoArray.getJSONObject(i);
                for (String s : jsonObject1.keySet()) {
                    if ("idNumber".equals(s)) {
                        idNumbers.add(jsonObject1.getString("idNumber"));
                    }
                }
            }
            variableData.addValueList("idNumbers", "PAN卡号集合", idNumbers, DataTypeEnum.STRING.getValue());
        }
        //从identityInfo对象中取出驾驶证，身份证等信息
        for (String s :identityInfo.keySet()){
            if ("driverLicences".equals(s)) {
                variableData.addValueList("driverLicences", "驾驶证", identityInfo.getString("driverLicences"), DataTypeEnum.STRING.getValue());
            } else if ("idCards".equals(s)) {
                variableData.addValueList("idCards", "身份证", identityInfo.getString("idCards"), DataTypeEnum.STRING.getValue());
            } else if ("idOthers".equals(s)) {
                variableData.addValueList("idOthers", "其他ID", identityInfo.getString("idOthers"), DataTypeEnum.STRING.getValue());
            } else if ("nationalIDCards".equals(s)) {
                variableData.addValueList("nationalIDCards", "国民卡信息", identityInfo.getString("nationalIDCards"), DataTypeEnum.STRING.getValue());
            } else if ("passportIDs".equals(s)) {
                variableData.addValueList("passportIDs", "护照ID", identityInfo.getString("passportIDs"), DataTypeEnum.STRING.getValue());
            } else if ("photoCreditCards".equals(s)) {
                variableData.addValueList("photoCreditCards", "图片版权", identityInfo.getString("photoCreditCards"), DataTypeEnum.STRING.getValue());
            } else if ("rationCards".equals(s)) {
                variableData.addValueList("rationCards", "配给卡", identityInfo.getString("rationCards"), DataTypeEnum.STRING.getValue());
            } else if ("voterIDs".equals(s)) {
                variableData.addValueList("voterIDs", "选民证", identityInfo.getString("voterIDs"), DataTypeEnum.STRING.getValue());
            }

        }
    }
}