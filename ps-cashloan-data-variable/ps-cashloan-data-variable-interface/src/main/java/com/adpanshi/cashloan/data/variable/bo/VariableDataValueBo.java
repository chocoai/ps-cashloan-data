package com.adpanshi.cashloan.data.variable.bo;


import com.adpanshi.cashloan.common.utils.BeanUtil;
import com.adpanshi.cashloan.data.common.enums.DataTypeEnum;

import java.io.Serializable;

/**
 * Created by zsw on 2018/6/29 0029.
 */
public class VariableDataValueBo implements Serializable,BeanUtil.ConversionCustomizble {
    private static final long serialVersionUID = 1L;

    private String key;
    private Object value;
    private DataTypeEnum valueType;
    private String content;

    public VariableDataValueBo(){}

    public VariableDataValueBo(String key, String content, Object value, DataTypeEnum valueType){
        this.setKey(key);
        this.setContent(content);
        this.setValue(value);
        this.setValueType(valueType);
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DataTypeEnum getValueType() {
        return valueType;
    }

    public void setValueType(DataTypeEnum valueType) {
        this.valueType = valueType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        if(value==null) {
            value = "";
        }
        this.value = value;
    }

    @Override
    public void convertOthers(Object o) {
        Object valueType = BeanUtil.getPropValue(o, "valueType");
        if (valueType != null && valueType instanceof Integer) {
            this.setValueType(DataTypeEnum.getByValue((Integer) valueType));
        }
    }
}
