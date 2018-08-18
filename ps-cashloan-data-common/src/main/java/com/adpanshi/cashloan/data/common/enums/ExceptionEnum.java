package com.adpanshi.cashloan.data.common.enums;

import com.adpanshi.cashloan.common.enums.ContentEnum;

/**
 * Created by zsw on 2018/7/15 0015.
 */
public enum ExceptionEnum  implements ContentEnum {

    /**
     * 非法传参
     */
    error_param("非法传参", 10001),;

    private String content;
    private Integer value;

    ExceptionEnum(String content, Integer value) {
        this.content = content;
        this.value = value;
    }

    @Override
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
