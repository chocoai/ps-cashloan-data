package com.adpanshi.cashloan.data.variable.model;


import com.adpanshi.cashloan.common.mongo.beanUtil.MongoBean;

import java.io.Serializable;

/**
 * 变量抽取配置
 * Created by zsw on 2018/6/29 0029.
 */
public class VariableExtractConfig extends MongoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer channelType;
    private Integer channelBizType;
    private String variableExtractVersion;
    private String variableType;

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public Integer getChannelBizType() {
        return channelBizType;
    }

    public void setChannelBizType(Integer channelBizType) {
        this.channelBizType = channelBizType;
    }

    public String getVariableExtractVersion() {
        return variableExtractVersion;
    }

    public void setVariableExtractVersion(String variableExtractVersion) {
        this.variableExtractVersion = variableExtractVersion;
    }

    public String getVariableType() {
        return variableType;
    }

    public void setVariableType(String variableType) {
        this.variableType = variableType;
    }
}
