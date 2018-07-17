package com.adpanshi.cashloan.data.user.bo;


import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.data.feature.enums.FeatureType;

import java.util.List;

/**
 * 用户特征数据
 * Created by zsw on 2018/6/29 0029.
 */
public class UserFeatureBo implements java.io.Serializable, BeanUtil.ConversionCustomizble {
    private static final long serialVersionUID = 1L;

    private String featureExtractVersion;//特征数据抽取程序版本
    private FeatureType featureType;            //特征类型
    private Integer featureDataId;          //特征数据主键
    private List<UserVariableBo> userFeatureDataFromList;//特征数据来源
    private String createTime;              //创建时间

    public String getFeatureExtractVersion() {
        return featureExtractVersion;
    }

    public void setFeatureExtractVersion(String featureExtractVersion) {
        this.featureExtractVersion = featureExtractVersion;
    }

    public FeatureType getFeatureType() {
        return featureType;
    }

    public void setFeatureType(FeatureType featureType) {
        this.featureType = featureType;
    }

    public Integer getFeatureDataId() {
        return featureDataId;
    }

    public void setFeatureDataId(Integer featureDataId) {
        this.featureDataId = featureDataId;
    }

    public List<UserVariableBo> getUserFeatureDataFromList() {
        return userFeatureDataFromList;
    }

    public void setUserFeatureDataFromList(List<UserVariableBo> userFeatureDataFromList) {
        this.userFeatureDataFromList = userFeatureDataFromList;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public void convertOthers(Object o) {
        Object userFeatureDataFromList = BeanUtil.getPropValue(o, "userFeatureDataFromList");
        if (userFeatureDataFromList != null && userFeatureDataFromList instanceof List) {
            this.setUserFeatureDataFromList(BeanUtil.convertList(((List) userFeatureDataFromList), UserVariableBo.class));
        }

        Object userFeatureType = BeanUtil.getPropValue(o, "featureType");
        if (userFeatureType != null){
            this.setFeatureType(FeatureType.getByValue(userFeatureType.toString()));
        }
    }
}
