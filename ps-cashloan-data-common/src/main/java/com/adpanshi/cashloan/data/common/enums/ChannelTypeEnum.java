package com.adpanshi.cashloan.data.common.enums;


import com.adpanshi.cashloan.common.enums.ContentEnum;

/**
 * 渠道商类型
 *  Created by zsw on 2018/6/29 0029.
 */
public enum ChannelTypeEnum implements ContentEnum {

    AADHAAR("aadhaar", 1),
    PANCARD("panCard", 2),
    CREDITREPORT("creditReport", 3),
    OPERATOR("operator", 4),
    CIBIL("cibil", 5),
    TONGDUNCREDITBODYGUARD("tongduncreditbodyguard", 6),
    PSAPP("盘石app",7);
;
    private String content;
    private Integer value;

    public boolean equalsValue(Integer value) {
        return (value != null) && (value.equals(getValue()));
    }

    ChannelTypeEnum(String content, Integer value) {
        this.content = content;
        this.value = value;
    }

    public static ChannelTypeEnum getByValue(Integer value) {
        ChannelTypeEnum[] types = ChannelTypeEnum.values();
        for (ChannelTypeEnum type : types) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }


    @Override
    public String toString() {
        return "ChannelTypeEnum{" +
                "content='" + content + '\'' +
                ", value=" + value +
                '}';
    }
}
