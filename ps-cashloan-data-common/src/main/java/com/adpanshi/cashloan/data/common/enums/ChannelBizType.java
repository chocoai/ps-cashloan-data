package com.adpanshi.cashloan.data.common.enums;


import com.adpanshi.cashloan.common.enums.ContentEnum;

/**
 * 渠道商类型
 *  Created by zsw on 2018/6/29 0029.
 */
public enum ChannelBizType implements ContentEnum {
    /**
     *
     */
    APP_SMS("APP_短信信息", 102),
    APP_DEVICE("APP_设备信息",104),
    APP_COMPLEX("综合信息校验",106),
    APP_CALLRECORDS("APP_通话记录", 110),
    APP_APPLICATION("APP_应用信息", 112),
    APP_EMERGENCY("APP_紧急联系人", 114),
    APP_COMMUNICATION("APP_通讯录信息", 116),
    APP_USER_BASE_INFO("APP_用户基本信息",118),
    PS_CROSS_VALIDATION_PHONE("联系top10手机号交叉验证",130),
    PS_CROSS_VALIDATION_MASTER("本人信息交叉验证",132),
    PS_CROSS_VALIDATION_CONTACT("通讯录交叉验证",134),
    PS_CROSS_VALIDATION_EMERGENCY1("紧急联系人1交叉验证",136),
    MOXIE_SIM_CARD_INFO("磨盒_SIM卡信息", 150),
    MOXIE_SNS_INFO("磨盒_社交信息", 152),
    PAN_CARD_VERIFY("盘卡验证", 160),
    PS_COMPLEX("综合信息校验",162),
    CROSS_VALIDATION_PHONE("联系top20手机号交叉验证",170),
    CROSS_VALIDATION_MASTER("本人信息交叉验证",172),
    CROSS_VALIDATION_CONTACT("通讯录交叉验证",174),
    CROSS_VALIDATION_EMERGENCYS("紧急联系人交叉验证",176),
    EQUIFAX_REPORT_ANALYZE("equifax_信用报告分析", 200),
    CONSUMERLOANHISTORY_LOAN_RECORD("历史数据_贷款记录", 210),
    TONGDUN_CREDIT_BODYGUARD_INFO("同盾_信贷保镖信息",230),
    ;


    private String content;
    private Integer value;

    @Override
    public boolean equalsValue(Integer value) {
        return (value != null) && (value.equals(getValue()));
    }

    ChannelBizType(String content, Integer value) {
        this.content = content;
        this.value = value;
    }

    public static ChannelBizType getByValue(Integer value) {
        ChannelBizType[] types = ChannelBizType.values();
        for (ChannelBizType type : types) {
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
