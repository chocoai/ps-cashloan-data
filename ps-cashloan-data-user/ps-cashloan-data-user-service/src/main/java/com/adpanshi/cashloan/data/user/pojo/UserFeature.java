package com.adpanshi.cashloan.data.user.pojo;


import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.data.feature.enums.FeatureType;

import java.util.List;

/**
 * 用户特征数据
 * Created by zsw on 2018/6/29 0029.
 */
public class UserFeature implements java.io.Serializable , BeanUtil.ConversionCustomizble{
    private static final long serialVersionUID = 1L;

    private String featureExtractVersion;//特征数据抽取程序版本
    private String featureType;            //特征类型
    private Integer featureDataId;          //特征数据主键
    private List<UserVariable> userFeatureDataFromList;//特征数据来源
    private Integer fromUserDataId;     //来源于哪个版本的用户数据ID
    private String createTime;              //创建时间

    public String getFeatureExtractVersion() {
        return featureExtractVersion;
    }

    public void setFeatureExtractVersion(String featureExtractVersion) {
        this.featureExtractVersion = featureExtractVersion;
    }

    public String getFeatureType() {
        return featureType;
    }

    public void setFeatureType(String featureType) {
        this.featureType = featureType;
    }

    public Integer getFeatureDataId() {
        return featureDataId;
    }

    public void setFeatureDataId(Integer featureDataId) {
        this.featureDataId = featureDataId;
    }

    public List<UserVariable> getUserFeatureDataFromList() {
        return userFeatureDataFromList;
    }

    public void setUserFeatureDataFromList(List<UserVariable> userFeatureDataFromList) {
        this.userFeatureDataFromList = userFeatureDataFromList;
    }

    public Integer getFromUserDataId() {
        return fromUserDataId;
    }

    public void setFromUserDataId(Integer fromUserDataId) {
        this.fromUserDataId = fromUserDataId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public void convertOthers(Object o) {
        Object featureType = BeanUtil.getPropValue(o, "featureType");
        if (featureType != null && featureType instanceof FeatureType) {
            this.setFeatureType((((FeatureType) featureType).getValue()));
        }
    }
}
