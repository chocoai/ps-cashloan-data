package com.adpanshi.cashloan.data.common.enums;


import com.adpanshi.cashloan.common.enums.ContentEnum;

/**
 * 渠道商类型
 *  Created by zsw on 2018/6/29 0029.
 */
public enum ChannelBizType implements ContentEnum {
    /**
     * APP基本信息
     */
    APP_USER_BASE_INFO("APP_用户基本信息",100),
    APP_COMMUNICATION("APP_通讯录信息", 101),
    APP_SMS("APP_短信信息", 102),
    APP_CALLRECORDS("APP_通话记录", 103),
    APP_APPLICATION("APP_应用信息", 106),
    APP_DEVICE("APP_设备信息",104),
    APP_EMERGENCY("APP_紧急联系人", 107),
    PAN_CARD_VERIFY("盘卡验证", 105),
    EQUIFAX_REPORT_ANALYZE("equifax_信用报告分析", 110),
    MOXIE_SIM_CARD_INFO("磨盒_SIM卡信息", 115),
    MOXIE_SNS_INFO("磨盒_社交信息", 120),
    CONSUMERLOANHISTORY_LOAN_RECORD("历史数据_贷款记录", 125),
    CROSS_VALIDATION_PHONE("联系top20手机号交叉验证",130),
    CROSS_VALIDATION_MASTER("本人信息交叉验证",135),
    CROSS_VALIDATION_CONTACT("通讯录交叉验证",140),
    CROSS_VALIDATION_EMERGENCYS("紧急联系人交叉验证",145),
    APP_COMPLEX("综合信息校验",150),
    TONGDUN_CREDIT_BODYGUARD_INFO("同盾_信贷保镖信息",155),
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
