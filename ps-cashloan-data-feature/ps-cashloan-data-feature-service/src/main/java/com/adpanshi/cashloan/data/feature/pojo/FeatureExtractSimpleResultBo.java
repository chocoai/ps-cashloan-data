package com.adpanshi.cashloan.data.feature.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class FeatureExtractSimpleResultBo implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Integer> dataFromVariableIdList;//用到了哪些变量
    private List<FeatureDataValue> dataValueList;//抽取出的特征内容

    public List<Integer> getDataFromVariableIdList() {
        return dataFromVariableIdList;
    }

    public void setDataFromVariableIdList(List<Integer> dataFromVariableIdList) {
        this.dataFromVariableIdList = dataFromVariableIdList;
    }

    public List<FeatureDataValue> getDataValueList() {
        return dataValueList;
    }

    public void setDataValueList(List<FeatureDataValue> dataValueList) {
        this.dataValueList = dataValueList;
    }
}
