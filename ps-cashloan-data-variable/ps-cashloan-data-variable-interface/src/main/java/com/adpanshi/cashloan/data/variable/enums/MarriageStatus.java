package com.adpanshi.cashloan.data.variable.enums;


import com.adpanshi.cashloan.common.enums.ContentEnum;

/**
 * 婚姻状况枚举
 * Created by zsw on 2018/6/29 0029.
 */
public enum MarriageStatus implements ContentEnum {

    NO_MARRIED("未婚",0),
    MARRIED("已婚",1),
    DIVORCE("离婚",2);

    private String content;
    private Integer value;

    public boolean equalsValue(Integer value)
    {
        return (value != null) && (value.equals(getValue()));
    }
    private MarriageStatus(String content, Integer value) {
        this.content = content;
        this.value = value;
    }

    public static MarriageStatus valueOf(Integer value) {
        MarriageStatus[] entities = MarriageStatus.values();
        for (MarriageStatus entity : entities) {
            if (entity.getValue().equals(value)) {
                return entity;
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
        return content;
    }
}
