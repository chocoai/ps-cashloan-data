package com.adpanshi.cashloan.data.common.enums;


import com.adpanshi.cashloan.common.enums.ContentEnum;

/**
 * 渠道商类型
 *  Created by zsw on 2018/6/29 0029.
 */
public enum ChannelType implements ContentEnum {

    /**
     * AADHAAR数据渠道
     */
    AADHAAR("aadhaar", 1),
    PANCARD("panCard", 2),
    EQUIFAXREPORT("equifaxReport", 3),
    OPERATOR("operator", 4),
    CIBIL("cibil", 5),
    TONGDUNCREDITBODYGUARD("tongdunCreditBodyguard", 6),
    PSAPP("盘石app",7),
    CONSUMERLOANHISTORY("consumerLoanHistory", 8),
    MOXIESIMCARD("moxieSIMCard", 9),
    MOXIESNS("moxieSNS", 10),
;
    private String content;
    private Integer value;

    @Override
    public boolean equalsValue(Integer value) {
        return (value != null) && (value.equals(getValue()));
    }

    ChannelType(String content, Integer value) {
        this.content = content;
        this.value = value;
    }

    public static ChannelType getByValue(Integer value) {
        ChannelType[] types = ChannelType.values();
        for (ChannelType type : types) {
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
        return "ChannelType{" +
                "content='" + content + '\'' +
                ", value=" + value +
                '}';
    }
}
