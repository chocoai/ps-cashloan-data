package com.adpanshi.cashloan.data.user;

import com.adpanshi.cashloan.common.enums.OrganizationEnum;
import com.adpanshi.cashloan.common.utils.DateUtil;
import com.adpanshi.cashloan.data.common.enums.ChannelBizTypeEnum;
import com.adpanshi.cashloan.data.common.enums.ChannelTypeEnum;
import com.adpanshi.cashloan.data.feature.enums.FeatureType;
import com.adpanshi.cashloan.data.user.bo.*;
import com.adpanshi.cashloan.data.user.domain.UserDataDomain;
import com.adpanshi.cashloan.data.variable.enums.VariableType;
import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.Test;
import tool.util.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class DataUserTest {

    private UserDataDomain remote = RemoteFactory.getRemote(UserDataDomain.class, "1.0.0");


    private static final String mobile = "15267011679";
    private static final String aadhaarNo = "1234567890";
    private static final String email = "zhoushanwen8502@adpanshi.com";
    private static final String deviceFingerprint = "qwertyuiop";
    private static final Integer userDataId = 2;
    private static final String account = "15267011679";
    private static final String name = "周善文";
    private static final Integer channelDataId = 3;
    private static final ChannelTypeEnum CHANNEL_TYPE_ENUM = ChannelTypeEnum.AADHAAR;
    private static final ChannelBizTypeEnum CHANNEL_BIZ_TYPE_ENUM = ChannelBizTypeEnum.APP_USER_BASE_INFO;
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
        UserDataBo userDataBo = remote.get(userDataId);
        String userDataBoStr = JSONObject.toJSONString(userDataBo);
        System.err.println(userDataBoStr);
    }

    /**
     * 修改用户数据
     */
    @Test
    public void modify() {
        UserBaseDataBo userBaseDataBo = getUserBaseDataBoInstance();
        remote.modify(userDataId, userBaseDataBo);

    }

    /**
     * 新增用户原始数据
     */
    @Test
    public void addMetaData() {
        UserMetaDataBo userMetaDataBo = new UserMetaDataBo();
        userMetaDataBo.setChannelType(CHANNEL_TYPE_ENUM);
        userMetaDataBo.setChannelBizType(CHANNEL_BIZ_TYPE_ENUM);
        userMetaDataBo.setChannelDataId(channelDataId);
        userMetaDataBo.setCreateTime(DateUtil.dateToString(new Date(), DateUtil.ymdhmsSSSFormat));
        remote.addMetaData(userDataId, userMetaDataBo);
    }

    private UserVariableBo getUserVariableBoInstance() {
        UserVariableBo userVariableBo = new UserVariableBo();
        userVariableBo.setCreateTime("2018-07-15 22:02:24 112");
        userVariableBo.setVariableDataId(3);
        userVariableBo.setVariableExtractVersion("channel_App_UserBasicInfo_VariableExtractor_1_0");
        userVariableBo.setVariableType(VariableType.USER_BASICINFO);

        UserMetaDataBo userMetaDataBo = new UserMetaDataBo();
        userMetaDataBo.setChannelDataId(2);
        userMetaDataBo.setChannelBizType(ChannelBizTypeEnum.APP_USER_BASE_INFO);
        userMetaDataBo.setChannelType(ChannelTypeEnum.AADHAAR);
        userMetaDataBo.setCreateTime("2018-07-15 22:02:24 112");

        userVariableBo.setUserVariableDataFrom(userMetaDataBo);
        return userVariableBo;
    }
    private UserBaseDataBo getUserBaseDataBoInstance() {
        UserBaseDataBo userBaseDataBo = new UserBaseDataBo();
        userBaseDataBo.setAadhaarNo(aadhaarNo);
        userBaseDataBo.setCountryType(OrganizationEnum.CountryType.INDIA);
        userBaseDataBo.setProductType(OrganizationEnum.ProductType.OLOAN);
        userBaseDataBo.setDeviceFingerprint(deviceFingerprint);
        userBaseDataBo.setEmail(email);
        userBaseDataBo.setMobile(mobile);
        userBaseDataBo.setName(name);
        return userBaseDataBo;
    }
}
