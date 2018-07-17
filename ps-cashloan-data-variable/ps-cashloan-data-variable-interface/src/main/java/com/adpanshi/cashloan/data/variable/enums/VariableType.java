package com.adpanshi.cashloan.data.variable.enums;


/**
 * 变量类别
 * Created by zsw on 2018/6/29 0029.
 */
public enum VariableType {

    USER_BASICINFO("用户基本属性","USER_BASICINFO"),
    USER_CONTACTS_INFO("用户社交属性","USER_CONTACTS_INFO"),
    USER_CHEAT("用户欺诈类属性","USER_CHEAT"),
    USER_CREDIT("用户信用属性","USER_CREDIT"),
    USER_FASTTOUCH("用户紧急联系人属性","USER_FASTTOUCH"),
    PS_USER_BADRECORD("用户不良记录","PS_USER_BADRECORD"),
    USER_DEVICEINFO_ATTRIBUTE("用户设备属性","USER_DEVICEINFO_ATTRIBUTE"),
    PS_USER_FINANCE("用户财务状况","PS_USER_FINANCE"),
    PS_USER_BASICINFO("用户基本属性","PS_USER_BASICINFO"),
    PS_USER_CREDITLOAN("用户历史信贷记录","PS_USER_CREDITLOAN"),
    PS_USER_IDENTITY("用户身份特质","PS_USER_IDENTITY"),
    PS_USER_COMMUNICATION("用户通信属性","PS_USER_COMMUNICATION"),
    PS_USER_CONSUMPTION("用户消费行为数据","PS_USER_CONSUMPTION"),
    PS_USER_VERFICATION("用户验证信息","PS_USER_VERFICATION"),
    PS_USER_DATA_REPORT_MODEL_OUT_PUTITEM("用户大数据报告模型输出话术","PS_USER_DATA_REPORT_MODEL_OUT_PUTITEM"),
    PS_XFD_SELF_RECORD("消费贷历史数据主贷人贷款记录", "PS_XFD_SELF_RECORD"),
    PS_XFD_EMERGENCYCONTACT1_RECORD("消费贷历史数据紧急联系人1贷款记录", "PS_XFD_EMERGENCYCONTACT1_RECORD"),
    PS_XFD_CONTACTS_RECORD("消费贷历史数据通讯录贷款记录", "PS_XFD_CONTACTS_RECORD"),
    PS_XFD_CALLTOP10_RECORD("消费贷历史通话时间top10贷款记录", "PS_XFD_CALLTOP10_RECORD"),
;
    private String content;
    private String value;

    private VariableType(String content, String value) {
        this.content = content;
        this.value = value;
    }

    public String getContent() {
        return this.content;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return content;
    }

    public static VariableType getByValue(String value) {
        for (VariableType b : VariableType.values()) {
            if (b.value.equals(value))
                return b;
        }
        return null;
    }
}
