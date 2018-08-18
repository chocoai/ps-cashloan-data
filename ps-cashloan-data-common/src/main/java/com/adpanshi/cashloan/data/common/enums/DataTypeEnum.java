package com.adpanshi.cashloan.data.common.enums;

import com.adpanshi.cashloan.common.enums.ContentEnum;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public enum DataTypeEnum implements ContentEnum {

    /**
     * 整数类型
     */
    INTEGER("整数", 10),
    DOUBLE("小数", 20),
    DATE("日期", 30),
    STRING("字符串", 40),
    SECTION("区间", 50),
    BOOLEAN("布尔类型", 60),
    ENUM("枚举", 70),
    ARRAY("数组", 80),
    ;

    private String content;
    private Integer value;

    DataTypeEnum(String content, Integer value) {
        this.content = content;
        this.value = value;
    }

    @Override
    public boolean equalsValue(Integer value) {
        return (value != null) && (value.equals(getValue()));
    }

    public static DataTypeEnum getByValue(Integer value) {
        DataTypeEnum[] types = DataTypeEnum.values();
        for (DataTypeEnum type : types) {
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