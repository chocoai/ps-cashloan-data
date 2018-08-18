package com.adpanshi.cashloan.data.variable.enums;

import com.adpanshi.cashloan.common.enums.ContentEnum;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public enum EmergencyRelType implements ContentEnum {

    /**
     * 紧急联系人
     */
    PARENT("父母",1),
    CHILDREN("子女",2),
    BROTHERS_AND_SISTERS("兄弟姐妹",3),
    RELATION("亲戚",4),
    FRIEND("朋友",5),
    WORK_MATE("同事",6),
    OTHERS("其他",7);

    private String content;
    private Integer value;

    @Override
    public boolean equalsValue(Integer value)
    {
        return (value != null) && (value.equals(getValue()));
    }
    private EmergencyRelType(String content, Integer value) {
        this.content = content;
        this.value = value;
    }

    public static EmergencyRelType valueOf(Integer value) {
        EmergencyRelType[] entities = EmergencyRelType.values();
        for (EmergencyRelType entity : entities) {
            if (entity.getValue().equals(value)) {
                return entity;
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
        return content;
    }
}
