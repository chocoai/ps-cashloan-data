package com.adpanshi.cashloan.data.variable.bo;


import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.data.common.enums.ChannelBizTypeEnum;
import com.adpanshi.cashloan.data.common.enums.ChannelTypeEnum;

import java.io.Serializable;

/**
 * 变量抽取结果数据来源
 * Created by zsw on 2018/6/29 0029.
 */
public class DataFromBo implements Serializable ,BeanUtil.ConversionCustomizble {
    private static final long serialVersionUID = 1L;

    private ChannelTypeEnum channelTypeEnum;
    private ChannelBizTypeEnum channelBizTypeEnum;
    private Integer channelDataId;
    private String createTime;

    public ChannelTypeEnum getChannelTypeEnum() {
        return channelTypeEnum;
    }

    public void setChannelTypeEnum(ChannelTypeEnum channelTypeEnum) {
        this.channelTypeEnum = channelTypeEnum;
    }

    public ChannelBizTypeEnum getChannelBizTypeEnum() {
        return channelBizTypeEnum;
    }

    public void setChannelBizTypeEnum(ChannelBizTypeEnum channelBizTypeEnum) {
        this.channelBizTypeEnum = channelBizTypeEnum;
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
        Object channelType = BeanUtil.getPropValue(o, "channelTypeEnum");
        if (channelType != null && channelType instanceof Integer) {
            this.setChannelTypeEnum(ChannelTypeEnum.getByValue((Integer) channelType));
        }
        Object channelBizType = BeanUtil.getPropValue(o, "channelBizTypeEnum");
        if (channelBizType != null && channelBizType instanceof Integer) {
            this.setChannelBizTypeEnum(ChannelBizTypeEnum.getByValue((Integer) channelBizType));
        }
    }
}
