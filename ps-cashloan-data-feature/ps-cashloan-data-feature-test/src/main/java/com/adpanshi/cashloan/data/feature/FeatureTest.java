package com.adpanshi.cashloan.data.feature;


import com.adpanshi.cashloan.data.feature.bo.DataFromBo;
import com.adpanshi.cashloan.data.feature.bo.FeatureDataBo;
import com.adpanshi.cashloan.data.feature.domain.FeatureDomain;
import com.adpanshi.cashloan.data.feature.enums.FeatureType;
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

    private static final String ACCOUNT = "15267011679";
    private static final String AADHAAR_NO = "1234567890";
    private static final String EQUIPMENT_FINGERPINTS = "qwertyuiop";
    private static final String NAME = "周善文";
    private static final Integer FEATURE_DATA_ID = 1;
    private static final FeatureType FEATURE_TYPE = FeatureType.USER_BASICINFO_VERFICATION;
    private static final String MOBILE = "15267011679";
    private static final String EMAIL = "1119439642@qq.com";
    /**
     * 抽取特征
     */
    @Test
    public void extractFeature() {
        List<DataFromBo> dataFromBoList = new ArrayList<>();
        DataFromBo dataFromBo = new DataFromBo();
        dataFromBo.setVariableDataId(6);
        dataFromBo.setVariableExtractVersion("channel_App_UserBasicInfo_VariableExtractor_1_0");
        dataFromBo.setVariableType(VariableType.USER_BASICINFO);
        dataFromBo.setChannelDataCreateTime("2018-07-15 22:02:24 112");
        dataFromBo.setCreateTime("2018-07-15 22:02:24 112");
        dataFromBoList.add(dataFromBo);
        FeatureDataBo featureDataBo = remote.extractFeature(dataFromBoList, FEATURE_TYPE, MOBILE, EMAIL, AADHAAR_NO, NAME, EQUIPMENT_FINGERPINTS);
        System.err.println(JSONObject.toJSONString(featureDataBo));
    }

    /**
     * 获取特征信息
     */
    @Test
    public void getFeatureDataById() {
        FeatureDataBo featureDataBo = remote.getFeatureDataById(FEATURE_DATA_ID);
        System.err.println(JSONObject.toJSONString(featureDataBo));

    }

    @Test
    public void findFeatureList() {
        List<Integer> featureList = new ArrayList<>();
        featureList.add(1);
        List<FeatureDataBo> featureDataBoList = remote.findFeatureList(featureList);
        System.err.println(JSONObject.toJSONString(featureDataBoList));
    }

}
