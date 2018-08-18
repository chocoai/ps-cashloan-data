package com.adpanshi.cashloan.data.feature.model;

import com.adpanshi.cashloan.common.mongo.beanUtil.MongoBean;
import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.data.variable.enums.VariableType;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class DataFrom extends MongoBean implements BeanUtil.ConversionCustomizble{
    private static final long serialVersionUID = 1L;

    private Integer variableDataId;
    private String variableType;
    private String variableExtractVersion;
    /**
     * 变量创建时间
     */
    private String createTime;
    /**
     * 渠道数据创建时间
     */
    private String channelDataCreateTime;

    public DataFrom(){}

    public String getVariableType() {
        return variableType;
    }

    public void setVariableType(String variableType) {
        this.variableType = variableType;
    }

    public Integer getVariableDataId() {
        return variableDataId;
    }

    public void setVariableDataId(Integer variableDataId) {
        this.variableDataId = variableDataId;
    }

    public String getVariableExtractVersion() {
        return variableExtractVersion;
    }

    public void setVariableExtractVersion(String variableExtractVersion) {
        this.variableExtractVersion = variableExtractVersion;
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

    @Override
    public void convertOthers(Object o) {
        Object variableType = BeanUtil.getPropValue(o, "variableType");
        if (variableType != null && variableType instanceof VariableType) {
            this.setVariableType(((VariableType) variableType).getValue());
        }
    }
}