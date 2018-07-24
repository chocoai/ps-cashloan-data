package com.adpanshi.cashloan.data.feature;


import com.adpanshi.cashloan.data.common.enums.ChannelBizTypeEnum;
import com.adpanshi.cashloan.data.common.enums.ChannelTypeEnum;
import com.adpanshi.cashloan.data.feature.bo.DataFromBo;
import com.adpanshi.cashloan.data.feature.bo.FeatureDataBo;
import com.adpanshi.cashloan.data.feature.domain.FeatureDomain;
import com.adpanshi.cashloan.data.feature.enums.FeatureType;
import com.adpanshi.cashloan.data.variable.bo.VariableDataBo;
import com.adpanshi.cashloan.data.variable.domain.VariableDomain;
import com.adpanshi.cashloan.data.variable.enums.VariableType;
import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 特征测试用例
 * Created by zsw on 2018/6/29 0029.
 */
public class FeatureTest {

    private FeatureDomain remote = RemoteFactory.getRemote(FeatureDomain.class, "1.0.0");

    private static final String account = "15267011679";
    private static final String aadhaarNo = "1234567890";
    private static final String equipmentFingerpints = "qwertyuiop";
    private static final String name = "周善文";
    private static final Integer featureDataId = 1;
    private static final FeatureType featureType = FeatureType.USER_BASICINFO_ADDRESS_CROSS_VALIDATION;
    private static final String mobile = "15267011679";
    private static final String email = "1119439642@qq.com";
    /**
     * 抽取特征
     */
    @Test
    public void extractFeature() {
        List<DataFromBo> dataFromBoList = new ArrayList<>();
        DataFromBo dataFromBo = new DataFromBo();
        dataFromBo.setVariableDataId(3);
        dataFromBo.setVariableExtractVersion("channel_App_UserBasicInfo_VariableExtractor_1_0");
        dataFromBo.setVariableType(VariableType.USER_BASICINFO);
        dataFromBo.setChannelDataCreateTime("2018-07-15 22:02:24 112");
        dataFromBo.setCreateTime("2018-07-15 22:02:24 112");
        dataFromBoList.add(dataFromBo);
        FeatureDataBo featureDataBo = remote.extractFeature(dataFromBoList, featureType, mobile, email, aadhaarNo, name, equipmentFingerpints);
        System.err.println(JSONObject.toJSONString(featureDataBo));
    }

    /**
     * 获取特征信息
     */
    @Test
    public void getFeatureDataById() {
        FeatureDataBo featureDataBo = remote.getFeatureDataById(featureDataId);
        System.err.println(JSONObject.toJSONString(featureDataBo));

    }

}
