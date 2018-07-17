package com.adpanshi.cashloan.data.variable.model;


import com.adpanshi.cashloan.common.mongo.beanUtil.MongoBean;

import java.io.Serializable;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class DataFrom extends MongoBean implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer channelType;
    private Integer channelBizType;
    private Integer channelDataId;
    private String createTime;

    public DataFrom(){}

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

    public Integer getChannelDataId() {
        return channelDataId;
    }

    public void setChannelDataId(Integer channelDataId) {
        this.channelDataId = channelDataId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
