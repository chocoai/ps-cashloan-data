package com.adpanshi.cashloan.data.feature.enums;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public enum CategoryTwo {
    /**
     * 地址交叉验证特征
     */
    ADDRESS_CROSS_VALIDATION("地址交叉验证特征","ADDRESS_CROSS_VALIDATION"),
    OPERATOR("运营商类特征", "OPERATOR"),
    SELF("自身属性特征","SELF"),
    MESSAGE("通信相关特征","MESSAGE"),
    FASTTOUCH("紧急联系人特性","FASTTOUCH"),
    CONSUMPTIONPORTRAIT("消费画像特征","CONSUMPTIONPORTRAIT"),
    VERFICATION("验证类属性特征","VERFICATION"),
    SELFCOURTCLASS("本人法院类特征","SELFCOURTCLASS"),
    SELFNONBANKCLASS("本人非银行类特征","SELFNONBANKCLASS"),
    SELFFRAUDCLASS("本人欺诈类特征","SELFFRAUDCLASS"),
    SELFBANKCLASS("本人银行类特征","SELFBANKCLASS"),
    CONTACTNONBANKCLASS("关联人非银行类特征","CONTACTNONBANKCLASS"),
    CONTACTFRAUDCLASS("关联人欺诈类特征","CONTACTFRAUDCLASS"),
    CONTACTBANKCLASS("关联人银行类特征","CONTACTBANKCLASS"),
    CONTACTOPERATORCLASS("关联人运营商类特征","CONTACTOPERATORCLASS"),
    SELFOPERATORCLASS("本人运营商类特征","SELFOPERATORCLASS"),
    COURTCASE("法院案件特征","COURTCASE"),
    POLICEBADINFO("公安不良特征","POLICEBADINFO"),
    INNERBLACKLIST("内部黑名单特征","INNERBLACKLIST"),
    MULTIPLELOANCLASS("多头借贷类特征","MULTIPLELOANCLASS"),
    NONBANKNETLOANCLASS("非银网贷类特征","NONBANKNETLOANCLASS"),
    INOUTDATD("收支数据特征","INOUTDATD"),
    COMMUNICATION("通信属性特征","COMMUNICATION"),
    ROOT("root状态","ROOT"),
    INNER_CROSS_VALIDATION("内部交叉验证特征","INNER_CROSS_VALIDATION"),

    EQUIFAXFRAUD("equifax信用报告反欺诈模型输出特征", "EQUIFAXFRAUD"),
    MOXIESNSCONTACTS("磨盒社交模型输出相关特征","MOXIESNSCONTACTS"),
    MOXIESIMFRAUD("磨盒SIM卡反欺诈模型输出特征","MOXIESIMFRAUD"),
    APP_APPLICATION("APP应用模型输出相关特征", "APP_APPLICATION"),
;

    private String content;
    private String value;
    private CategoryTwo(String content, String value){
        this.content = content;
        this.value = value;
    }

    public String getContent() {
        return this.content;
    }

    public String getValue() {
        return this.value;
    }

    public static CategoryTwo getByValue(String value) {
        for (CategoryTwo b : CategoryTwo.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return content;
    }
}
