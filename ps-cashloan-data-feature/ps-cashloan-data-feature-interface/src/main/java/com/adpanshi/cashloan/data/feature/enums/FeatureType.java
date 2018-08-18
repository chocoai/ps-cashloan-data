package com.adpanshi.cashloan.data.feature.enums;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public enum FeatureType {
    /**
     * 用户基本属性_地址交叉验证特征
     */
    USER_BASICINFO_ADDRESS_CROSS_VALIDATION("用户基本属性_地址交叉验证特征","USER_BASICINFO_ADDRESS_CROSS_VALIDATION",CategoryOne.USER_BASICINFO, CategoryTwo.ADDRESS_CROSS_VALIDATION),
    USER_BASICINFO_OPERATOR("用户基本属性_运营商类特征", "USER_BASICINFO_OPERATOR",CategoryOne.USER_BASICINFO, CategoryTwo.OPERATOR),
    USER_BASICINFO_SELF("用户基本属性_自身属性特征","USER_BASICINFO_SELF",CategoryOne.USER_BASICINFO, CategoryTwo.SELF),
    USER_BASICINFO_FASTTOUCH("用户基本属性_紧急联系人特性","USER_BASICINFO_FASTTOUCH",CategoryOne.USER_BASICINFO, CategoryTwo.FASTTOUCH),

    USER_SOCIAL_OPERATOR("社交特性_运营商类特征", "USER_SOCIAL_OPERATOR",CategoryOne.USER_SOCIAL, CategoryTwo.OPERATOR),
    USER_SOCIAL_MESSAGE("社交特性_通信相关特征","USER_SOCIAL_MESSAGE",CategoryOne.USER_SOCIAL, CategoryTwo.MESSAGE),
    USER_FRAUD_OPERATOR("欺诈特性_运营商类特征", "USER_FRAUD_OPERATOR",CategoryOne.USER_FRAUD, CategoryTwo.OPERATOR),

    USER_SOCIAL_INNER_CROSS_VALIDATION("社交属性_内部交叉验证特征","USER_SOCIAL_INNER_CROSS_VALIDATION",CategoryOne.USER_SOCIAL, CategoryTwo.INNER_CROSS_VALIDATION),
    USER_CONSUMPTION_CONSUMPTIONPORTRAIT("用户消费行为数据_消费画像特征","USER_CONSUMPTION_CONSUMPTIONPORTRAIT",CategoryOne.USER_CONSUMPTION, CategoryTwo.CONSUMPTIONPORTRAIT),
    USER_BASICINFO_VERFICATION("用户基本属性_验证类属性特征","USER_BASICINFO_VERFICATION",CategoryOne.USER_BASICINFO, CategoryTwo.VERFICATION),
    USER_BADRECORD_SELFCOURTCLASS("用户不良记录_本人法院类特征","USER_BADRECORD_SELFCOURTCLASS",CategoryOne.USER_BADRECORD, CategoryTwo.SELFCOURTCLASS),

    USER_BADRECORD_SELFNONBANKCLASS("用户不良记录_本人非银行类特征","USER_BADRECORD_SELFNONBANKCLASS",CategoryOne.USER_BADRECORD, CategoryTwo.SELFNONBANKCLASS),
    USER_BADRECORD_SELFFRAUDCLASS("用户不良记录_本人欺诈类特征","USER_BADRECORD_SELFFRAUDCLASS",CategoryOne.USER_BADRECORD, CategoryTwo.SELFFRAUDCLASS),
    USER_BADRECORD_SELFBANKCLASS("用户不良记录_本人银行类特征","USER_BADRECORD_SELFBANKCLASS",CategoryOne.USER_BADRECORD, CategoryTwo.SELFBANKCLASS),
    USER_BADRECORD_SELFOPERATORCLASS("用户不良记录_本人运营商类特征","USER_BADRECORD_SELFOPERATORCLASS",CategoryOne.USER_BADRECORD, CategoryTwo.SELFOPERATORCLASS),
    USER_BADRECORD_CONTACTNONBANKCLASS("用户不良记录_关联人非银行类特征","USER_BADRECORD_CONTACTNONBANKCLASS",CategoryOne.USER_BADRECORD, CategoryTwo.CONTACTNONBANKCLASS),

    USER_BADRECORD_CONTACTFRAUDCLASS("用户不良记录_关联人欺诈类特征","USER_BADRECORD_CONTACTFRAUDCLASS",CategoryOne.USER_BADRECORD, CategoryTwo.CONTACTFRAUDCLASS),
    USER_BADRECORD_CONTACTBANKCLASS("用户不良记录_关联人银行类特征","USER_BADRECORD_CONTACTBANKCLASS",CategoryOne.USER_BADRECORD, CategoryTwo.CONTACTBANKCLASS),
    USER_BADRECORD_CONTACTOPERATORCLASS("用户不良记录_关联人运营商类特征","USER_BADRECORD_CONTACTOPERATORCLASS",CategoryOne.USER_BADRECORD, CategoryTwo.CONTACTOPERATORCLASS),
    USER_IDENTITY_COURTCASE("用户身份特质_法院案件特征","USER_IDENTITY_COURTCASE",CategoryOne.USER_IDENTITY, CategoryTwo.COURTCASE),
    USER_IDENTITY_POLICEBADINFO("用户身份特质_公安不良特征","USER_IDENTITY_POLICEBADINFO",CategoryOne.USER_IDENTITY, CategoryTwo.POLICEBADINFO),

    USER_IDENTITY_INNERBLACKLIST("用户身份特质_内部黑名单特征","USER_IDENTITY_INNERBLACKLIST",CategoryOne.USER_IDENTITY, CategoryTwo.INNERBLACKLIST),
    USER_CREDITLOAN_MULTIPLELOANCLASS("用户信贷历史记录_多头借贷类特征","USER_CREDITLOAN_MULTIPLELOANCLASS",CategoryOne.USER_CREDITLOAN, CategoryTwo.MULTIPLELOANCLASS),
    USER_CREDITLOAN_NONBANKNETLOANCLASS("用户信贷历史记录_非银网贷类特征","USER_CREDITLOAN_NONBANKNETLOANCLASS",CategoryOne.USER_CREDITLOAN, CategoryTwo.NONBANKNETLOANCLASS),
    USER_FINANCE_INOUTDATD("用户财务状况_收支数据特征","USER_FINANCE_INOUTDATD",CategoryOne.USER_FINANCE, CategoryTwo.INOUTDATD),

    USER_DEVICE_ROOT("用户设备属性_root状态","USER_DEVICE_ROOT",CategoryOne.USER_DEVICE, CategoryTwo.ROOT),
    USER_DEVICE_APPLICATION("用户设备属性_root状态","USER_DEVICE_APPLICATION",CategoryOne.USER_DEVICE, CategoryTwo.APP_APPLICATION),
    USER_COMMUNICATION_VERFICATION("用户基本属性_通信属性特征","USER_COMMUNICATION_VERFICATION",CategoryOne.USER_BASICINFO, CategoryTwo.COMMUNICATION),
    USER_FRAUD_EQUIFAXCREDITREPORT("欺诈特性_equifax信用报告反欺诈模型输出特征","USER_FRAUD_EQUIFAXCREDITREPORT",CategoryOne.USER_FRAUD, CategoryTwo.EQUIFAXFRAUD),
    USER_SOCIAL_MOXIESNS("社交特性_魔盒反欺诈模型输出特征", "USER_SOCIAL_MOXIESNS",CategoryOne.USER_SOCIAL, CategoryTwo.MOXIESNSCONTACTS),
    USER_FRAUD_MOXIESIM("欺诈特性_魔盒反欺诈模型输出特征", "USER_FRAUD_MOXIESIM",CategoryOne.USER_FRAUD, CategoryTwo.MOXIESIMFRAUD),
    USER_VERFICATION_PANCARD("欺诈特性_盘卡反欺诈模型输出特征", "USER_VERFICATION_PANCARD",CategoryOne.USER_VERFICATION, CategoryTwo.VERFICATION),

    ;

    private String content;
    private String value;
    private CategoryOne categoryOne;
    private CategoryTwo categoryTwo;


    private FeatureType(String content, String value,CategoryOne categoryOne,CategoryTwo categoryTwo) {
        this.content = content;
        this.value = value;
        this.categoryOne=categoryOne;
        this.categoryTwo=categoryTwo;
    }

    public static FeatureType getByValue(String value) {
        for (FeatureType b : FeatureType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        return null;
    }

    public String getContent() {
        return this.content;
    }

    public String getValue() {
        return this.value;
    }

    public CategoryOne getCategoryOne() {
        return categoryOne;
    }

    public CategoryTwo getCategoryTwo() {
        return categoryTwo;
    }

    @Override
    public String toString() {
        return content;
    }

}
