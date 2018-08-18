package com.adpanshi.cashloan.data.user;

import com.adpanshi.cashloan.common.enums.OrganizationEnum;
import com.adpanshi.cashloan.data.common.enums.ChannelBizType;
import com.adpanshi.cashloan.data.common.enums.ChannelType;
import com.adpanshi.cashloan.data.user.bo.*;
import com.adpanshi.cashloan.data.user.domain.UserDataDomain;
import com.adpanshi.cashloan.data.variable.enums.VariableType;
import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.Test;
import tool.util.StringUtil;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class DataUserTest {

    private UserDataDomain remote = RemoteFactory.getRemote(UserDataDomain.class, "1.0.0");


    private static final String MOBILE = "15267011679";
    private static final String AADHAAR_NO = "1234567890";
    private static final String EMAIL = "zhoushanwen8502@adpanshi.com";
    private static final String DEVICE_FINGERPRINT = "qwertyuiop";
    private static final Integer USER_DATA_ID = 6;
    private static final String ACCOUNT = "15267011679";
    private static final String NAME = "周善文";
    private static final Integer CHANNEL_DATA_ID = 3;
    private static final ChannelType CHANNEL_TYPE_ENUM = ChannelType.AADHAAR;
    private static final ChannelBizType CHANNEL_BIZ_TYPE_ENUM = ChannelBizType.APP_USER_BASE_INFO;
    /**
     * 创建用户数据
     */
    @Test
    public void create(){

        UserBaseDataBo userBaseDataBo = getUserBaseDataBoInstance();
        UserDataBo userDataBo = remote.create(userBaseDataBo);
        String userDataBoStr = JSONObject.toJSONString(userDataBo);
        assert !StringUtil.isBlank(userDataBoStr):userDataBoStr;
    }


    /**
     * 获取用户数据
     */
    @Test
    public void get() {
        UserDataBo userDataBo = remote.get(USER_DATA_ID);
        String userDataBoStr = JSONObject.toJSONString(userDataBo);
        System.err.println(userDataBoStr);
    }

    /**
     * 修改用户数据
     */
    @Test
    public void modify() {
        UserBaseDataBo userBaseDataBo = getUserBaseDataBoInstance();
        remote.modify(USER_DATA_ID, userBaseDataBo);

    }

    @Test
    public void oloanLoanApply() {
        String loanApplyResult = remote.oloanLoanApply(USER_DATA_ID);
        System.err.println(loanApplyResult);
    }
    private UserVariableBo getUserVariableBoInstance() {
        UserVariableBo userVariableBo = new UserVariableBo();
        userVariableBo.setCreateTime("2018-07-15 22:02:24 112");
        userVariableBo.setVariableDataId(3);
        userVariableBo.setVariableExtractVersion("channel_App_UserBasicInfo_VariableExtractor_1_0");
        userVariableBo.setVariableType(VariableType.USER_BASICINFO);

        UserMetaDataBo userMetaDataBo = new UserMetaDataBo();
        userMetaDataBo.setChannelDataId(2);
        userMetaDataBo.setChannelBizType(ChannelBizType.APP_USER_BASE_INFO);
        userMetaDataBo.setChannelType(ChannelType.AADHAAR);
        userMetaDataBo.setCreateTime("2018-07-15 22:02:24 112");

        userVariableBo.setUserVariableDataFrom(userMetaDataBo);
        return userVariableBo;
    }
    private UserBaseDataBo getUserBaseDataBoInstance() {
        UserBaseDataBo userBaseDataBo = new UserBaseDataBo();
        userBaseDataBo.setAadhaarNo(AADHAAR_NO);
        userBaseDataBo.setCountryType(OrganizationEnum.CountryType.INDIA);
        userBaseDataBo.setProductType(OrganizationEnum.ProductType.OLOAN);
        userBaseDataBo.setDeviceFingerprint(DEVICE_FINGERPRINT);
        userBaseDataBo.setEmail(EMAIL);
        userBaseDataBo.setMobile(MOBILE);
        userBaseDataBo.setName(NAME);
        return userBaseDataBo;
    }
}
