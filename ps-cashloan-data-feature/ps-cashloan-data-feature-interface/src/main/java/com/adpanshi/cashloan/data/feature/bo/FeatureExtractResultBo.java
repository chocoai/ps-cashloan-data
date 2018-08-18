package com.adpanshi.cashloan.data.feature.bo;


import com.adpanshi.cashloan.data.feature.enums.FeatureType;

import java.io.Serializable;
import java.util.List;

/**
 * 特征抽取结果
 *  Created by zsw on 2018/6/29 0029.
 */
public class FeatureExtractResultBo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 特征抽取版本
     */
    private String featureExtractVersion;
    /**
     *特征类型
     */
    private FeatureType featureType;
    /**
     * 特征数据主键
     */
    private Integer featureDataId;
    /**
     * 特征数据来源
     */
    private List<DataFromBo> dataFromBoList;

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

    public List<DataFromBo> getDataFromBoList() {
        return dataFromBoList;
    }

    public void setDataFromBoList(List<DataFromBo> dataFromBoList) {
        this.dataFromBoList = dataFromBoList;
    }
}
