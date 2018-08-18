package com.adpanshi.cashloan.data.variable.bo;


import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.data.common.enums.ChannelBizType;
import com.adpanshi.cashloan.data.common.enums.ChannelType;

import java.io.Serializable;

/**
 * 变量抽取结果数据来源
 * Created by zsw on 2018/6/29 0029.
 */
public class DataFromBo implements Serializable ,BeanUtil.ConversionCustomizble {
    private static final long serialVersionUID = 1L;

    private ChannelType channelType;
    private ChannelBizType channelBizType;
    private Integer channelDataId;
    private String createTime;

    public ChannelType getChannelType() {
        return channelType;
    }

    public void setChannelType(ChannelType channelType) {
        this.channelType = channelType;
    }

    public ChannelBizType getChannelBizType() {
        return channelBizType;
    }

    public void setChannelBizType(ChannelBizType channelBizType) {
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

    @Override
    public void convertOthers(Object o) {
        Object channelType = BeanUtil.getPropValue(o, "channelType");
        if (channelType != null && channelType instanceof Integer) {
            this.setChannelType(ChannelType.getByValue((Integer) channelType));
        }
        Object channelBizType = BeanUtil.getPropValue(o, "channelBizType");
        if (channelBizType != null && channelBizType instanceof Integer) {
            this.setChannelBizType(ChannelBizType.getByValue((Integer) channelBizType));
        }
    }
}
