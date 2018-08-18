package com.adpanshi.cashloan.data.feature.model;

import com.adpanshi.cashloan.common.mongo.beanUtil.MongoBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class FeatureExtractConfig extends MongoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 特征类型
     */
    private String featureType;
    /**
     *变量类型
     */
    private List<String> variableTypeList;
    /**
     *特征抽取版本
     */
    private String featureExtractVersion;

    public String getFeatureType() {
        return featureType;
    }

    public void setFeatureType(String featureType) {
        this.featureType = featureType;
    }

    public List<String> getVariableTypeList() {
        return variableTypeList;
    }

    public void setVariableTypeList(List<String> variableTypeList) {
        this.variableTypeList = variableTypeList;
    }

    public String getFeatureExtractVersion() {
        return featureExtractVersion;
    }

    public void setFeatureExtractVersion(String featureExtractVersion) {
        this.featureExtractVersion = featureExtractVersion;
    }
}
