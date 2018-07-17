package com.adpanshi.cashloan.data.common.enums;

import com.adpanshi.cashloan.common.enums.ContentEnum;

/**
 * Created by zsw on 2018/7/15 0015.
 */
public enum ExceptionEnum  implements ContentEnum {

    error_param("非法传参", 10001),;

    private String content;
    private Integer value;

    ExceptionEnum(String content, Integer value) {
        this.content = content;
        this.value = value;
    }

    public boolean equalsValue(Integer value) {
        return (value != null) && (value.equals(getValue()));
    }

    public static ExceptionEnum getByValue(Integer value) {
        ExceptionEnum[] types = ExceptionEnum.values();
        for (ExceptionEnum type : types) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public String getContent() {
        return this.content;
    }

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
