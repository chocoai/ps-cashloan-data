package com.adpanshi.cashloan.data.common.enums;


import com.adpanshi.cashloan.common.enums.ContentEnum;

/**
 * 渠道商类型
 *  Created by zsw on 2018/6/29 0029.
 */
public enum ChannelBizTypeEnum implements ContentEnum {
    PS_BANK_PORTRAIT("银联画像",92),
    PS_TWO_ELEMENTS_VERIFY("二元素验证", 91),
    APP_USER_BASE_INFO("APP_用户基本信息",100),
    CONSUMERLOANHISTORY_LOAN_RECORD("历史数据_贷款记录", 111),
    PS_CROSS_VALIDATION_PHONE("联系top10手机号交叉验证",120),
    PS_CROSS_VALIDATION_MASTER("本人信息交叉验证",121),
    PS_CROSS_VALIDATION_CONTACT("通讯录交叉验证",122),
    PS_CROSS_VALIDATION_EMERGENCY1("紧急联系人1交叉验证",123),
    PS_COMPLEX("综合信息校验",130),

    ;


    private String content;
    private Integer value;

    public boolean equalsValue(Integer value) {
        return (value != null) && (value.equals(getValue()));
    }

    ChannelBizTypeEnum(String content, Integer value) {
        this.content = content;
        this.value = value;
    }

    public static ChannelBizTypeEnum getByValue(Integer value) {
        ChannelBizTypeEnum[] types = ChannelBizTypeEnum.values();
        for (ChannelBizTypeEnum type : types) {
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
