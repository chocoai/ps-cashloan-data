package com.adpanshi.cashloan.data.variable.enums;


/**
 * 变量类别
 * Created by zsw on 2018/6/29 0029.
 */
public enum VariableType {

    /**
     * 用户基本属性
     */
    USER_BASICINFO("用户基本属性","USER_BASICINFO"),
    USER_CONTACTS_INFO("用户社交属性","USER_CONTACTS_INFO"),
    USER_CHEAT("用户欺诈类属性","USER_CHEAT"),
    USER_CREDIT("用户信用属性","USER_CREDIT"),
    USER_FASTTOUCH("用户紧急联系人属性","USER_FASTTOUCH"),
    USER_BADRECORD("用户不良记录","USER_BADRECORD"),
    USER_DEVICEINFO_ATTRIBUTE("用户设备属性","USER_DEVICEINFO_ATTRIBUTE"),
    USER_FINANCE("用户财务状况","USER_FINANCE"),
    USER_CREDITLOAN("用户历史信贷记录","USER_CREDITLOAN"),
    USER_IDENTITY("用户身份特质","USER_IDENTITY"),
    USER_COMMUNICATION("用户通信属性","USER_COMMUNICATION"),
    USER_CALLRECORD("用户联系记录","USER_CALLRECORD"),
    USER_CONSUMPTION("用户消费行为数据","USER_CONSUMPTION"),
    USER_VERFICATION("用户验证信息","USER_VERFICATION"),
    USER_DEVICE("用户设备信息", "USER_DEVICE"),
    USER_WORK("用户工作经历", "USER_WORK"),
    USER_EDUCATION("用户教育水平", "USER_EDUCATION"),
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
            if (b.value.equals(value)) {
                return b;
            }
        }
        return null;
    }
}
