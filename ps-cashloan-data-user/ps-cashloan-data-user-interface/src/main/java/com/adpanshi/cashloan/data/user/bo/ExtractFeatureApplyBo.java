package com.adpanshi.cashloan.data.user.bo;


import com.adpanshi.cashloan.data.feature.enums.FeatureType;

import java.util.List;

/**
 * 抽取特征申请
 * Created by zsw on 2018/6/29 0029.
 */
public class ExtractFeatureApplyBo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 特征类型
     */
    private FeatureType featureType;
    /**
     *用到的变量ID
     */
    private List<Integer> userVariableIdList;

    public FeatureType getFeatureType() {
        return featureType;
    }

    public void setFeatureType(FeatureType featureType) {
        this.featureType = featureType;
    }

    public List<Integer> getUserVariableIdList() {
        return userVariableIdList;
    }

    public void setUserVariableIdList(List<Integer> userVariableIdList) {
        this.userVariableIdList = userVariableIdList;
    }

    public ExtractFeatureApplyBo(){
        super();
    }
    public ExtractFeatureApplyBo(FeatureType featureType, List<Integer> userVariableIdList){
        super();
        this.featureType = featureType;
        this.userVariableIdList = userVariableIdList;
    }
}
