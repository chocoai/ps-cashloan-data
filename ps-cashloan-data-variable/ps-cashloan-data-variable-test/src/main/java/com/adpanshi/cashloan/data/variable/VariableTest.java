package com.adpanshi.cashloan.data.variable;


import com.adpanshi.cashloan.data.common.enums.ChannelBizType;
import com.adpanshi.cashloan.data.common.enums.ChannelType;
import com.adpanshi.cashloan.data.variable.bo.VariableDataBo;
import com.adpanshi.cashloan.data.variable.domain.VariableDomain;
import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.Test;

import java.util.List;

/**
 * 变量测试用例
 * Created by zsw on 2018/6/29 0029.
 */
public class VariableTest {

    private VariableDomain remote = RemoteFactory.getRemote(VariableDomain.class, "1.0.0");

    private static final ChannelType CHANNEL_TYPE = ChannelType.PSAPP;
    private static final ChannelBizType CHANNEL_BIZ_TYPE = ChannelBizType.APP_USER_BASE_INFO;
    private static final Integer CHANNEL_DATA_ID = 7;
    private static final String CHANNEL_DATA_CREATE_TIME = "2018-07-15 18:46:22 096";
    private static final String ACCOUNT = "15267011679";
    private static final String AADHAAR_NO = "1234567890";
    private static final String DEVICE_FINGERPRINT = "qwertyuiop";
    private static final String NAME = "周善文";
    private static final Integer VARIABLE_DATA_ID = 3;
    private static final String MOBILE = "15267011679";
    private static final String EMAIL = "1119439642@qq.com";
    /**
     * 抽取变量
     */
    @Test
    public void extractVariable() {
        List<VariableDataBo> variableDataBoList = remote.extractVariable(CHANNEL_TYPE, CHANNEL_BIZ_TYPE, CHANNEL_DATA_ID,
                CHANNEL_DATA_CREATE_TIME, MOBILE, EMAIL, AADHAAR_NO, NAME, DEVICE_FINGERPRINT);
        System.err.println(JSONObject.toJSONString(variableDataBoList));
    }

    /**
     * 变量ID获取变量
     */
    @Test
    public void getVariableFindById() {
        VariableDataBo variableDataBo = remote.getVariableFindById(VARIABLE_DATA_ID);
        System.err.println(JSONObject.toJSONString(variableDataBo));

    }

}
