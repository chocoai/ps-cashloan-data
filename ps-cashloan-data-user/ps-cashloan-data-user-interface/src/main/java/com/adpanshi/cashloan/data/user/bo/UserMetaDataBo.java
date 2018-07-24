package com.adpanshi.cashloan.data.user.bo;


import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.data.common.enums.ChannelBizTypeEnum;
import com.adpanshi.cashloan.data.common.enums.ChannelTypeEnum;

/**
 * 用户元数据
 * Created by zsw on 2018/6/29 0029.
 */
public class UserMetaDataBo implements java.io.Serializable, BeanUtil.ConversionCustomizble {
    private static final long serialVersionUID = 1L;

    private ChannelTypeEnum channelType;        //渠道类型
    private ChannelBizTypeEnum channelBizType;     //渠道业务类型
    private Integer channelDataId;      //渠道数据主键
    private String createTime;//原始数据创建时间

    public UserMetaDataBo() {}

    public UserMetaDataBo(ChannelTypeEnum channelType, ChannelBizTypeEnum channelBizType, Integer channelDataId, String createTime) {
        this.setChannelType(channelType);
        this.setChannelBizType(channelBizType);
        this.setChannelDataId(channelDataId);
        this.setCreateTime(createTime);
    }
    public ChannelTypeEnum getChannelType() {
        return channelType;
    }

    public void setChannelType(ChannelTypeEnum channelType) {
        this.channelType = channelType;
    }

    public ChannelBizTypeEnum getChannelBizType() {
        return channelBizType;
    }

    public void setChannelBizType(ChannelBizTypeEnum channelBizType) {
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
            this.setChannelType(ChannelTypeEnum.getByValue((Integer) channelType));
        }

        Object channelBizType = BeanUtil.getPropValue(o, "channelBizType");
        if (channelBizType != null && channelBizType instanceof Integer) {
            this.setChannelBizType(ChannelBizTypeEnum.getByValue((Integer) channelBizType));
        }
    }
}
