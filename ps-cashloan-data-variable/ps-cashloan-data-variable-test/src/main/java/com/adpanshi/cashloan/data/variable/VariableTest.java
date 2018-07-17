package com.adpanshi.cashloan.data.variable;


import com.adpanshi.cashloan.data.common.enums.ChannelBizTypeEnum;
import com.adpanshi.cashloan.data.common.enums.ChannelTypeEnum;
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

    private static final ChannelTypeEnum CHANNEL_TYPE_ENUM = ChannelTypeEnum.AADHAAR;
    private static final ChannelBizTypeEnum CHANNEL_BIZ_TYPE_ENUM = ChannelBizTypeEnum.APP_USER_BASE_INFO;
    private static final Integer channelDataId = 2;
    private static final String channelDataCreateTime = "2018-07-15 18:46:22 096";
    private static final String account = "15267011679";
    private static final String aadhaarNo = "1234567890";
    private static final String deviceFingerprint = "qwertyuiop";
    private static final String name = "周善文";
    private static final Integer variableDataId = 3;
    /**
     * 抽取变量
     */
    @Test
    public void extractVariable() {
        List<VariableDataBo> variableDataBoList = remote.extractVariable(CHANNEL_TYPE_ENUM, CHANNEL_BIZ_TYPE_ENUM, channelDataId,
                channelDataCreateTime, account, aadhaarNo, name, deviceFingerprint);
        System.err.println(JSONObject.toJSONString(variableDataBoList));
    }

    /**
     * 变量ID获取变量
     */
    @Test
    public void getVariableFindById() {
        VariableDataBo variableDataBo = remote.getVariableFindById(variableDataId);
        System.err.println(JSONObject.toJSONString(variableDataBo));

    }

}
