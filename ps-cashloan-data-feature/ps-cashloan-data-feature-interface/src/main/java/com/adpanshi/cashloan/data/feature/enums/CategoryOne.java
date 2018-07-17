package com.adpanshi.cashloan.data.feature.enums;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public enum CategoryOne{
    USER_BASICINFO("用户基本属性","USER_BASICINFO"),
    USER_SOCIAL("社交特性","USER_SOCIAL"),
    USER_FRAUD("欺诈特性","USER_FRAUD"),
    USER_CONSUMPTION("用户消费行为数据","USER_CONSUMPTION"),
    USER_IDENTITY("用户身份特质","USER_IDENTITY"),
    USER_BADRECORD("用户不良记录","USER_BADRECORD"),
    USER_CREDITLOAN("用户信贷历史记录","USER_CREDITLOAN"),
    USER_FINANCE("用户身份特质","USER_FINANCE"),
    USER_DEVICE("用户设备属性","USER_DEVICE");
    private String content;
    private String value;
    private CategoryOne(String content,String value){
        this.content = content;
        this.value = value;
    }

    public String getContent() {
        return this.content;
    }

    public String getValue() {
        return this.value;
    }

    public static CategoryOne getByValue(String value) {
        for (CategoryOne b : CategoryOne.values()) {
            if (b.value.equals(value))
                return b;
        }
        return null;
    }
    @Override
    public String toString() {
        return content;
    }
}
