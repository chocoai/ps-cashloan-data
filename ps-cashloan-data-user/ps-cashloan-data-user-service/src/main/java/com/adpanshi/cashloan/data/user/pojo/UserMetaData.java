package com.adpanshi.cashloan.data.user.pojo;

import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.data.common.enums.ChannelBizType;
import com.adpanshi.cashloan.data.common.enums.ChannelType;

/**
 * 用户元数据
 * Created by zsw on 2018/6/29 0029.
 */
public class UserMetaData implements java.io.Serializable, BeanUtil.ConversionCustomizble {
    private static final long serialVersionUID = 1L;

    private Integer channelType;        //渠道类型
    private Integer channelBizType;     //渠道业务类型
    private Integer channelDataId;      //渠道数据主键
    private String createTime;//原始数据创建时间

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

    @Override
    public void convertOthers(Object o) {
        Object channelType = BeanUtil.getPropValue(o, "channelType");
        if (channelType != null && channelType instanceof ChannelType) {
            this.setChannelType(((ChannelType) channelType).getValue());
        }

        Object channelBizType = BeanUtil.getPropValue(o, "channelBizType");
        if (channelBizType != null && channelBizType instanceof ChannelBizType) {
            this.setChannelBizType(((ChannelBizType) channelBizType).getValue());
        }
    }
}
