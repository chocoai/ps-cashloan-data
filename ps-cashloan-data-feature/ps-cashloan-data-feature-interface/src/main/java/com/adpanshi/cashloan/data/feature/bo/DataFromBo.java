package com.adpanshi.cashloan.data.feature.bo;


import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.data.variable.enums.VariableType;

import java.io.Serializable;

/**
 * 特征数据来源
 *  Created by zsw on 2018/6/29 0029.
 */
public class DataFromBo implements Serializable ,BeanUtil.ConversionCustomizble{
    private static final long serialVersionUID = 1L;

    private Integer variableDataId;            //变量ID
    private VariableType variableType;        //变量类型
    private String variableExtractVersion;    //变量抽取版本
    private String createTime;                //变量创建时间
    private String channelDataCreateTime;     //渠道数据创建时间

    public VariableType getVariableType() {
        return variableType;
    }

    public void setVariableType(VariableType variableType) {
        this.variableType = variableType;
    }

    public Integer getVariableDataId() {
        return variableDataId;
    }

    public void setVariableDataId(Integer variableDataId) {
        this.variableDataId = variableDataId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getChannelDataCreateTime() {
        return channelDataCreateTime;
    }

    public void setChannelDataCreateTime(String channelDataCreateTime) {
        this.channelDataCreateTime = channelDataCreateTime;
    }

    public String getVariableExtractVersion() {
        return variableExtractVersion;
    }

    public void setVariableExtractVersion(String variableExtractVersion) {
        this.variableExtractVersion = variableExtractVersion;
    }

    @Override
    public void convertOthers(Object o) {
        Object variableType = BeanUtil.getPropValue(o, "variableType");
        if (variableType != null && variableType instanceof String) {
            this.setVariableType(VariableType.getByValue((String) variableType));
        }
    }
}
